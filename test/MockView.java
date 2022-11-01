import view.View;

public class MockView implements View {
  private StringBuilder inputLog;
  private StringBuilder outputLog;

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
}
