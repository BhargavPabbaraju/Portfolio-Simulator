package models;

import java.time.LocalDate;

public class NewUserImpl extends UserImpl implements NewUser{
  public NewUserImpl(String userName, float balance) throws IllegalArgumentException {
    super(userName, balance);
  }

  @Override
  public void buyStock(String symbol, LocalDate date, float numberOfShares) {
    this.activePortfolio.buyStock(symbol,date,numberOfShares);
  }

  @Override
  public void sellStock(String symbol, LocalDate date, float numberOfShares) {
    this.activePortfolio.sellStock(symbol,date,numberOfShares);
  }

  @Override
  public StringBuilder getPlot(LocalDate startDate, LocalDate endDate) {
    return this.activePortfolio.getPlot(startDate,endDate);
  }

  @Override
  public StringBuilder getComposition(LocalDate date) {
    return this.activePortfolio.getComposition(date);
  }

  @Override
  public float getCostBasis(LocalDate date) {
    return this.activePortfolio.getCostBasis(date);
  }


}
