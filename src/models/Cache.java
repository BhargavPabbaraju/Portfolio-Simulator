package models;

import models.jsonparser.JsonObject;

/**
 * This interface represents the cache which stores data after making a api call to reduce
 * API calls.
 */
public interface Cache {

  /**
   * This method takes a symbol and timeseries data which is received from the API call and
   * store it in HashMap. This is increase the performance.
   *
   * @param symbol         String on which the stock value is to be returned.
   * @param timeSeriesData timeseries data of the symbol.
   */
  void addStockToCache(String symbol, JsonObject timeSeriesData);

  /**
   * This method takes a symbol  and returns the corresponding times series data stored in the
   * HashMap.
   *
   * @param symbol String on which the time series value is to be returned.
   * @return timeseries data from the cache
   */
  JsonObject getTimeData(String symbol);

  /**
   * This method takes a symbol and removed it from the HasMap.
   *
   * @param symbol String which is to be removed
   */
  void remove(String symbol);

  /**
   * This method takes a symbol and checks if it is valid or not.
   *
   * @param symbol String which is to be validated.
   * @return boolean which tells if the symbol is valid.
   */
  boolean validSymbol(String symbol);

  /**
   * This method takes a symbol and checks if it is valid or not.
   *
   * @param symbol String for which stock value is returned.
   * @return float which is the value of the stock which is backup option when a API call fails.
   */
  float getSymbolData(String symbol);

}
