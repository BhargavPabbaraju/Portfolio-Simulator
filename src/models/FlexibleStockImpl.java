package models;

import java.time.LocalDate;

public class FlexibleStockImpl implements  FlexibleStock{
  LocalDate date;
  String symbol;
  float numberOfShares;
  float transactionCost;

  FlexibleStockImpl(String symbol, LocalDate date, float numberOfShares,float transactionCost){
    this.symbol = symbol;
    this.date = date;
    this.numberOfShares  = numberOfShares;
    this.transactionCost = transactionCost;
  }


  @Override
  public StringBuilder getPlot(LocalDate startDate, LocalDate endDate) {
    return null;
  }

  @Override
  public float getValue(LocalDate date) {
    return 0;
  }



  @Override
  public float getCostBasis(LocalDate date) {
    return 0;
  }

  @Override
  public float getShares() {
    return this.numberOfShares;
  }
}
