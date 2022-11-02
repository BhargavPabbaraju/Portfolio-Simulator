package models;

import java.io.IOException;
import java.text.ParseException;

/**
 * This interface represents a model.
 */
public interface Model {
  /**
   * This method creates a user from a username and initial balance.
   * @param userName
   * @param balance
   * @throws IOException
   */
  void createUser(String userName, float balance) throws IOException;

  /**
   * This method loads a user from a file  with the same name as the username.
   * @param userName
   * @throws IOException
   * @throws ParseException
   */
  void loadUser(String userName) throws IOException, ParseException;

  /**
   * This method is used to create a portfolio with a given portfolio  name.
   * @param portfolioName
   */
  void createPortfolio(String portfolioName);

  /**
   * This method is used to get the total value of a portfolio on a given date.
   * @param date
   * @return
   */
  float getTotalValue(String date);

  /**
   * This method gives the composition of a portfolio.
   * @return
   */
  StringBuilder getComposition();

  /**
   * This method is used to add a stock to a portfolio.
   * @param symbol
   * @param shares
   */
  void addStockToPortfolio( String symbol, int shares);

  /**
   * This method takes in the name of the portfolio as a parameter and loads the portfolio.
   * @param portfolioName
   */
  void loadPortfolio(String portfolioName);

  /**
   * This method is used to save a user's details.
   * @throws IOException
   */
  void save() throws IOException;
}
