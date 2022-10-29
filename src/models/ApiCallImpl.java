
package models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;

import models.jsonparser.JsonObject;
import models.jsonparser.JsonParser;

public class ApiCallImpl {
  private static String apiKey = "3YCR9C5VHZEI9WFZ";

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

  public static void initialize() {

  }

  public static float getData(String stockSymbol, LocalDate date) throws IllegalArgumentException {
    String dateString = date.toString();
    return dataChecking(stockSymbol, dateString);
  }

  public static boolean validSymbol(String symbol) {
    return cache.validSymbol(symbol);
  }

  public static float getSymbolData(String symbol) {
    return cache.getSymbolData(symbol);

  }

  private static float dataChecking(String stockSymbol, String dateString) {
    JsonObject data = cache.getTimeData(stockSymbol);
    if (data != null) {
      System.out.println("cache");
      try {
        return getSharevalue(data, dateString);
      } catch (IllegalArgumentException e) {
        cache.remove(stockSymbol);
        return stockValue(stockSymbol, dateString);
      }
    } else {
      return stockValue(stockSymbol, dateString);
    }
  }

  private static float stockValue(String stockSymbol, String dateString) {
    JsonObject data = ApiInteration(stockSymbol);
    if (data == null) {
      return cache.getSymbolData(stockSymbol);
    } else {
      return getSharevalue(data, dateString);
    }
  }

  private static JsonObject ApiInteration(String stockSymbol) {
    getJsonFormat(stockSymbol);
    removeMetaDataInFile(stockSymbol);
    JsonObject data = parseJson(stockSymbol);
    if (data != null) {
      cache.addStockToCache(stockSymbol, data);
    }
    return data;

  }

  private static float getSharevalue(JsonObject timeseries, String date) {
    return Float.parseFloat(timeseries.get(date).get("2.high").toString());
  }

  private static JsonObject parseJson(String symbol) {
    JsonObject json = null;
    try {
      json = JsonParser.parse("data" + File.separator + "cacheData" + File.separator + symbol + ".json");
    } catch (FileNotFoundException e) {
    }
    return json;
  }

  private static void getJsonFormat(String stockSymbol) {
    URL url = getURL(stockSymbol);
    InputStream in = null;
    try {
      in = url.openStream();
      File outputFile = new File("data" + File.separator + "cacheData" + File.separator + stockSymbol + ".json");
      FileOutputStream fos = new FileOutputStream(outputFile);
      int b;

      while ((b = in.read()) != -1) {
        fos.write(b);
      }
      fos.close();
    } catch (Exception e) {


    }
  }


  public static void removeMetaDataInFile(String symbol) {
    File inputFile = new File("data" + File.separator + "cacheData" + File.separator + symbol + ".json");
    File tempFile = new File("data" + File.separator + "cacheData" + File.separator + symbol + "1" + ".json");
    try {
      BufferedReader reader = new BufferedReader(new FileReader(inputFile));
      BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
      String lineToRemove = "\"1. Information\": \"Daily Prices (open, high, low, close) and Volumes\",";
      String currentLine;
      while ((currentLine = reader.readLine()) != null) {
        String trimmedLine = currentLine.trim();
        if (trimmedLine.equals(lineToRemove)) continue;
        writer.write(currentLine + System.getProperty("line.separator"));
        if (trimmedLine.equals(errorString)) {
          tempFile.delete();
          inputFile.delete();
          return;
        }
      }
      writer.close();
      reader.close();
      tempFile.renameTo(inputFile);
    } catch (IOException e) {

    }

  }

  private static URL getURL(String stockSymbol) {
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

