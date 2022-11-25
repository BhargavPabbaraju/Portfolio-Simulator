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
   * @param isFlexible    tells if the portfolio is flexible
   */
  void createPortfolio(String portfolioName, boolean isFlexible);

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
   * @param date    String which is given by the user
   * @param apiType ApiType used determine which third party api is to be called.
   * @return total value of the portfolio on a certain date.
   */
  float getTotalValue(String date, ApiType apiType);


  /**
   * This method is used to buy a stock and add it to this user's active portfolio.
   * A flexible portfolio contains a hashmap which stores all these stocks. To add a stock to the
   * portfolio user needs to enter Stock Symbol, number of shares he wants, date on which he wants
   * and the transaction cost associated with it.
   *
   * @param symbol          String which should be a valid.
   * @param numberOfShares  float which is the number of shares the user wants of that company.
   * @param date            LocalDate date on which he wants to purchase.
   * @param transactionCost float which is transaction associated for this transaction.
   */
  void buyStock(String symbol, String date, float numberOfShares, float transactionCost);

  /**
   * This method is used to sell a stock from user's active portfolio.
   * It checks if it possible to sell the stocks and sells them only if it is possible.
   * To sell a stock from a portfolio user needs to enter Stock Symbol, number of shares he wants,
   * date on which he wants to sell and the transaction cost associated with it.
   *
   * @param symbol          String which should be a valid.
   * @param numberOfShares  float which is the number of shares the user wants to sell.
   * @param date            LocalDate date on which he wants to sell.
   * @param transactionCost float which is transaction associated for this transaction.
   */
  void sellStock(String symbol, String date, float numberOfShares, float transactionCost);

  /**
   * This method tell the user how a particular portfolio has performed over a period of time using
   * a line chart by taking date ranges in which he wants to see the performance.
   *
   * @param startDate LocalDate start date of the range.
   * @param endDate   LocalDate end date of the range.
   * @param apiType   ApiType used determine which third party api is to be called.
   * @return plot showing the performance of the portfolio.
   */
  StringBuilder getPlot(String startDate, String endDate, ApiType apiType);

  /**
   * This method is used to get the composition of this user's active portfolio. Composition shows
   * the symbol and number of shares of each stock in a portfolio.
   *
   * @return StringBuilder which has symbol and number of shares for each stock in portfolio
   */
  StringBuilder getComposition();

  /**
   * This method is used to get the composition of this user's active portfolio. Composition shows
   * the symbol and number of shares of each stock in a portfolio purchased before a particular day.
   *
   * @param date String which is given by the user
   * @return StringBuilder which has symbol and number of shares for each stock in portfolio
   */
  StringBuilder getComposition(String date);

  /**
   * This method is used to get the cost basis of this user's active portfolio.Cost Basis shows
   * the total amount of money invested in a portfolio by a specific date.
   *
   * @param date    String which is given by the user
   * @param apiType ApiType used determine which third party api is to be called.
   * @return float which has the total amount of money invested in a portfolio by a specific date
   */
  float getCostBasis(String date, ApiType apiType);

  /**
   * This method checks if a portfolio is flexible.
   *
   * @return boolean result of a validation.
   */
  boolean isFlexiblePortfolio();


  /**
   * This method gives a list of portfolios as a string builder.
   *
   * @return StringBuilder to get list of portfolios.
   */
  StringBuilder getListOfPortfolios();

  /**
   * This method checks if a particular stock exists in a portfolio.
   *
   * @param symbol Symbol for validation.
   * @return boolean result of a validation.
   */
  boolean stockExists(String symbol);

  /**
   * This method checks if a Date is valid.
   *
   * @param date date validation.
   * @return boolean result of a validation.
   */
  boolean isValidDate(String date);

  /**
   * This method checks if a portfolio exists with a given portfolio name.
   *
   * @param portfolioName portfolio for validation.
   * @return boolean result of a validation.
   */
  boolean portfolioExists(String portfolioName);

  /**
   * This method checks if at least one portfolio exists.
   *
   * @return boolean result of a validation.
   */
  boolean portfolioExists();

  void investIntoPortfolio( String date,float amount ,float transactionCost, HashMap<String,Float> stocks,ApiType apiType);

  void createDollarCostStrategyPortfolio(String startDate,String endDate,int interval,float amount ,float transactionCost, HashMap<String,Float> stocks);


  String getActivePortfolio();
}
