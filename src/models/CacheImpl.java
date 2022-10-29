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

public class CacheImpl implements Cache {
  Map<String, JsonObject> cacheList;

  Map<String, Float> symbolList;

  private final String cacheDir = "data" + File.separator + "cacheData" + File.separator;

  public CacheImpl() throws FileNotFoundException, InterruptedException {
    this.cacheList = new HashMap<>();
    this.symbolList = new HashMap<>();
    Path path = Paths.get(this.cacheDir);
    this.loadCSVDate();
    if (Files.exists(path)) {
      this.LoadDate();
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
    if (symbolList.containsKey(symbol)) {
      return true;
    } else {
      return false;
    }

  }

  @Override
  public float getSymbolData(String symbol) {
    return symbolList.get(symbol);
  }

  private void LoadDate() throws FileNotFoundException, InterruptedException {
    File directoryPath = new File(cacheDir);
    String contents[] = directoryPath.list();

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
        String arr[] = trimmedLine.split(",");
        this.symbolList.put(arr[1], Float.parseFloat(arr[2]));
      }
      writer.close();
    } catch (IOException e) {
      System.out.println("IO expection in Symbol data file");
    }
  }
}
