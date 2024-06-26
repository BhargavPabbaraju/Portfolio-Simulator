package view;

import java.io.PrintStream;

/**
 * This class represents an implementation of View.
 */
public class ViewImpl implements View {
  private PrintStream out;

  /**
   * This constructor creates a view with a given printStream. Displaying of the messages
   * is done by the controller.
   *
   * @param out the printStream
   */
  public ViewImpl(PrintStream out) {
    this.out = out;
  }

  @Override
  public void displayInitialMenu() {
    out.println("To select a particular option,enter the number next to it\n");
    out.println("1.Create User");
    out.println("2.Load User");
  }

  @Override
  public void askForUsername() {
    out.println("Enter username");
  }

  @Override
  public void askForBalance() {
    out.println("Enter initial balance");
  }

  @Override
  public void displayMessage(String message) {
    out.println("\n" + message);
  }

  @Override
  public void displayMainMenu() {
    out.println("\n1.Create Portfolio");
    out.println("2.Load Portfolio");
    out.println("3.Get Composition");
    out.println("4.Get Total Value on certain date");
    out.println("5.Save");
    out.println("6.Exit");
  }

  @Override
  public void askForPortfolioName() {
    out.println("Enter portfolio name");
  }

  @Override
  public void askForDate() {
    out.println("Enter date(yyyy-mm-dd)");
  }

  @Override
  public void displayValue(float totalValue, String date) {
    out.println("\nTotal Value on " + date + " is " + totalValue + "\n");
  }

  @Override
  public void displayComposition(StringBuilder composition) {
    out.println(composition.toString());
  }

  @Override
  public void askForStockSymbol() {
    out.println("Enter Stock Symbol");
  }

  @Override
  public void askForShares() {
    out.println("Enter number of shares");
  }

  @Override
  public void displayAddNewStockMenu() {
    out.println("1.Add new stock");
    out.println("2.Back to main menu");

  }
}
