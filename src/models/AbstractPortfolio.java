package models;

import java.time.LocalDate;
import java.util.HashMap;

public abstract class AbstractPortfolio implements Portfolio,FlexiblePortfolio{

  @Override
  public void buyStock(String symbol, LocalDate date, float numberOfShares) {
    //throw exception for inflexible
  }

  @Override
  public void sellStock(String symbol, LocalDate date, float numberOfShares) {
    //throw exception for inflexible
  }

  @Override
  public StringBuilder getPlot(LocalDate startDate, LocalDate endDate) {
    //throw exception for inflexible
    return null;
  }



  @Override
  public StringBuilder getComposition(LocalDate date){
    return null;
  }

  @Override
  public float getCostBasis(LocalDate date) {
    //throw exception for inflexible
    return 0;
  }

  @Override
  public void createStock(String symbol, float numberOfShares) throws IllegalArgumentException {
    //throw exception for flexible
  }

  @Override
  public void createStock(String symbol, float numberOfShares, LocalDate date) {
    //throw exception for flexible
  }

  @Override
  public abstract float getTotalValue(String date);

  @Override
  public StringBuilder getComposition(){
    return null;
  }

  @Override
  public void addStock(String symbol, float numberOfShares, LocalDate dateBought) {
    ////throw exception for flexible
  }

  @Override
  public HashMap<String, Stock> getStocks() {
    //throw exception for flexible
    return null;
  }
}
