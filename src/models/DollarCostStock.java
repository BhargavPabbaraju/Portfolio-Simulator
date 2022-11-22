package models;

public interface DollarCostStock {
  float getCostBasis(String date);

  float getTotalValue(String date);

}
