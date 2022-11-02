package models;

import java.time.LocalDate;
import java.util.HashMap;


/**
 * This interface represents the operations on a single portfolio. It contains a hash
 */
interface Portfolio {

  /**
   * This method is used to create a stock and add it to this user's active portfolio.
   * A portfolio contains a hashmap which stores all these stocks. To add a stock to the portfolio
   * user needs to enter Stock Symbol and number of shares he wants.
   *
   * @param symbol         String which should be a valid.
   * @param numberOfShares float which is the number of shares the user wants of that company.
   */
  void createStock(String symbol, float numberOfShares) throws IllegalArgumentException;

  /**
   * This method is used to load a stock from a file and add it to this user's active portfolio.
   * This method is called while loading a file from the local file system.
   *
   * @param symbol         String which should be a valid.
   * @param numberOfShares float which is the number of shares the user wants of that company.
   * @param date           on which this stock was bought taken from the file.
   */
  void createStock(String symbol, float numberOfShares, LocalDate date);

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

  /**
   * This method is used to add a stock to a loaded portfolio.
   *
   * @param symbol         String which should be a valid.
   * @param numberOfShares float which is the number of shares the user wants of that company.
   * @param dateBought     on which this stock was bought taken from the file.
   */
  void addStock(String symbol, float numberOfShares, LocalDate dateBought);

  /**
   * this method gives a hashmap representing all the stocks present in this portfolio.
   *
   * @return Hashmap of all the stocks in this portfolio
   */
  HashMap<String, Stock> getStocks();
}
