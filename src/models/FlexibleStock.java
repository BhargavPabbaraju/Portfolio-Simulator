package models;

import java.time.LocalDate;


/**
 * This interface represents the operations that can be performed on a single stock of a company in
 * flexible portfolio.
 */
public interface FlexibleStock {

  /**
   * This method tell the user how a particular stock has performed over a period of time using
   * a line chart by taking date ranges in which he wants to see the performance.
   *
   * @param startDate LocalDate start date of the range.
   * @param endDate   LocalDate end date of the range.
   * @param apiType   ApiType used determine which third party api is to be called.
   * @return plot showing the performance of the stock.
   */
  StringBuilder getPlot(LocalDate startDate, LocalDate endDate, ApiType apiType);

  /**
   * This method is used to get the total value of this stock on a certain date.
   * If weekend values are given then it tells the user to enter a date which is not weekend.
   *
   * @param date    String which is given by the user
   * @param apiType ApiType used determine which third party api is to be called.
   * @return total value of the stock on a certain date.
   */
  float getValue(LocalDate date, ApiType apiType);

  /**
   * This method is used to get the cost basis of this stock.Cost Basis shows
   * the total amount of money invested in a portfolio by a specific date.
   *
   * @param date    String which is given by the user
   * @param apiType ApiType used determine which third party api is to be called.
   * @return float which has the total amount of money invested in a stock by a specific date
   */
  float getCostBasis(LocalDate date, ApiType apiType);

  /**
   * This method returns the  number of shares present in this stock.
   *
   * @return float number of shares present for this stock
   */
  float getShares();

  /**
   * This method returns transaction cost associated with this stock.
   *
   * @return float transaction cost
   */
  float getTransactionCost();
}
