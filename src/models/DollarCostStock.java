package models;

import java.time.LocalDate;

public interface DollarCostStock {
  float getCostBasis(LocalDate date);

  float getTotalValue(LocalDate date, ApiType apiType);

}
