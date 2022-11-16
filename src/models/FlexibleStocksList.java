package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This interface represents the operations that can be performed on a stock list of a company in
 * flexible portfolio purchased on different dates.
 */
public interface FlexibleStocksList {

  /**
   * This method is used to buy a stock and add it to this user's active portfolio.
   * A flexible Stocklist contains a hashmap which stores Stock objects which were brought
   * on different dates. The key of the hash map is the date and the stock object
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
   *
   * @param symbol          String which should be a valid.
   * @param numberOfShares  float which is the number of shares the user wants to sell.
   * @param date            LocalDate date on which he wants to sell.
   * @param transactionCost float which is transaction associated for this transaction.
   */
  void sellStock(String symbol, LocalDate date, float numberOfShares, float transactionCost);

  /**
   * This method returns a ArrayList of flot values of the stock on given dates which is used for
   * plotting to show the portfolio's performance in a given range.
   *
   * @param datesList ArrayList of LocaDate for which the value is need.
   * @param apiType   ApiType used determine which third party api is to be called.
   * @return arraylist of float values user for plotting.
   */
  ArrayList<Float> getPlot(ArrayList<LocalDate> datesList, ApiType apiType);

  /**
   * This method is used to get the total value of this list containing different number of shares
   * which were brought on different date.If weekend values are given then it tells the user to
   * enter a date which is not weekend.
   *
   * @param date String which is given by the user
   * @param apiType   ApiType used determine which third party api is to be called.
   * @return total value of the stock on a certain date.
   */
  float getValue(LocalDate date, ApiType apiType);

  /**
   * This method is used to get the composition of this user's active portfolio. Composition shows
   * the symbol and number of shares of each stock in a portfolio purchased before a particular day.
   *
   * @param date String which is given by the user
   * @return StringBuilder which has symbol and number of shares for each stock in portfolio
   */
  float getComposition(LocalDate date);

  /**
   * This method is used to get the cost basis of this list containing different number of shares
   * * which were brought on different date .Cost Basis shows
   * the total amount of money invested in a portfolio by a specific date.
   *
   * @param date String which is given by the user
   * @param apiType   ApiType used determine which third party api is to be called.
   * @return float which has the total amount of money invested in a stock by a specific date
   */
  float getCostBasis(LocalDate date, ApiType apiType);

  /**
   * This method returns a HashMap of a stock with its shares purchased on different dates.
   *
   * @return hashmap of a stock with its shares purchased on different dates
   */
  HashMap<LocalDate, FlexibleStock> getStocks();


}
