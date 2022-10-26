package models;

import java.util.Date;
import java.util.HashMap;

/**
 * This class represents a single portfolio.
 */
class PortfolioImpl implements Portfolio {
  final String portfolioName;
  HashMap<String, Stock> stockList;

  User user;

  /**
   * This constructor creates a portfolio for this user.
   * @param portfolioName
   * @throws IllegalArgumentException if a user has already created a portfolio with this name.
   */
  PortfolioImpl(String portfolioName, User user) throws IllegalArgumentException{
    //throw exception if portfolio already exists for this user.
    this.portfolioName = portfolioName;
    this.user = user;
    //Create portfolio file.
  }

  @Override
  public void addStock(String symbol, float numberOfShares) throws IllegalArgumentException {
    Stock stock = new StockImpl(symbol,numberOfShares);
    float price = stock.getCostPrice();
    if(user.getBalance() >= price){
      user.deductFromBalance(price);
      stockList.put(symbol,stock);
    }

  }

  @Override
  public float getTotalValue(String date) {
    float value = 0;
    for(Stock stock : stockList.values()){
      value+=stock.getValue(date);
    }
    return value;
  }

  @Override
  public String getComposition() {
    return null;
  }
}
