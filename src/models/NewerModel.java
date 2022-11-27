package models;

import java.util.HashMap;

import customDataType.PlotPair;

public interface NewerModel extends NewModel {

  boolean isValidSymbol(String symbol);
  void investIntoPortfolio( String date,float amount ,float transactionCost, HashMap<String,Float> stocks,ApiType apiType);

  void createDollarCostStrategyPortfolio(String startDate,String endDate,int interval,float amount ,float transactionCost, HashMap<String,Float> stocks);

  String getActivePortfolio();

  PlotPair newGetPlot(String startDate, String endDate, ApiType apiType,
                      int maximumPlots);
}
