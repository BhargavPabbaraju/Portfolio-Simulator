package models;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;

/**
 * This interface represents operations that a user can perform.
 */
public interface User {

  /**
   * This method is used to create a portfolio with a given user name.
   * @param portfolioName
   */
  void createPortfolio(String portfolioName);

  /**
   * This method is used to load a portfolio with a given portfolio name.
   * @param portfolioName
   * @return
   * @throws IllegalArgumentException if that portfolio doesn't exist.
   */

  Portfolio loadPortfolio(String portfolioName) throws IllegalArgumentException;

  /**
   * This method is used to get the balance of this user.
   * @return
   */
  float getBalance();

  /**
   * This method is used to deduct a certain amount from this user's balance.
   * @param amount
   */

  void deductFromBalance(float amount);

  /**
   * This method is used to add a certain amount to this user's balance.
   * @param amount
   */

  void addToBalance(float amount);

  /**
   * This method is used to create a stock and add it to this user's active portfolio.
   * @param symbol
   * @param numberOfShares
   */

  void addStockToPortfolio(String symbol, float numberOfShares);

  /**
   * This method is used to load a stock from a file and add it to this user's active portfolio.
   * @param symbol
   * @param numberOfShares
   * @param date on which this stock was bought.
   */

  void addStockToPortfolio(String symbol, float numberOfShares, LocalDate date);

  /**
   * This method is used to save this user in a file.
   * @throws IOException
   */
  void save() throws IOException;

  /**
   * This method is used to get the name of this user.
   * @return
   */
  String getName();

  /**
   * this method is used to represent a hashmap containing all the portfolios of this user.
   * @return
   */
  HashMap<String,Portfolio> getPortfolios();

  /**
   * This method is used to get the total value of this user's active portfolio on a certain date.
   * @param date
   * @return
   */
  public float getTotalValue(String date);

  /**
   * This method is used to get the composition of this user's active portfolio.
   * @return
   */
  StringBuilder getComposition();
}
