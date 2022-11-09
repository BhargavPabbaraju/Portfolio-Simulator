package models;

import java.time.LocalDate;

public interface FlexiblePortfolio {
  void buyStock(String symbol, LocalDate date,float numberOfShares);
  void sellStock(String symbol, LocalDate date,float numberOfShares);
  StringBuilder getPlot(LocalDate startDate,LocalDate endDate);
  float getTotalValue(String date);
  StringBuilder getComposition(LocalDate date);
  float getCostBasis(LocalDate date);
}
