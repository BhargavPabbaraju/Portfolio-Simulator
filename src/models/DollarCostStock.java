package models;

import java.time.LocalDate;
import java.util.HashMap;

public interface DollarCostStock {
  float getCostBasis(LocalDate date);

  float getTotalValue(LocalDate date, ApiType apiType);

  LocalDate getStartDate();

  LocalDate getEndDate();

  float getAmount();

  int getInterval();

  HashMap<String,Float> getStocks();

  float getTransactionCost();

}
