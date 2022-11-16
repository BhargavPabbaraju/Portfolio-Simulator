
package models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;

import models.jsonparser.JsonObject;
import models.jsonparser.JsonParser;

/**
 * This class represents API Calls. It consists of static methods which interacts with third
 * party Api. It contains fields like apikey which is used for authentication of the api and a
 * cache object in which the data is stored after making api calls to avoid repeated calls for
 * same symbol. It also contains a error message which is returned when a api call fails.
 */
public class ApiCallImpl {
  //private static String apiKey = "3YCR9C5VHZEI9WFZ";
  private static String apiKey = "O7XGNPGQU35IKLRW";


  private static Cache cache;

  private static String errorString = "\"Note\": \"Thank you for using Alpha Vantage! Our " +
          "standard API call frequency is 5 calls per minute and 500 calls per day. Please visit " +
          "https://www.alphavantage.co/premium/ if you would like to target a higher API call " +
          "frequency.\"";

  static {
    try {
      cache = new CacheImpl();
    } catch (FileNotFoundException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * This method takes in Symbol of a company and date on which the share price is required and
   * returns the share value as a float. It checks in the cache first, if the symbol is not found
   * in cache only then it makes api call.
   *
   * @param stockSymbol unique symbol of a company.
   * @param date        the date in string format to get the share value
   * @return returns a float which is the share value of that company on a particular date.
   */
  public static float getData(String stockSymbol, LocalDate date, ApiType apiType) throws IllegalArgumentException {
    String dateString = date.toString();
    return dataChecking(stockSymbol, dateString, apiType);
  }

  /**
   * This method takes in Symbol of a company and checks if the symbol exists or not.
   *
   * @param symbol unique symbol of a company.
   * @return returns a boolean value if the symbol is valid or not.
   */
  public static boolean validSymbol(String symbol) {
    return cache.validSymbol(symbol);
  }

  private static float dataChecking(String stockSymbol, String dateString, ApiType apiType) {
    JsonObject data = cache.getTimeData(stockSymbol);
    if (data != null) {
      try {
        return getShareValue(data, dateString);
      } catch (IllegalArgumentException e) {
        cache.remove(stockSymbol);
        return stockValue(stockSymbol, dateString, apiType);
      }
    } else {
      return stockValue(stockSymbol, dateString, apiType);
    }
  }

  private static float stockValue(String stockSymbol, String dateString, ApiType apiType) {
    JsonObject data = apiInteration(stockSymbol, apiType);
    if (data == null) {
      return cache.getSymbolData(stockSymbol);
    } else {
      return getShareValue(data, dateString);
    }
  }

  private static JsonObject apiInteration(String stockSymbol, ApiType apiType) {
    String output = getJsonFormat(stockSymbol, apiType);
    removeMetaDataInFile(stockSymbol, output);
    JsonObject data = parseJson(stockSymbol);
    if (data != null) {
      cache.addStockToCache(stockSymbol, data);
    }
    return data;

  }

  private static float getShareValue(JsonObject timeseries, String date) {
    return Float.parseFloat(timeseries.get(date).get("4.close").toString());
  }

  private static JsonObject parseJson(String symbol) {
    JsonObject json = null;
    try {
      json = JsonParser.parse("data" + File.separator + "cacheData" + File.separator
              + symbol + ".json");
    } catch (FileNotFoundException e) {
      //If file is not found in the directory.
    }
    return json.get("TimeSeries(Daily)");
  }

  private static String getJsonFormat(String stockSymbol, ApiType apiType) {
    URL url = getURL(stockSymbol, apiType);
    InputStream in = null;
    StringBuilder output = new StringBuilder();

    try {
      in = url.openStream();
      int b;

      while ((b = in.read()) != -1) {
        output.append((char) b);
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("No price data found for " + stockSymbol);
    }
    return output.toString();
  }

  private static void removeMetaDataInFile(String symbol, String output) {
    if (output.length() < 300) {
      return;
    }
    File inputFile = new File("data" + File.separator + "cacheData" + File.separator
            + symbol + ".json");
    BufferedReader br = new BufferedReader(new StringReader(output));
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile));
      String lineToRemove = "\"1. Information\": \"Daily Prices (open, high, low, close) " +
              "and Volumes\",";
      String currentLine;
      while ((currentLine = br.readLine()) != null) {
        String trimmedLine = currentLine.trim();
        if (trimmedLine.equals(lineToRemove)) {
          continue;
        }
        writer.write(currentLine + System.getProperty("line.separator"));
        if (trimmedLine.equals(errorString)) {
          inputFile.delete();
          return;
        }
      }
      writer.close();
      BufferedReader br1 = new BufferedReader(new FileReader(inputFile));
      if (br1.readLine() == null) {
        inputFile.delete();
      }
    } catch (IOException e) {
      //If file is not found in the directory.
    }

  }

  private static URL getURL(String stockSymbol, ApiType apiType) {
    URL url = null;
    switch (apiType) {
      case ALPHA_VANTAGE:
        url = getAplhaVantageURL(stockSymbol, apiType);
        break;
      default:
        url = getAplhaVantageURL(stockSymbol, apiType);
        break;
    }
    return url;

  }

  private static URL getAplhaVantageURL(String stockSymbol, ApiType apiType) {
    URL url = null;
    try {
      url = new URL("https://www.alphavantage"
              + ".co/query?function=TIME_SERIES_DAILY"
              + "&outputsize=full"
              + "&symbol"
              + "=" + stockSymbol + "&apikey=" + apiKey + "&datatype=json");
    } catch (MalformedURLException e) {
      throw new RuntimeException("the alpha vantage API has either changed or "
              + "no longer works");
    }
    return url;


  }

}

