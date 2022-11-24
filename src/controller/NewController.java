package controller;

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

  }

  @Override
  public void buyStocks(int numberOfShares, String date, float commissionFee) {

  }

  @Override
  public void sellStocks(float numberOfShares, String date, float commissionFee) {

  }

  @Override
  public float getCostBasis(String date) {
    return 0;
  }

  @Override
  public float getTotalValue(String date) {
    return 0;
  }

  @Override
  public void save() {

  }

  @Override
  public void loadPortfolio(String portfolioName) {

  }

  @Override
  public void investOnDate(String date, float amount, float commissionFee, String symbol,
                           float weight) {

  }

  @Override
  public void dollarCost(String startDate, String endDate, int interval, float amount,
                         float commissionFee, String symbol, float weight) {

  }



  @Override
  public void addStock(String symbol, float weight) {

  }

  @Override
  public void createUser(String userName, float balance) {
    try{
      model.createUser(userName,balance);
      view.showMainMenu();
      view.showMessage("Successfully created user",false);
    }catch(Exception e){
      view.showInitialMenu();
      view.showMessage(e.getMessage(),true);
    }

  }

  @Override
  public void loadUser(String userName) {
    try{
      model.loadUser(userName);
      view.showMainMenu();
      view.showMessage("Successfully loaded "+userName,false);
    }catch(Exception e){
      view.showInitialMenu();
      String message = e.getMessage()==null  ? "Load file is in invalid format" : e.getMessage();
      view.showMessage(message,true);
    }


  }
}
