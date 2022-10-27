package models;

import java.util.HashMap;

import models.jsonparser.JsonObject;

public class CacheImpl implements Cache {
  HashMap<String, JsonObject> cacheList;

  public CacheImpl() {
    this.cacheList = new HashMap<>();
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


}
