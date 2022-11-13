import view.View;

/**
 * This class is used to test the controller in isolation.
 */
public class MockView implements View {
  private StringBuilder inputLog;
  private StringBuilder outputLog;

  /**
   * This constructor creates a mock model to test the controller in isolation.
   *
   * @param inputLog  String Builder to test if the input send by the controller are correct
   * @param outputLog String Builder to test if correct method is called
   */
  public MockView(StringBuilder inputLog, StringBuilder outputLog) {
    this.inputLog = inputLog;
    this.outputLog = outputLog;
  }

  @Override
  public void displayInitialMenu() {
    outputLog.append("displayInitialMenu ");

  }

  @Override
  public void askForUsername() {
    outputLog.append("askForUsername ");

  }

  @Override
  public void askForBalance() {
    outputLog.append("askForBalance ");

  }

  @Override
  public void displayMessage(String message) {
    outputLog.append("displayMessage ");

  }

  @Override
  public void displayMainMenu() {
    outputLog.append("displayMainMenu ");

  }

  @Override
  public void askForPortfolioName() {
    outputLog.append("askForPortfolioName ");

  }

  @Override
  public void askForDate() {
    outputLog.append("askForDate ");

  }

  @Override
  public void displayValue(float totalValue, String date) {
    inputLog.append(totalValue + " " + date);
    outputLog.append("displayValue ");

  }

  @Override
  public void displayComposition(StringBuilder composition) {
    inputLog.append(composition);
    outputLog.append("displayComposition ");

  }

  @Override
  public void askForStockSymbol() {
    outputLog.append("askForStockSymbol ");

  }

  @Override
  public void askForShares() {
    outputLog.append("askForShares ");

  }

  @Override
  public void displayAddNewStockMenu() {
    outputLog.append("displayAddNewStockMenu ");

  }

  @Override
  public void displayPortfolioTypesMenu() {

  }

  @Override
  public void displayFlexibleMenu() {

  }

  @Override
  public void askForStartDate() {

  }

  @Override
  public void askForEndDate() {

  }
}
