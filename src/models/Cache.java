package models;

import models.jsonparser.JsonObject;

public interface Cache {
  public void addStockToCache(String symbol, JsonObject timeSeriesData);

  public JsonObject getTimeData(String symbol);
}
