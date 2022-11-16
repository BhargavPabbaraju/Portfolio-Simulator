package models;

import java.time.LocalDate;

/**
 * This class represents an implementation of NewModel. It extends ModelImpl created previously.
 */
public class NewModelImpl extends ModelImpl implements NewModel {

  @Override
  public void buyStock(String symbol, String date, float numberOfShares, float transactionCost) {
    if (transactionCost < 0) {
      throw new IllegalArgumentException("Transaction Cost must be a valid positive floating" +
              "point number");
    }
    this.user.buyStock(symbol, date, numberOfShares, transactionCost);
  }

  @Override
  public void sellStock(String symbol, String date, float numberOfShares, float transactionCost) {
    if (transactionCost < 0) {
      throw new IllegalArgumentException("Transaction Cost must be a valid positive floating" +
              "point number");
    }
    this.user.sellStock(symbol, date, numberOfShares, transactionCost);
  }

  @Override
  public StringBuilder getPlot(String startDate, String endDate,ApiType apiType) {
    return this.user.getPlot(startDate, endDate,apiType);
  }

  @Override
  public StringBuilder getComposition(String date) {
    return this.user.getComposition(date);
  }

  @Override
  public float getCostBasis(String date,ApiType apiType) {
    return this.user.getCostBasis(date,apiType);
  }

  @Override
  public boolean isFlexiblePortfolio() {
    return this.user.isFlexiblePortfolio();
  }

  @Override
  public boolean userExists(String userName) {
    return Loader.userNameExits(userName);
  }

  @Override
  public boolean stockExists(String symbol) {
    return this.user.stockExists(symbol);
  }

  @Override
  public boolean portfolioExists(String portfolioName) {
    if (Loader.isInvalidName(portfolioName)) {
      throw new IllegalArgumentException("Portfolio name must not contain " +
              "any of \\\"{}[],: characters.");
    }
    return user.portfolioExists(portfolioName);
  }

  @Override
  public boolean portfolioExists() {
    return this.user.portfolioExists();
  }

  @Override
  public StringBuilder getListOfPortfolios() {
    return user.getListOfPortfolios();
  }

  @Override
  public boolean isValidDate(String date) {
    return this.user.isValidDate(date);
  }
}
