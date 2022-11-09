package models;

import java.time.LocalDate;

public interface FlexibleUser extends User{
  void buyStock(String symbol, LocalDate date, float numberOfShares);
  void sellStock(String symbol, LocalDate date,float numberOfShares);
  StringBuilder getPlot(LocalDate startDate,LocalDate endDate);
  float getCostBasis(LocalDate date);
}
