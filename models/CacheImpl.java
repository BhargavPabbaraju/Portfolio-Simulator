package models;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import models.jsonparser.JsonObject;
import models.jsonparser.JsonParser;

public class CacheImpl implements Cache {
  HashMap<String, JsonObject> cacheList;

  private final String cacheDir = "data" + File.separator + "cacheData" + File.separator;

  public CacheImpl() throws FileNotFoundException, InterruptedException {
    this.cacheList = new HashMap<>();
    Path path = Paths.get(this.cacheDir);

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

}
