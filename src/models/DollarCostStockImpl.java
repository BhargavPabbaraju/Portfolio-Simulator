package models;

import java.time.LocalDate;
import java.util.HashMap;

public class DollarCostStockImpl implements DollarCostStock{

  LocalDate startDate;
  LocalDate endDate;
  int interval;
  HashMap<String,Float> stocks; // Symbol: Amount , AAPL:200/50%;




  @Override
  public float getCostBasis(String date) {
    return 0;
  }

  @Override
  public float getTotalValue(String date) {
    return 0;
  }
}
