package models;

import java.util.HashMap;

import customDataType.PlotPair;

/**
 * This class represents an implementation of NewerModel. It extends NewModelImpl created previously.
 */
public class NewerModelImpl extends NewModelImpl implements NewerModel {
  @Override
  public boolean isValidSymbol(String symbol) {
    return ApiCallImpl.validSymbol(symbol);
  }

  @Override
  public void investIntoPortfolio(String date, float amount, float transactionCost, HashMap<String, Float> stocks, ApiType apiType) {
    if (amount <= 0) {
      throw new IllegalArgumentException("amount should be greater than 0");
    }
    if (transactionCost < 0) {
      throw new IllegalArgumentException("transaction cost should not be negative");
    }
    if (!validateStocks(stocks)) {
      throw new IllegalArgumentException("stock symbol or weight is not valid");
    }
    this.user.investIntoPortfolio(date, amount, transactionCost, stocks, apiType);

  }

  @Override
  public void createDollarCostStrategyPortfolio(String startDate, String endDate, int interval, float amount, float transactionCost, HashMap<String, Float> stocks) {
    if (amount <= 0) {
      throw new IllegalArgumentException("amount should be greater than 0");
    }
    if (transactionCost < 0) {
      throw new IllegalArgumentException("transaction cost should not be negative");
    }
    if (interval <= 0) {
      throw new IllegalArgumentException("Interval should be positive integer");
    }
    if (!validateStocks(stocks)) {
      throw new IllegalArgumentException("stock symbol or weight is not valid");
    }
    this.user.createDollarCostStrategyPortfolio(startDate, endDate, interval, amount, transactionCost, stocks);
  }

  @Override
  public String getActivePortfolio() {
    return this.user.getActivePortfolio();
  }

  @Override
  public PlotPair newGetPlot(String startDate, String endDate, ApiType apiType,
                             int maximumPlots) {
    return this.user.newGetPlot(startDate, endDate, apiType, maximumPlots);
  }

  private boolean validateStocks(HashMap<String, Float> stocks) {
    for (String key : stocks.keySet()) {
      if (ApiCallImpl.validSymbol(key)) {
        if (stocks.get(key) <= 0) {
          return false;
        }
      } else {
        return false;
      }

    }
    return true;
  }
}
