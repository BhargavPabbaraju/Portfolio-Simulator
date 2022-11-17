package models;


/**
 * This interface represents a NewModel which extends previously used Model interface.
 * The controller of the program only interacts with this model.
 */
public interface NewModel extends Model {
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
   * It checks if it is possible to sell the stocks and sells them only if it is possible.
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
   * This method is used to get the total value of this user's active portfolio on a certain date.
   * If weekend values are given then it tells the user to enter a date which is not weekend.
   *
   * @param date String which is given by the user
   * @return total value of the portfolio on a certain date.
   */
  float getTotalValue(String date, ApiType apiType);

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
   * This method checks if a User Exists.
   *
   * @param userName name of the current user.
   * @return boolean result of a validation.
   */
  boolean userExists(String userName);

  /**
   * This method checks if a particular stock exists in a portfolio.
   *
   * @param symbol Symbol for validation.
   * @return boolean result of a validation.
   */
  boolean stockExists(String symbol);

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

  /**
   * This method gives a list of portfolio as a stringbuilder.
   *
   * @return StringBuilder to get list of portfolios.
   */
  StringBuilder getListOfPortfolios();

  /**
   * This method checks if a Date is valid.
   *
   * @param date date validation.
   * @return boolean result of a validation.
   */
  public boolean isValidDate(String date);

}
