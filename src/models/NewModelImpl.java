package models;

import java.time.LocalDate;

public class NewModelImpl extends ModelImpl implements  NewModel{

  @Override
  public void buyStock(String symbol, String date, float numberOfShares,float transactionCost) {
    if(transactionCost<0){
      throw new IllegalArgumentException("Transaction Cost must be a valid positive floating" +
              "point number");
    }
    this.user.buyStock(symbol,date,numberOfShares,transactionCost);
  }

  @Override
  public void sellStock(String symbol, String date, float numberOfShares,float transactionCost) {
    if(transactionCost<0){
      throw new IllegalArgumentException("Transaction Cost must be a valid positive floating" +
              "point number");
    }
    this.user.sellStock(symbol,date,numberOfShares,transactionCost);
  }

  @Override
  public StringBuilder getPlot(String startDate, String endDate) {
    return this.user.getPlot(startDate,endDate);
  }

  @Override
  public StringBuilder getComposition(String date) {
    return this.user.getComposition(date);
  }

  @Override
  public float getCostBasis(String date) {
    return this.user.getCostBasis(date);
  }

  @Override
  public boolean isFlexiblePortfolio() {
    return this.user.isFlexiblePortfolio();
  }
}
