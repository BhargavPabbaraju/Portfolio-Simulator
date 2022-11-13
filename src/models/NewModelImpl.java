package models;

import java.time.LocalDate;

public class NewModelImpl extends ModelImpl implements  NewModel{

  float transactionCost = 10;
  @Override
  public void buyStock(String symbol, String date, float numberOfShares) {

    this.user.buyStock(symbol,date,numberOfShares,transactionCost);
  }

  @Override
  public void sellStock(String symbol, String date, float numberOfShares) {
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
