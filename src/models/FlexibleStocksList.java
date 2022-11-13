package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public interface FlexibleStocksList {
  void buyStock(String symbol, LocalDate date, float numberOfShares,float transactionCost);
  void sellStock(String symbol, LocalDate date,float numberOfShares,float transactionCost);

  ArrayList<Float> getPlot(ArrayList<LocalDate> datesList);

  float getValue(LocalDate date);
  float getComposition(LocalDate date);
  float getCostBasis(LocalDate date);

  HashMap<LocalDate,FlexibleStock> getStocks();


}
