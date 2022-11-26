package controller;

import java.util.HashMap;

import models.ApiType;
import models.NewerModel;
import view.NewView;

public class NewController implements Features{
  private NewerModel model;
  private NewView view;

  public NewController(NewerModel model,NewView view){
    this.model = model;
    this.view = view;
    this.view.setValidator(new ValidatorImpl(model));
    this.view.setFeatures(this);

  }

  @Override
  public void createPortfolio(String portfolioName) {
    model.createPortfolio(portfolioName,true);
  }

  @Override
  public void buyStocks(String symbol, int numberOfShares, String date, float commissionFee,
                        boolean creating) {

    try{
      model.buyStock(symbol,date,numberOfShares,commissionFee);
      String message = "Successfully bought stocks";
      if(creating){
        message = "Successfully created portfolio";
      }
      view.showMainMenu();
      view.showMessage(message,false);
    }catch(Exception e){
      view.showMainMenu();
      view.showMessage(e.getMessage(),true);
    }


  }

  @Override
  public void sellStocks(String symbol, float numberOfShares, String date, float commissionFee) {
    try{
      model.sellStock(symbol,date,numberOfShares,commissionFee);
      showMainMenu("Successfully sold stocks",false);
    }catch(Exception e){
      view.showMessage(e.getMessage(),true);
    }
  }

  @Override
  public float getCostBasis(String date) {
    try{
      return model.getCostBasis(date,ApiType.ALPHA_VANTAGE);
    }catch(Exception e){
      showMainMenu(e.getMessage(),true);
      return -1;
    }

  }

  @Override
  public float getTotalValue(String date) {
    try{
      return model.getTotalValue(date,ApiType.ALPHA_VANTAGE);
    }catch(Exception e){
      showMainMenu(e.getMessage(),true);
      return -1;
    }
  }

  @Override
  public void save() {

  }

  @Override
  public void loadPortfolio(String portfolioName) {
    try{
      model.loadPortfolio(portfolioName);
      showMainMenu("Successfully loaded portfolio",false);
    }catch (Exception e){
      showMainMenu(e.getMessage(),true);
    }

  }

  private void showMainMenu(String message,boolean isError){
    view.showMainMenu();
    view.showMessage(message,isError);
  }

  @Override
  public void investOnDate(String date, float amount, float commissionFee,
                           HashMap<String, Float> stocks, boolean creating) {
    try{
      model.investIntoPortfolio(date,amount,commissionFee,stocks, ApiType.ALPHA_VANTAGE);
      String message = "Successfully invested";
      if(creating){
        message = "Successfully created portfolio";
      }
      showMainMenu(message,false);
    }catch(Exception e){
      showMainMenu(e.getMessage(),true);
    }

  }


  @Override
  public void dollarCost(String startDate, String endDate, int interval, float amount,
                         float commissionFee, HashMap<String, Float> stocks, boolean creating) {

  }





  @Override
  public void createUser(String userName, float balance) {
//    try{
//      model.createUser(userName,balance);
//      view.showNewUserMenu();
//    }catch(Exception e){
//      view.showInitialMenu();
//      view.showMessage(e.getMessage(),true);
//    }
    model.createUser(userName,balance);
      view.showNewUserMenu();

  }

  @Override
  public void loadUser(String userName) {
    try{
      model.loadUser(userName);
      showMainMenu("Successfully loaded "+userName,false);
    }catch(Exception e){
      view.showInitialMenu();
      String message = e.getMessage()==null  ? "Load file is in invalid format" : e.getMessage();
      view.showMessage(message,true);
    }


  }

  @Override
  public String getActivePortfolio() {
    return model.getActivePortfolio();
  }

  @Override
  public String getListOfPortfolios() {
    try{
      return model.getListOfPortfolios().toString();
    }catch(Exception e){
      return "";
    }
  }


}
