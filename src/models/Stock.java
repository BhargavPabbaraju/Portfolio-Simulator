package models;


import java.io.IOException;
import java.time.LocalDate;

/**
 * This interface represents the operations that can be performed on a single stock.
 */
interface Stock {

  float getShares();

  /**
   * This method gets the value of the stock at a given date.
   * @param date
   * @return
   */
  float getValue(String date);

  /**
   * This method gets the date of purchase of this stock.
   *
   * @return date as a String
   */
  LocalDate getDate();

  float getValue() throws IOException;

  void addToShares(float number);

}
