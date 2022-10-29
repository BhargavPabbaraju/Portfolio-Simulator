package models;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;

/**
 * This interface represents a user.
 */
public interface User {

  void createPortfolio(String portfolioName);

  Portfolio loadPortfolio(String portfolioName);

  float getBalance();

  void deductFromBalance(float amount);

  void addToBalance(float amount);

  void addStockToPortfolio(String symbol, float numberOfShares);

  void addStockToPortfolio(String symbol, float numberOfShares, LocalDate date);

  void save() throws IOException;

  String getName();

  HashMap<String,Portfolio> getPortfolios();

  public float getTotalValue(String date);

  StringBuilder getComposition();
}
