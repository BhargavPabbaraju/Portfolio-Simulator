package models;

import java.time.LocalDate;
import java.util.HashMap;

import customDataType.PlotPair;

/**
 * This interface represents the operations on a single Flexible portfolio.
 */
public interface FlexiblePortfolio {

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
  void buyStock(String symbol, LocalDate date, float numberOfShares, float transactionCost);

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
  void sellStock(String symbol, LocalDate date, float numberOfShares, float transactionCost);

  /**
   * This method tell the user how a particular portfolio has performed over a period of time using
   * a line chart by taking date ranges in which he wants to see the performance.
   *
   * @param startDate LocalDate start date of the range.
   * @param endDate   LocalDate end date of the range.
   * @param apiType   ApiType used determine which third party api is to be called.
   * @return plot showing the performance of the portfolio.
   */
  StringBuilder getPlot(LocalDate startDate, LocalDate endDate, ApiType apiType);

  /**
   * This method is used to get the total value of this user's active portfolio on a certain date.
   * If weekend values are given then it tells the user to enter a date which is not weekend.
   *
   * @param date    String which is given by the user
   * @param apiType ApiType used determine which third party api is to be called.
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
  StringBuilder getComposition(LocalDate date);

  /**
   * This method is used to get the cost basis of this user's active portfolio.Cost Basis shows
   * the total amount of money invested in a portfolio by a specific date.
   *
   * @param date    String which is given by the user
   * @param apiType ApiType used determine which third party api is to be called.
   * @return float which has the total amount of money invested in a portfolio by a specific date
   */
  float getCostBasis(LocalDate date, ApiType apiType);

  /**
   * This method gives a hashmap representing all the stocks present in this portfolio.
   *
   * @return Hashmap of all the stocks in this portfolio
   */
  HashMap<String, FlexibleStocksList> getStocksList();

  /**
   * This method checks if a particular stock is present in a portfolio.
   *
   * @param symbol Symbol for validation.
   * @return boolean result of a validation.
   */
  public boolean stockExists(String symbol);

  /**
   * This method is used to invest a specific amount in an existing flexible portfolio on a
   * specific date by specifying the weights of how that money should be invested in each stock
   * inside that portfolio.
   *
   * @param date            LocalDate date on which he wants to purchase.
   * @param amount          float amount which the user wants to invest.
   * @param transactionCost float which is transaction associated for this transaction.
   * @param stocks          Hashmap of stocks which contains the Symbol as key and weight as value.
   * @param apiType         ApiType used determine which third party api is to be called.
   */
  void investIntoPortfolio(LocalDate date, float amount, float transactionCost, HashMap<String, Float> stocks, ApiType apiType);

  /**
   * This method is used to create a portfolio using dollar-cost averaging as specified above,
   * and query cost basis and value of such a portfolio at a specific date.
   *
   * @param startDate       LocalDate date on which he wants to start the purchase.
   * @param endDate         LocalDate date on which he wants to end the purchase.
   * @param interval        float interval in which the user wants to invest.
   * @param amount          float amount which the user wants to invest.
   * @param transactionCost float which is transaction associated for this transaction.
   * @param stocks          Hashmap of stocks which contains the Symbol as key and weight as value.
   */
  void createDollarCostStrategyPortfolio(LocalDate startDate, LocalDate endDate, int interval, float amount, float transactionCost, HashMap<String, Float> stocks);

  /**
   * This method is used to generate plotting values such as dates and values which are
   * used for plotting.
   *
   * @param startDate    LocalDate start date of the range.
   * @param endDate      LocalDate end date of the range.
   * @param apiType      ApiType used determine which third party api is to be called.
   * @param maximumPlots int which is the maximum number of points on x-axis of the plot.
   * @return return a PlotPair which is a custom datatype created by us to return dates and values
   */
  PlotPair newGetPlot(LocalDate startDate, LocalDate endDate, ApiType apiType, int maximumPlots);
}
