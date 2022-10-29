package models;

import java.time.LocalDate;
import java.util.HashMap;


/**
 * This interface represents the operations on a single portfolio.
 */
interface Portfolio {
  /**
   * This method adds a stock to this portfolio.
   *
   * @param symbol         The company's stock symbol.
   * @param numberOfShares Number of shares the user has.
   * @throws IllegalArgumentException if the stock symbol is invalid or if numberOfShares
   *                                  is invalid.
   */
  void createStock(String symbol, float numberOfShares) throws IllegalArgumentException;

  void createStock(String symbol, float numberOfShares, LocalDate date);

  /**
   * This method gives the total Value of this portfolio on a given date.
   *
   * @return
   */
  float getTotalValue(String date);

  /**
   * This method gives the composition of this portfolio.
   *
   * @return
   */
  StringBuilder getComposition();

  /**
   * This method is used to add a stock to a loaded portfolio.
   *
   * @param symbol
   * @param numberOfShares
   * @param dateBought
   */
  void addStock(String symbol, float numberOfShares, LocalDate dateBought);


  HashMap<String, Stock> getStocks();
}
