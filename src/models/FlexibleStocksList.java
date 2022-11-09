package models;

import java.time.LocalDate;

public interface FlexibleStocksList {
  void buyStock(String symbol, LocalDate date, float numberOfShares);
  void sellStock(String symbol, LocalDate date,float numberOfShares);
  StringBuilder getPlot(LocalDate startDate,LocalDate endDate);
  float getValue(LocalDate date);
  float getComposition(LocalDate date);
  float getCostBasis(LocalDate date);
}
