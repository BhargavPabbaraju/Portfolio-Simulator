package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
   * Note: Portfolio name must not contain the following characters: {}[],:"\
   * @param portfolioName
   * @throws IllegalArgumentException if a user has already created a portfolio with this name.
   */
  PortfolioImpl(String portfolioName, User user) throws IllegalArgumentException {
    //throw exception if portfolio already exists for this user.

    if(Loader.isInvalidName(portfolioName)){
      throw new IllegalArgumentException(
              "Portfolio name must not contain any of \\\"{}[],: characters.");
    }
    this.portfolioName = portfolioName;
    this.user = user;
    this.stockList = new HashMap<>();

  }

  @Override
  public void createStock(String symbol, float numberOfShares) throws IllegalArgumentException {
    Stock stock = new StockImpl(symbol, numberOfShares);
    //float price = stock.getValue();
    float price =0;
    if (user.getBalance() >= price) {
      user.deductFromBalance(price);
      stockList.put(symbol, stock);
    } else {
      throw new IllegalArgumentException("Out of balance");
    }
  }

  @Override
  public void createStock(String symbol, float numberOfShares, LocalDate date) {
    Stock stock = new StockImpl(symbol, numberOfShares, date);
    stockList.put(symbol, stock);
  }

  @Override
  public float getTotalValue(String date){
    LocalDate dateString = LocalDate.parse(date,
            DateTimeFormatter.ISO_LOCAL_DATE);
    float value = 0;
    for (Stock stock : stockList.values()) {
      value += stock.getValue(dateString);
    }
    return value;
  }

  @Override
  public StringBuilder getComposition() {
    StringBuilder composition = new StringBuilder();
    for(String key:stockList.keySet()){
      composition.append(key+"\t"+stockList.get(key).getShares()+"\n");
    }
    return composition;
  }


  @Override
  public void addStock(String symbol, float numberOfShares, LocalDate dateBought) {
    Stock stock = new StockImpl(symbol, numberOfShares, dateBought);
    stockList.put(symbol, stock);
  }

  @Override
  public HashMap<String, Stock> getStocks() {
    return stockList;
  }

  @Override
  public String toString() {
    return this.stockList.toString();
  }
}
