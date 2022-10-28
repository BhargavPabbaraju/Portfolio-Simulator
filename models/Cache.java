package models;

import java.io.FileNotFoundException;
import java.io.IOException;

import models.jsonparser.JsonObject;

public interface Cache {
  public void addStockToCache(String symbol, JsonObject timeSeriesData);

  public JsonObject getTimeData(String symbol);





}
