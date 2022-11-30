package controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

import customDataType.PlotPair;

/**
 * This interface represents the operations on controller.
 * It is the central part of the program and is called by the main method.
 */
public interface Features {

  /**
   * This method is used to create a portfolio with a given portfolio name.
   * A portfolios should have unique names for a particular user.
   *
   * @param portfolioName String which is unique for each user
   */
  void createPortfolio(String portfolioName);

  /**
   * This method is used to buy a stock and add it to this user's active portfolio.
   * A flexible portfolio contains a hashmap which stores all these stocks. To add a stock to the
   * portfolio user needs to enter Stock Symbol, number of shares he wants, date on which he wants
   * and the transaction cost associated with it.
   *
   * @param symbol         String which should be a valid.
   * @param numberOfShares float which is the number of shares the user wants of that company.
   * @param date           LocalDate date on which he wants to purchase.
   * @param commissionFee  float which is transaction associated for this transaction.
   * @param creating       boolean to check if it is called while creating.
   */
  void buyStocks(String symbol, int numberOfShares, String date, float commissionFee,
                 boolean creating);

  /**
   * This method is used to sell a stock from user's active portfolio.
   * It checks if it is possible to sell the stocks and sells them only if it is possible.
   * To sell a stock from a portfolio user needs to enter Stock Symbol, number of shares he wants,
   * date on which he wants to sell and the transaction cost associated with it.
   *
   * @param symbol         String which should be a valid.
   * @param numberOfShares float which is the number of shares the user wants to sell.
   * @param date           LocalDate date on which he wants to sell.
   * @param commissionFee  float which is transaction associated for this transaction.
   */
  void sellStocks(String symbol, float numberOfShares, String date, float commissionFee);

  /**
   * This method is used to get the cost basis of this user's active portfolio.Cost Basis shows
   * the total amount of money invested in a portfolio by a specific date.
   *
   * @param date String which is given by the user
   * @return float which has the total amount of money invested in a portfolio by a specific date
   */
  float getCostBasis(String date);

  /**
   * This method is used to get the total value of this user's active portfolio on a certain date.
   * If weekend values are govern then it tells the user to enter a date which is not weekend.
   *
   * @param date String which is given by the user
   * @return total value of the portfolio on a certain date.
   */
  float getTotalValue(String date);

  /**
   * This method is used to save this user in a file. Save option is given to the user in the menu.
   * It stores all the data into local file system.
   */
  void save();

  /**
   * This method is used to load a portfolio with a given portfolio name. This is called by the user
   * to make a portfolio active and do operations like getComposition and getTotal value on a
   * certain date.
   *
   * @param portfolioName String which is unique for a user.
   */
  void loadPortfolio(String portfolioName);

  /**
   * This method is used to invest a specific amount in an existing flexible portfolio on a
   * specific date by specifying the weights of how that money should be invested in each stock
   * inside that portfolio.
   *
   * @param date          LocalDate date on which he wants to purchase.
   * @param amount        float amount which the user wants to invest.
   * @param commissionFee float which is cost associated for this transaction.
   * @param stocks        Hashmap of stocks which contains the Symbol as key and weight as value.
   * @param creating      boolean to check if it is called while creating.
   */
  void investOnDate(String date, float amount, float commissionFee, HashMap<String, Float> stocks,
                    boolean creating);

  /**
   * This method is used to create a portfolio using dollar-cost averaging as specified above,
   * and query cost basis and value of such a portfolio at a specific date.
   *
   * @param startDate     LocalDate date on which he wants to start the purchase.
   * @param endDate       LocalDate date on which he wants to end the purchase.
   * @param interval      float interval in which the user wants to invest.
   * @param amount        float amount which the user wants to invest.
   * @param commissionFee float which is cost associated for this transaction.
   * @param stocks        Hashmap of stocks which contains the Symbol as key and weight as value.
   * @param creating      boolean to check if it is called while creating.
   */
  void dollarCost(String startDate, String endDate, int interval, float amount,
                  float commissionFee, HashMap<String, Float> stocks, boolean creating);

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
   */
  void loadUser(String userName);

  /**
   * This method is used to get the current active portfolio which is loaded.
   *
   * @return return string which is name of the current active portfolio.
   */
  String getActivePortfolio();

  /**
   * This method is used to get the list of portfolios of the current user.
   *
   * @return return string which has the list of portfolios.
   */
  String getListOfPortfolios();


  /**
   * This method is used to generate plotting values such as dates and values which are
   * used for plotting.
   *
   * @param startDate    LocalDate start date of the range.
   * @param endDate      LocalDate end date of the range.
   * @param maximumPlots int which is the maximum number of points on x-axis of the plot.
   * @return return a PlotPair which is a custom datatype created by us to return dates and values
   */
  PlotPair getPlot(String startDate, String endDate, int maximumPlots);
}
