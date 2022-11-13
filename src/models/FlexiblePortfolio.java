package models;

import java.time.LocalDate;
import java.util.HashMap;

public interface FlexiblePortfolio {
  void buyStock(String symbol, LocalDate date,float numberOfShares,float transactionCost);
  void sellStock(String symbol, LocalDate date,float numberOfShares,float transactionCost);
  StringBuilder getPlot(LocalDate startDate,LocalDate endDate);
  float getTotalValue(String date);
  StringBuilder getComposition(LocalDate date);
  float getCostBasis(LocalDate date);

  HashMap<String, FlexibleStocksList> getStocksList();
}
