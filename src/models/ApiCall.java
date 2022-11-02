package models;

import java.io.IOException;
import java.time.LocalDate;

/**
 * This interface represents the Api calls. Mainly getting data from external sources.
 */
public interface ApiCall {

  /**
   * This method takes in Symbol of a company and date on which the share price is required and
   * returns the share value as a float.
   *
   * @param symbol unique symbol of a company.
   * @param date   the date in string format to get the share value
   * @return returns a float which is the share value of that company on a particular date.
   */
  float getData(String symbol, LocalDate date) throws IOException;

}
