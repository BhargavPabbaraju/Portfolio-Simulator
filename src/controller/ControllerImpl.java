package controller;

import java.io.InputStream;

import java.util.Scanner;

import models.Model;
import view.View;

public class ControllerImpl implements Controller {
  private Model model;
  private View view;
  private Scanner in;

  private int option;
  private boolean quit;

  public ControllerImpl(Model model, View view, InputStream in) {
    this.model = model;
    this.view = view;
    this.in = new Scanner(in);
    this.option = 1;
    this.quit = false;
  }


  private void createUser(String userName) {
    try {
      view.askForBalance();
      float balance = in.nextFloat();
      model.createUser(userName, balance);
      view.displayMessage("User successfully created");

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
    }

  }

  private void initialMenu() {
    view.displayInitialMenu();
    option = in.nextInt();
    view.askForUsername();
    String userName = in.next();
    switch (option) {
      case 1:
        createUser(userName);
        break;
      case 2:
        loadUser(userName);
        break;
    }

  }

  public void go() {
    initialMenu();
    while (!quit) {
      mainMenu();
    }
  }

  private void mainMenu() {
    view.displayMainMenu();
    option = in.nextInt();

    String portfolioName = "";
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
    }
  }

  private void save() {
    try{
      model.save();
      view.displayMessage("Successfully saved.");
    }catch(Exception e){
      view.displayMessage(e.getMessage());
      mainMenu();
    }

  }

  private void loadPortfolio(String portfolioName) {
    try{
      model.loadPortfolio(portfolioName);
      view.displayMessage("Loaded "+portfolioName);
    }catch(Exception e){
      view.displayMessage(e.getMessage());
    }

  }

  private void getTotalValue() {
    view.askForDate();
    String date = in.next();
    float value = model.getTotalValue(date);
    view.displayValue(value, date);
  }

  private void getComposition() {
    view.displayComposition(model.getComposition());
  }

  private void createPortfolio(String portfolioName) {
    try{
      model.createPortfolio(portfolioName);
      addNewStock();
    }catch(Exception e){
      view.displayMessage(e.getMessage());
    }

  }

  private void addNewStock() {
    view.askForStockSymbol();
    String symbol = in.next();
    view.askForShares();
    int shares = in.nextInt();
    try {
      model.addStockToPortfolio(symbol, shares);
      view.displayAddNewStockMenu();
      option = in.nextInt();
      switch (option) {
        case 1:
          addNewStock();
          break;
        case 2:
          mainMenu();
          break;
      }
    } catch (Exception e) {
      view.displayMessage(e.getMessage());
      addNewStock();
    }

  }


}
