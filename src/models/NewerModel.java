package models;

import java.util.HashMap;

import customDataType.PlotPair;

/**
 * This interface represents a NewerModel which extends previously used NewModel interface.
 * The controller of the program only interacts with this model.
 */
public interface NewerModel extends NewModel {

  /**
   * This method is used to check if a given symbol is valid.
   *
   * @param symbol String symbol of the company which is to be validated.
   * @return boolean after the validation of symbol.
   */
  boolean isValidSymbol(String symbol);

  /**
   * This method is used to invest a specific amount in an existing flexible portfolio on a
   * specific date by specifying the weights of how that money should be invested in each stock
   * inside that portfolio.
   *
   * @param date            LocalDate date on which he wants to purchase.
   * @param amount          float amount which the user wants to invest.
   * @param transactionCost float which is cost associated for this transaction.
   * @param stocks          Hashmap of stocks which contains the Symbol as key and weight as value.
   * @param apiType         ApiType used determine which third party api is to be called.
   */
  void investIntoPortfolio(String date, float amount, float transactionCost, HashMap<String,
          Float> stocks, ApiType apiType);

  /**
   * This method is used to create a portfolio using dollar-cost averaging as specified above,
   * and query cost basis and value of such a portfolio at a specific date.
   *
   * @param startDate       LocalDate date on which he wants to start the purchase.
   * @param endDate         LocalDate date on which he wants to end the purchase.
   * @param interval        float interval in which the user wants to invest.
   * @param amount          float amount which the user wants to invest.
   * @param transactionCost float which is cost associated for this transaction.
   * @param stocks          Hashmap of stocks which contains the Symbol as key and weight as value.
   */
  void createDollarCostStrategyPortfolio(String startDate, String endDate, int interval,
                                         float amount, float transactionCost,
                                         HashMap<String, Float> stocks);

  /**
   * This method is used to get the current active portfolio which is loaded.
   *
   * @return return string which is name of the current active portfolio.l
   */
  String getActivePortfolio();

  /**
   * This method is used to generate plotting values such as dates and values which are
   * used for plotting.
   *
   * @param startDate    LocalDate start date of the range.
   * @param endDate      LocalDate end date of the range.
   * @param apiType      ApiType used determine which third party api is to be called.
   * @param maximumPlots int which is the maximum number of points on x-axis of the plot.
   * @return return a PlotPair which is a custom datatype created by us to return dates and values
   */
  PlotPair newGetPlot(String startDate, String endDate, ApiType apiType,
                      int maximumPlots);
}
