package models;

import java.io.IOException;
import java.text.ParseException;

/**
 * This interface represents a model. The controller of the program only interacts with this model.
 */
public interface Model {
  /**
   * This method creates a user from a username and initial balance.
   * This method is called which creating a user from the program.
   *
   * @param userName String username which is unique for a user
   * @param balance  float balance which is entered by the user
   */
  void createUser(String userName, float balance);

  /**
   * This method loads a user from a file with the username as the file name.
   * It loads all the data from that file into the program.
   *
   * @param userName String username which is unique for a user
   * @throws IOException    throws an IO exception  which is handled by controller
   * @throws ParseException throws an ParseException which is handled by controller
   */
  void loadUser(String userName) throws IOException, ParseException;

  /**
   * This method is used to create a portfolio with a given portfolio name.
   * A portfolios should have unique names for a particular user.
   *
   * @param portfolioName String which is unique for each user
   */
  void createPortfolio(String portfolioName, boolean isFlexible);

  /**
   * This method is used to get the total value of this user's active portfolio on a certain date.
   * If weekend values are govern then it tells the user to enter a date which is not weekend.
   *
   * @param date    String which is given by the user
   * @param apiType ApiType used determine which third party api is to be called.
   * @return total value of the portfolio on a certain date.
   */
  float getTotalValue(String date, ApiType apiType);

  /**
   * This method is used to get the composition of this user's active portfolio. Composition shows
   * the symbol and number of shares of each stock in a portfolio.
   *
   * @return StringBuilder which has symbol and number of shares for each stock in portfolio
   */
  StringBuilder getComposition();

  /**
   * This method is used to create a stock and add it to this user's active portfolio.
   * A portfolio contains a hashmap which stores all these stocks. To add a stock to the portfolio
   * user needs to enter Stock Symbol and number of shares he wants.
   *
   * @param symbol String which should be a valid.
   * @param shares float which is the number of shares the user wants of that company.
   */
  void addStockToPortfolio(String symbol, int shares);

  /**
   * This method is used to load a portfolio with a given portfolio name. This is called by the user
   * to make a portfolio active and do operations like getComposition and getTotal value on a
   * certain date.
   *
   * @param portfolioName String which is unique for a user.
   */
  void loadPortfolio(String portfolioName);

  /**
   * This method is used to save this user in a file. Save option is given to the user in the menu.
   * It stores all the data into local file system.
   *
   * @throws IOException throws an IO exception is save is failed which is handled by controller
   */
  void save() throws IOException;


}
