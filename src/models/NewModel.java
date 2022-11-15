package models;

import java.time.LocalDate;

public interface NewModel extends Model {

  void buyStock(String symbol, String date, float numberOfShares, float transactionCost);

  void sellStock(String symbol, String date, float numberOfShares, float transactionCost);

  StringBuilder getPlot(String startDate, String endDate);

  float getTotalValue(String date);

  StringBuilder getComposition(String date);

  float getCostBasis(String date);

  boolean isFlexiblePortfolio();

  boolean userExists(String userName);

  boolean stockExists(String symbol);

  boolean portfolioExists(String portfolioName);

  boolean portfolioExists();

  StringBuilder getListOfPortfolios();

  public boolean isValidDate(String date);

}
