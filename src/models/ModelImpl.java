package models;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;

public class ModelImpl implements Model{
  private User user;
  @Override
  public void createUser(String userName, float balance) throws IOException {
    this.user = new UserImpl(userName,balance);
  }

  @Override
  public void loadUser(String userName) throws IOException, ParseException {

    this.user = Loader.loadFile(userName);

  }



  @Override
  public void createPortfolio(String portfolioName) {

    this.user.createPortfolio(portfolioName);
  }

  @Override
  public float getTotalValue(String date) {
    return this.user.getTotalValue(date);
  }

  @Override
  public StringBuilder getComposition() {
    return this.user.getComposition();
  }

  @Override
  public void addStockToPortfolio( String symbol, int shares){
    this.user.addStockToPortfolio(symbol,shares);
  }

  @Override
  public void loadPortfolio(String portfolioName) {
    this.user.loadPortfolio(portfolioName);
  }

  @Override
  public void save() throws IOException {
    this.user.save();
  }

}
