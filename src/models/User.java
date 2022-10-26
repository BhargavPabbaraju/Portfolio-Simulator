package models;

/**
 * This interface represents a user.
 */
public interface User {

  void createPortfolio(String portfolioName);

  Portfolio loadPortfolio(String portfolioName);

  float getBalance();

  void deductFromBalance(float amount);

  void addToBalance(float amount);

  public void addStockToPortfolio(String symbol, float numberOfShares);

}
