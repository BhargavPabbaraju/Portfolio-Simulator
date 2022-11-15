package models;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * This class represents an implementation of model. It has a user field and delegates all the
 * methods to this user.
 */
public class ModelImpl implements Model {
  protected User user;



  @Override
  public void createUser(String userName, float balance) {
    this.user = new UserImpl(userName, balance);
  }

  @Override
  public void loadUser(String userName) throws IOException, ParseException {
    try {
      this.user = Loader.loadFile(userName);
    } catch (Exception e) {
      if (e.getMessage().equals( "User doesn't exists")) {
        throw e;
      }
      throw new IllegalStateException("Load file is not in valid format.");
    }

  }


  @Override
  public void createPortfolio(String portfolioName,boolean isFlexible) {
    this.user.createPortfolio(portfolioName,isFlexible);
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
  public void addStockToPortfolio(String symbol, int shares) {
    this.user.addStockToPortfolio(symbol, shares);
  }

  @Override
  public void loadPortfolio(String portfolioName) {
    this.user.loadPortfolio(portfolioName);
  }

  @Override
  public void save() throws IOException {
    this.user.save();
  }

  @Override
  public String toString() {
    return this.user.toString();
  }

}
