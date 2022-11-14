package controller;

import java.io.InputStream;

import java.util.Scanner;


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


  private void createUser(String userName) {
    try {
      view.askForBalance();
      if (in.hasNextFloat()) {
        float balance = in.nextFloat();
        model.createUser(userName, balance);
        view.displayMessage("User successfully created");
      } else {
        in.next();
        throw new IllegalArgumentException("Balance must be a valid floating point number");

      }

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
      invalid = true;
    }

    if (invalid) {
      in.next();
      view.displayMessage("Option must be one of the following numbers");

    }
    return invalid;
  }

  @Override
  public void goController() {
    initialMenu();
    while (!quit) {
      if(model.isFlexiblePortfolio()){
        flexiblePortfolioOptions();
      }else{
        mainMenu();
      }
    }
  }

  private void mainMenu() {
    view.displayMainMenu();

    String portfolioName = "";
    if (isOptionInvalid(6)) {
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
        save();
        break;
      case 6:
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
    view.askForDate();
    try {
      String date = in.next();
      float value = model.getTotalValue(date);
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

  private void createFlexiblePortfolio(String portfolioName){
    try {
      model.createPortfolio(portfolioName,true);
      buySellStock(true);
    } catch (Exception e) {
      view.displayMessage(e.getMessage());
      mainMenu();
    }
  }

  private void buySellStock(boolean buying){
    view.askForDate();
    try {
      String date = in.next();
      view.askForStockSymbol();
      String symbol = in.next();
      view.askForShares();
      if (in.hasNextInt()) {
        try {
          int shares = in.nextInt();
          view.askForTransactionCost();
          if(in.hasNextFloat()){
            float transactionCost = in.nextFloat();
            if(buying){
              model.buyStock(symbol, date,shares,transactionCost);
            }else{
              model.sellStock(symbol,date,shares,transactionCost);
            }
            flexiblePortfolioOptions();
          }else{
            in.next();
            view.displayMessage("Transaction cost must be a valid positive floating point number");
            buySellStock(buying);
          }
        } catch (Exception e) {
          view.displayMessage(e.getMessage());
          buySellStock(buying);
        }

      } else {
        in.next();
        view.displayMessage("Shares must be a valid positive integer");
        buySellStock(buying);
      }

    } catch (IllegalStateException e) {
      view.displayMessage(e.getMessage());
      flexiblePortfolioOptions();
    } catch (Exception e) {
      view.displayMessage(e.getMessage());
      buySellStock(buying);
    }


  }

  private void flexiblePortfolioOptions() {
    view.displayFlexibleMenu();
    String portfolioName = "";
    if (isOptionInvalid(10)) {
      flexiblePortfolioOptions();
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

    switch(option){
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
        getCostBasis();
        break;
      case 6:
        getFlexibleComposition();
        break;
      case 7:
        getTotalValue();
        break;
      case 8:
        getPlot();
        break;
      case 9:
        save();
        break;
      case 10:
        quit = true;
        break;

    }
  }

  private void getCostBasis() {
    view.askForDate();
    try {
      String date = in.next();
      view.displayCostBasis(date,model.getCostBasis(date));
    } catch (IllegalStateException e) {
      view.displayMessage(e.getMessage());
      flexiblePortfolioOptions();
    } catch (Exception e) {
      view.displayMessage(e.getMessage());
      getCostBasis();
    }
  }

  private void getPlot() {
    view.askForStartDate();
    try {
      String startDate = in.next();
      view.askForEndDate();
      String endDate = in.next();
      view.displayComposition(model.getPlot(startDate,endDate));
    } catch (IllegalStateException e) {
      view.displayMessage(e.getMessage());
      flexiblePortfolioOptions();
    } catch (Exception e) {
      view.displayMessage(e.getMessage());
      getFlexibleComposition();
    }
  }

  private void getFlexibleComposition() {
    view.askForDate();
    try {
      String date = in.next();
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
    view.displayPortfolioTypesMenu();
    if(isOptionInvalid(2)){
      createPortfolio(portfolioName);
    }
    if(option==1){
      createFlexiblePortfolio(portfolioName);
    }else{
      createRigidPortfolio(portfolioName);
    }


  }

  private void createRigidPortfolio(String portfolioName) {
    try {
      model.createPortfolio(portfolioName,false);
      addAStock();
    } catch (Exception e) {
      view.displayMessage(e.getMessage());
      mainMenu();
    }
  }

  private void addAStock() {
    view.askForStockSymbol();
    String symbol = in.next();
    view.askForShares();
    if (in.hasNextInt()) {
      try {
        int shares = in.nextInt();
          model.addStockToPortfolio(symbol, shares);
          addNewStock();

      } catch (Exception e) {
        view.displayMessage(e.getMessage());
        addAStock();
      }

    } else {
      in.next();
      view.displayMessage("Shares must be a valid positive integer");
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





