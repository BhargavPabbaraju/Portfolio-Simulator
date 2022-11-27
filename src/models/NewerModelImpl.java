package models;

import java.util.HashMap;

import customDataType.PlotPair;

public class NewerModelImpl extends NewModelImpl implements NewerModel {
  @Override
  public boolean isValidSymbol(String symbol) {
    return ApiCallImpl.validSymbol(symbol);
  }

  @Override
  public void investIntoPortfolio(String date, float amount, float transactionCost, HashMap<String, Float> stocks, ApiType apiType) {
    this.user.investIntoPortfolio(date, amount, transactionCost, stocks, apiType);

  }

  @Override
  public void createDollarCostStrategyPortfolio(String startDate, String endDate, int interval, float amount, float transactionCost, HashMap<String, Float> stocks) {
    this.user.createDollarCostStrategyPortfolio(startDate, endDate, interval, amount, transactionCost, stocks);
  }

  @Override
  public String getActivePortfolio() {
    return this.user.getActivePortfolio();
  }

  @Override
  public PlotPair newGetPlot(String startDate, String endDate, ApiType apiType,
                             int maximumPlots) {
    return this.user.newGetPlot(startDate,endDate,apiType,maximumPlots);
  }
}
