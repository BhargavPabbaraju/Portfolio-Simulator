package models;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;

/**
 * This interface represents operations that a user can perform. A user will have a unique name and
 * can be created by the program or loaded from the file system.
 */
public interface User {

  /**
   * This method is used to create a portfolio with a given portfolio name.
   * A portfolios should have unique names for a particular user.
   *
   * @param portfolioName String which is unique for each user
   */
  void createPortfolio(String portfolioName,boolean isFlexible);

  /**
   * This method is used to load a portfolio with a given portfolio name. This is called by the user
   * to make a portfolio active and do operations like getComposition and getTotal value on a
   * certain date.
   *
   * @param portfolioName String which is unique for a user.
   * @return a portfolio object which can used for future operations.
   * @throws IllegalArgumentException if that portfolio doesn't exist.
   */

  Portfolio loadPortfolio(String portfolioName) throws IllegalArgumentException;

  /**
   * This method is used to get the balance of this user. Balance is just a number which has no
   * use as of now but can be usefully when buy and sell is implemented.
   *
   * @return a float value which contains the current balance of the user.
   */
  float getBalance();

  /**
   * This method is used to deduct a certain amount from this user's balance.
   *
   * @param amount float which is to be deducted from the current balance
   */

  void deductFromBalance(float amount);

  /**
   * This method is used to add a certain amount to this user's balance.
   *
   * @param amount which is to be added to the current balance
   */

  void addToBalance(float amount);

  /**
   * This method is used to create a stock and add it to this user's active portfolio.
   * A portfolio contains a hashmap which stores all these stocks. To add a stock to the portfolio
   * user needs to enter Stock Symbol and number of shares he wants.
   *
   * @param symbol         String which should be a valid.
   * @param numberOfShares float which is the number of shares the user wants of that company.
   */

  void addStockToPortfolio(String symbol, float numberOfShares);

  /**
   * This method is used to load a stock from a file and add it to this user's active portfolio.
   * This method is called while loading a file from the local file system.
   *
   * @param symbol         String which should be a valid.
   * @param numberOfShares float which is the number of shares the user wants of that company.
   * @param date           on which this stock was bought taken from the file.
   */

  void addStockToPortfolio(String symbol, float numberOfShares, LocalDate date);

  /**
   * This method is used to save this user in a file. Save option is given to the user in the menu.
   * It stores all the data into local file system.
   *
   * @throws IOException throws an IOException if saving fails.
   */
  void save() throws IOException;

  /**
   * This method is used to get the name of this user.
   *
   * @return String which contains the username
   */
  String getName();

  /**
   * this method is used to represent a hashmap containing all the portfolios of this user.
   *
   * @return HashMap which contains all the list of portfolio's for a particular folder
   */
  HashMap<String, AbstractPortfolio> getPortfolios();

  /**
   * This method is used to get the total value of this user's active portfolio on a certain date.
   * If weekend values are govern then it tells the user to enter a date which is not weekend.
   *
   * @param date String which is given by the user
   * @return total value of the portfolio on a certain date.
   */
  float getTotalValue(String date);

  /**
   * This method is used to get the composition of this user's active portfolio. Composition shows
   * the symbol and number of shares of each stock in a portfolio.
   *
   * @return StringBuilder which has symbol and number of shares for each stock in portfolio
   */
  StringBuilder getComposition();

  void buyStock(String symbol, String date, float numberOfShares);
  void sellStock(String symbol, String date,float numberOfShares);
  StringBuilder getPlot(String startDate,String endDate);

  StringBuilder getComposition(String date);
  float getCostBasis(String date);
}
