package models;

import java.time.LocalDate;

/**
 * This interface represents the operations on stocks created by dollar costing average strategy.
 */
public interface DollarCostStock {

  /**
   * This method is used to get the cost basis of this user's dollar costing average stocks.
   * Cost Basis shows the total amount of money invested in a portfolio by a specific date.
   *
   * @param date String which is given by the user
   * @return float which has the total amount of money invested in a portfolio by a specific date
   */
  float getCostBasis(LocalDate date);

  /**
   * This method is used to get the total value of this user's stocks created by dollar costing
   * average on a certain date. If weekend values are given then it tells the user to enter a
   * date which is not weekend.
   *
   * @param date    String which is given by the user
   * @param apiType ApiType used determine which third party api is to be called.
   * @return total value of the portfolio on a certain date.
   */
  float getTotalValue(LocalDate date, ApiType apiType);

}
