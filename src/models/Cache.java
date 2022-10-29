package models;

import java.io.FileNotFoundException;
import java.io.IOException;

import models.jsonparser.JsonObject;

public interface Cache {
   void addStockToCache(String symbol, JsonObject timeSeriesData);

   JsonObject getTimeData(String symbol);

   void remove(String symbol);

   boolean validSymbol(String symbol);

   float getSymbolData(String symbol);




}
