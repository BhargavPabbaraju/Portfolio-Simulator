package view;

public interface View {
  void displayInitialMenu();

  void askForUsername();

  void askForBalance();

  

  void displayMessage(String message);

  void displayMainMenu();

  void askForPortfolioName();

  void askForDate();

  void displayValue(float totalValue, String date);

  void displayComposition(StringBuilder composition);

  void askForStockSymbol();

  void askForShares();

  void displayAddNewStockMenu();
}
