package controller;

import java.io.InputStream;

import java.util.Scanner;


import models.ApiCallImpl;
import models.ApiType;
import models.NewModel;
import view.View;

/**
 * This class represents a controller implementation. It processes user inputs and delegates methods
 * to model and view.
 */

public class ControllerImpl implements Controller {
  private final NewModel model;
  private final View view;
  private final Scanner in;

  private int option;
  private boolean quit;

  /**
   * Constructs Controller and initialises values of the model, view and input stream.
   *
   * @param model model object which actually has the logic.
   * @param view  view object which is responsible to display things to the user
   * @param in    input stream of the data
   */
  public ControllerImpl(NewModel model, View view, InputStream in) {
    this.model = model;
    this.view = view;
    this.in = new Scanner(in);
    this.option = 1;
    this.quit = false;
  }

  private float floatHandler(String type) {
    if (type.equals("Balance")) {
      view.askForBalance();
    } else {
      view.askForTransactionCost();
    }

    if (in.hasNextFloat()) {
      float balance = in.nextFloat();

      if (balance > 0) {
        return balance;
      }

    } else {
      in.next();
    }


    view.displayMessage(type + " must be a valid floating point number");
    return floatHandler(type);

  }

  private String dateHandler(String kind) {
    if (kind.equals("start")) {
      view.askForStartDate();
    } else if (kind.equals("end")) {
      view.askForEndDate();
    } else {
      view.askForDate();
    }

    String date = in.next();
    try {
      if (model.isValidDate(date)) {
        return date;
      } else {
        view.displayMessage("Date cannot be a future date");
      }
    } catch (Exception e) {
      view.displayMessage(e.getMessage());

    }
    return dateHandler(kind);
  }

  private void createUser(String userName) {

    if (model.userExists(userName)) {
      view.displayMessage("User already exists");
      initialMenu();
      return;

    }
    try {
      float balance = floatHandler("Balance");
      model.createUser(userName, balance);
      view.displayMessage("User successfully created");
      flexiblePortfolioOptions();

    } catch (Exception e) {
      view.displayMessage(e.getMessage());
      initialMenu();

    }
  }

  private void loadUser(String userName) {
    try {
      model.loadUser(userName);
      view.displayMessage("User successfully loaded");
    } catch (Exception e) {
      view.displayMessage(e.getMessage());
      initialMenu();
    }

  }


  private void initialMenu() {
    view.displayInitialMenu();

    if (isOptionInvalid(2)) {

      initialMenu();
      return;

    }
    view.askForUsername();
    String userName = in.next();
    if (option == 1) {
      createUser(userName);

    } else if (option == 2) {
      loadUser(userName);
    }

  }


  private boolean isOptionInvalid(int numberOfOptions) {
    boolean invalid = false;
    if (in.hasNextInt()) {
      option = in.nextInt();
      if (option < 1 || option > numberOfOptions) {
        invalid = true;
      }

    } else {
      in.next();
      invalid = true;
    }

    if (invalid) {

      view.displayMessage("Option must be one of the following numbers");

    }
    return invalid;
  }

  @Override
  public void goController() {
    initialMenu();

    while (!quit) {

      if (model.isFlexiblePortfolio()) {
        flexiblePortfolioOptions();
      } else {
        mainMenu();
      }

    }

  }

  private void mainMenu() {
    view.displayMainMenu();

    String portfolioName = "";
    if (isOptionInvalid(7)) {
      mainMenu();
    }
    if (option < 3) {
      view.askForPortfolioName();
      try {
        portfolioName = in.next();
      } catch (Exception e) {
        view.displayMessage(e.getMessage());
        mainMenu();
      }


    }
    switch (option) {

      case 1:
        createPortfolio(portfolioName);
        break;
      case 2:
        loadPortfolio(portfolioName);
        break;
      case 3:
        getComposition();
        break;
      case 4:
        getTotalValue();
        break;
      case 5:
        viewListOfPortfolios();
        break;
      case 6:
        save();
        break;
      case 7:
        this.quit = true;
        break;
      default:
        //Option can only be one of the above.
    }
  }

  private void save() {
    try {
      model.save();
      view.displayMessage("Successfully saved.");
    } catch (Exception e) {
      view.displayMessage(e.getMessage());
      mainMenu();
    }

  }

  private void loadPortfolio(String portfolioName) {
    try {
      model.loadPortfolio(portfolioName);
      view.displayMessage("Loaded " + portfolioName);
    } catch (Exception e) {
      view.displayMessage(e.getMessage());
    }

  }

  private void getTotalValue() {
    String date = dateHandler("");
    try {
      float value = model.getTotalValue(date, ApiType.ALPHA_VANTAGE);
      view.displayValue(value, date);
    } catch (IllegalStateException e) {
      view.displayMessage(e.getMessage());
      mainMenu();
    } catch (Exception e) {
      view.displayMessage(e.getMessage());
      getTotalValue();
    }

  }

  private void getComposition() {
    try {
      view.displayComposition(model.getComposition());
    } catch (Exception e) {
      view.displayMessage(e.getMessage());
      mainMenu();
    }

  }

  private void createFlexiblePortfolio(String portfolioName) {
    try {
      model.createPortfolio(portfolioName, true);
      buySellStock(true);
    } catch (Exception e) {
      view.displayMessage(e.getMessage());
      mainMenu();
    }
  }


