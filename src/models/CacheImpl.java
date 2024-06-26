package models;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


import models.jsonparser.JsonObject;
import models.jsonparser.JsonParser;

/**
 * This class handles the caching of the data after the API call.
 */
public class CacheImpl implements Cache {
  Map<String, JsonObject> cacheList;

  Map<String, Float> symbolList;

  private final String cacheDir = "data" + File.separator + "cacheData" + File.separator;

  /**
   * Constructs cacheImpl and initialises values by loading a file which contains stock symbol's
   * of around 4000 companies. Along with this it also loads data from cacheData folder which
   * contains time-series data for each company to improve performance. This file is not hardcoded
   * it is generated on the go automatically from the API call and saved in file system.
   * This improves the performance of the app.
   *
   * @throws InterruptedException  throws a InterruptedException when loading fails
   * @throws FileNotFoundException throws a FileNotFoundException if the file is not found in the
   *                               directory
   */
  public CacheImpl() throws FileNotFoundException, InterruptedException {
    this.cacheList = new HashMap<>();
    this.symbolList = new HashMap<>();
    Path path = Paths.get(this.cacheDir);
    this.loadCSVDate();
    if (Files.exists(path)) {
      this.loadDate();
    }
    try {
      Files.createDirectories(path);
    } catch (IOException e) {
      System.err.println("Failed to create directory!" + e.getMessage());
    }
  }

  @Override
  public void addStockToCache(String symbol, JsonObject timeSeriesData) {
    cacheList.put(symbol, timeSeriesData);
  }

  @Override
  public JsonObject getTimeData(String symbol) {
    if (cacheList.containsKey(symbol)) {
      return cacheList.get(symbol);
    } else {
      return null;
    }
  }

  @Override
  public void remove(String symbol) {
    cacheList.remove(symbol);
  }

  @Override
  public boolean validSymbol(String symbol) {
    return symbolList.containsKey(symbol);
  }

  @Override
  public float getSymbolData(String symbol) {
    return symbolList.get(symbol);
  }

  private void loadDate() throws FileNotFoundException {
    File directoryPath = new File(cacheDir);
    String[] contents = directoryPath.list();

    for (int i = 0; i < contents.length; i++) {
      String[] arr = contents[i].split("\\.");
      String pathOfFile = cacheDir + contents[i];
      this.loadingIntoCache(pathOfFile, arr[0]);

    }
  }

  private void loadingIntoCache(String path, String s) throws FileNotFoundException {

    JsonObject json = JsonParser.parse(path);
    cacheList.put(s, json);
  }

  private void loadCSVDate() {
    File tempFile = new File("data" + File.separator + "data" + ".csv");
    BufferedReader writer = null;
    try {
      writer = new BufferedReader(new FileReader(tempFile));
    } catch (FileNotFoundException e) {
      System.out.println("Symbol data file not found");
    }
    String currentLine;
    try {
      while ((currentLine = writer.readLine()) != null) {
        String trimmedLine = currentLine.trim();
        String[] arr = trimmedLine.split(",");
        this.symbolList.put(arr[1], Float.parseFloat(arr[2]));
      }
      writer.close();
    } catch (IOException e) {
      System.out.println("IO expection in Symbol data file");
    }
  }
}
