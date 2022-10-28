
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

  static {
    try {
      cache = new CacheImpl();
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public static void initialize() {

  }

  public static float getData(String stockSymbol, LocalDate date) throws IllegalArgumentException, IOException {
    JsonObject data = cache.getTimeData(stockSymbol);
    if (data != null) {
      System.out.println("cache");
    } else {
      getJsonFormat(stockSymbol);
      removeMetaDataInFile(stockSymbol);
      data = parseJson(stockSymbol);
      cache.addStockToCache(stockSymbol, data);
    }
    String dateString = date.toString();
    return getSharevalue(data, dateString);
  }

  private static float getSharevalue(JsonObject timeseries, String date) {
    return Float.parseFloat(timeseries.get(date).get("2.high").toString());
  }

  private static JsonObject parseJson(String symbol) throws FileNotFoundException {
    JsonObject json = JsonParser.parse("data" + File.separator + "cacheData" + File.separator + symbol + ".json");
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
    } catch (IOException e) {
      throw new IllegalArgumentException("No price data found for " + stockSymbol);
    }
  }

  private static void removeMetaDataInFile(String symbol) throws IOException {
    File inputFile = new File("data" + File.separator + "cacheData" + File.separator + symbol + ".json");
    File tempFile = new File("data" + File.separator + "cacheData" + File.separator + symbol + "1" + ".json");

    BufferedReader reader = new BufferedReader(new FileReader(inputFile));
    BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

    String lineToRemove = "\"1. Information\": \"Daily Prices (open, high, low, close) and Volumes\",";
    String currentLine;

    while ((currentLine = reader.readLine()) != null) {
      String trimmedLine = currentLine.trim();
      if (trimmedLine.equals(lineToRemove)) continue;
      writer.write(currentLine + System.getProperty("line.separator"));
    }
    writer.close();
    reader.close();
    tempFile.renameTo(inputFile);

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

