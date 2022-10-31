package controller;

import java.io.InputStream;

import java.util.Scanner;

import models.Model;
import view.View;

public class ControllerImpl implements Controller {
  private final Model model;
  private final View view;
  private final Scanner in;

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
      if(in.hasNextFloat()){
        float balance = in.nextFloat();
        model.createUser(userName, balance);
        view.displayMessage("User successfully created");
      }else{
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

        if(isOptionInvalid(2)){

          initialMenu();
        }
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


  private boolean isOptionInvalid(int numberOfOptions){
    boolean invalid = false;
    if(in.hasNextInt()){
      option = in.nextInt();
      if(option<1 || option>numberOfOptions) {
        invalid = true;
      }

    }else{
      invalid = true;
    }

    if(invalid){
      in.next();
      view.displayMessage("Option must be one of the following numbers");

    }
    return invalid;
  }



  public void go() {
    initialMenu();
    while (!quit) {
      mainMenu();
    }
  }

  private void mainMenu() {
    view.displayMainMenu();

    String portfolioName = "";
    if(isOptionInvalid(6)){
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
    try{
      String date = in.next();
      float value = model.getTotalValue(date);
      view.displayValue(value, date);
    }catch(IllegalStateException e){
      view.displayMessage(e.getMessage());
      mainMenu();
    } catch(Exception e){
      view.displayMessage(e.getMessage());
      getTotalValue();
    }

  }

  private void getComposition() {
    try{
      view.displayComposition(model.getComposition());
    }catch(Exception e){
      view.displayMessage(e.getMessage());
      mainMenu();
    }

  }

  private void createPortfolio(String portfolioName) {
    try{
      model.createPortfolio(portfolioName);
      addAStock();
    }catch(Exception e){
      view.displayMessage(e.getMessage());
      mainMenu();
    }

  }

  private void addAStock(){
    view.askForStockSymbol();
    String symbol = in.next();
    view.askForShares();
    if(in.hasNextInt()) {
      int shares = in.nextInt();
      model.addStockToPortfolio(symbol, shares);
      view.displayAddNewStockMenu();
      addNewStock();
    }else {
      in.next();
      view.displayMessage("Shares must be a valid positive integer");
      addAStock();
    }
  }

  private void addNewStock() {
    view.displayAddNewStockMenu();
    if(isOptionInvalid(2)){
      addNewStock();
    }
        switch (option) {
          case 1:
            addAStock();
            break;
          case 2:
            mainMenu();
            break;
        }
      }




  }



