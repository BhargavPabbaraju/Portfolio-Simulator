package models;

import java.time.LocalDate;
import java.util.HashMap;

public interface DollarCostStrategy extends HigherLevelStrategy {
  LocalDate getStartDate();

  LocalDate getEndDate();

  float getAmount();

  int getInterval();

  HashMap<String, Float> getStocks();

  float getTransactionCost();
}
