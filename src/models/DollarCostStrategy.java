package models;

import java.time.LocalDate;
import java.util.HashMap;

/**
 * This interface represents the operations on stocks created by dollar costing average strategy.
 */
public interface DollarCostStrategy extends HigherLevelStrategy {

  /**
   * This method returns start date of the strategy which is used for saving in file system
   *
   * @return LocalDate which is the startDate of the Strategy.
   */
  LocalDate getStartDate();

  /**
   * This method returns end date of the strategy which is used for saving in file system
   *
   * @return LocalDate which is the endDate of the Strategy.
   */
  LocalDate getEndDate();

  /**
   * This method returns amount of the strategy which is used for saving in file system
   *
   * @return float which is the amount of the Strategy.
   */
  float getAmount();

  /**
   * This method returns interval of the strategy which is used for saving in file system
   *
   * @return int which is the interval of the Strategy.
   */
  int getInterval();

  /**
   * This method returns stocks if the strategy which is used for saving in file system
   *
   * @return HashMap which are the stocks in the Strategy.
   */
  HashMap<String, Float> getStocks();

  /**
   * This method returns transaction cost of the strategy which is used for saving in file system
   *
   * @return float which is the transaction cost  of the Strategy.
   */
  float getTransactionCost();
}
