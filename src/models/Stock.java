package models;


import java.io.IOException;
import java.time.LocalDate;

/**
 * This interface represents the operations that can be performed on a single stock of a company.
 */
interface Stock {

  float getShares();

  /**
   * This method gets the value of the stock at a given date. This will take the date from the
   * field of the object so there is no need to specify date.
   *
   * @param apiType ApiType used determine which third party api is to be called.
   * @return float value of the stock
   */
  float getValue(ApiType apiType) throws IOException;

  /**
   * This method gets the value of the stock at a given date.
   *
   * @param date    String on which the stock value is to be returned.
   * @param apiType ApiType used determine which third party api is to be called.
   * @return float value of the stock on a particular date
   */
  float getValue(String date, ApiType apiType);

  /**
   * This method gets the date of purchase of this stock.
   *
   * @return date in the format LocalDate
   */
  LocalDate getDate();


  /**
   * This method takes a number of shares and adds it to the current shares. This is only used when
   * user enters same stock symbol more than once while creating a portfolio.
   *
   * @param number the number of shares which are added to the current value
   */
  void addToShares(float number);

}