  private String symbolHandler() {
    view.askForStockSymbol();
    String symbol = in.next();
    if (ApiCallImpl.validSymbol(symbol)) {
      return symbol;

    }
    view.displayMessage("Invalid symbol");
    return symbolHandler();
  }

  private void buySellStockHelper(String symbol, int shares, float transactionCost, String date,
                                  boolean buying) {
    try {
      if (buying) {
        model.buyStock(symbol, date, shares, transactionCost);
        view.displayMessage("Successfully bought stocks");
      } else {
        model.sellStock(symbol, date, shares, transactionCost);
        view.displayMessage("Successfully sold stocks");
      }

      flexiblePortfolioOptions();

    } catch (Exception e) {
      view.displayMessage(e.getMessage());
      flexiblePortfolioOptions();
    }


  }

  private void buySellStock(boolean buying) {

    String symbol = symbolHandler();
    if (!buying && !model.stockExists(symbol)) {
      view.displayMessage("Stock doesn't exist in portfolio");
      flexiblePortfolioOptions();
      return;
    }
    float transactionCost = floatHandler("Transaction Cost");
    String date = dateHandler("");
    int shares = sharesHandler();
    buySellStockHelper(symbol, shares, transactionCost, date, buying);


  }

  private void flexiblePortfolioOptions() {
    view.displayFlexibleMenu();
    String portfolioName = "";
    if (isOptionInvalid(11)) {
      flexiblePortfolioOptions();
      return;
    }
    if (option < 3) {
      view.askForPortfolioName();
      try {
        portfolioName = in.next();
      } catch (Exception e) {
        view.displayMessage(e.getMessage());
        flexiblePortfolioOptions();
        return;
      }
    }

    if (option > 2 && option < 11) {
      if (!model.portfolioExists()) {
        view.displayMessage("You should create at least one portfolio");
        flexiblePortfolioOptions();
        return;
      }
    }

    switch (option) {
      case 1:
        createPortfolio(portfolioName);
        break;
      case 2:
        loadPortfolio(portfolioName);
        break;
      case 3:
        buySellStock(true);
        break;
      case 4:
        buySellStock(false);
        break;
      case 5:
        viewListOfPortfolios();
        break;
      case 6:
        getCostBasis();
        break;
      case 7:
        getFlexibleComposition();
        break;
      case 8:
        getTotalValue();
        break;
      case 9:
        getPlot();
        break;
      case 10:
        save();
        break;
      case 11:
        quit = true;
        break;

      default:
        //Invalid option

    }
  }

  private void viewListOfPortfolios() {
    view.displayListOfPortfolios(model.getListOfPortfolios());
  }

  private void getCostBasis() {
    String date = dateHandler("");
    try {
      view.displayCostBasis(date, model.getCostBasis(date, ApiType.ALPHA_VANTAGE));
    } catch (IllegalStateException e) {
      view.displayMessage(e.getMessage());
      flexiblePortfolioOptions();
    } catch (Exception e) {
      view.displayMessage(e.getMessage());
      getCostBasis();
    }
  }

  private void getPlot() {
    String startDate = dateHandler("start");
    String endDate = dateHandler("end");
    try {
      view.displayComposition(model.getPlot(startDate, endDate, ApiType.ALPHA_VANTAGE));
    } catch (IllegalStateException e) {
      view.displayMessage(e.getMessage());
      flexiblePortfolioOptions();
    } catch (Exception e) {
      view.displayMessage(e.getMessage());
      getPlot();
    }
  }

  private void getFlexibleComposition() {
    String date = dateHandler("");
    try {
      view.displayComposition(model.getComposition(date));
    } catch (IllegalStateException e) {
      view.displayMessage(e.getMessage());
      flexiblePortfolioOptions();
    } catch (Exception e) {
      view.displayMessage(e.getMessage());
      getFlexibleComposition();
    }
  }

  private void createPortfolio(String portfolioName) {
    try {
      if (model.portfolioExists(portfolioName)) {
        view.displayMessage("Portfolio already exists");
        return;
      }
    } catch (IllegalArgumentException e) {
      view.displayMessage(e.getMessage());
      return;
    }

    view.displayPortfolioTypesMenu();
    if (isOptionInvalid(2)) {
      createPortfolio(portfolioName);
      return;
    }
    if (option == 1) {
      createFlexiblePortfolio(portfolioName);
    } else {
      createRigidPortfolio(portfolioName);
    }


  }

  private void createRigidPortfolio(String portfolioName) {
    try {
      model.createPortfolio(portfolioName, false);
      addAStock();
    } catch (Exception e) {
      view.displayMessage(e.getMessage());
      mainMenu();
    }
  }


  private int sharesHandler() {
    view.askForShares();
    if (in.hasNextInt()) {
      int shares = in.nextInt();
      if (shares > 0) {
        return shares;
      }
    } else {
      in.next();
    }
    view.displayMessage("Shares must be a valid positive integer");
    return sharesHandler();
  }

  private void addAStock() {

    String symbol = symbolHandler();
    int shares = sharesHandler();
    try {
      model.addStockToPortfolio(symbol, shares);
      addNewStock();
    } catch (Exception e) {
      view.displayMessage(e.getMessage());
      addAStock();
    }

  }

  private void addNewStock() {
    view.displayAddNewStockMenu();
    if (isOptionInvalid(2)) {
      addNewStock();
    }
    if (option == 1) {
      addAStock();
    } else if (option == 2) {
      mainMenu();
    }
  }
}





