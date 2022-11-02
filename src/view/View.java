package view;

/**
 * This interface represents the operations of a view.
 */
public interface View {

  /**
   * This method is used to display the initial menu , the first menu that is displayed when the
   * user runs the application.
   */
  void displayInitialMenu();

  /**
   * This method prompts the user to enter username.
   */
  void askForUsername();

  /**
   * This method prompts the user to enter initial balance.
   */
  void askForBalance();


  /**
   * This method prints the given message to the output stream.
   *
   * @param message String to display.
   */
  void displayMessage(String message);

  /**
   * This method displays the main menu , the menu that is shown after the user logs in.
   */
  void displayMainMenu();

  /**
   * This method prompts the user to enter portfolio name.
   */
  void askForPortfolioName();

  /**
   * This method prompts the user to enter a date in (yyyy-mm-dd) format.
   */
  void askForDate();

  /**
   * This method displays the total value on a given date.
   *
   * @param totalValue total value send from controller
   * @param date       date sent from controller
   */
  void displayValue(float totalValue, String date);

  /**
   * This method displays the given composition of a portfolio.
   *
   * @param composition composition sent from controller
   */
  void displayComposition(StringBuilder composition);

  /**
   * This method prompts the user to enter a stock symbol.
   */
  void askForStockSymbol();

  /**
   * This method prompts the user to enter number of shares.
   */

  void askForShares();

  /**
   * This method displays the menu to add a new stock.
   */

  void displayAddNewStockMenu();
}
