package controller;

import java.util.HashMap;

public interface Features {
  void createPortfolio(String portfolioName);
  void buyStocks(String symbol, int numberOfShares , String date , float commissionFee,
                 boolean creating);
  void sellStocks(String symbol, float numberOfShares , String date, float commissionFee);
  float getCostBasis(String date);
  float getTotalValue(String date);
  void save();
  void loadPortfolio(String portfolioName);

  //Invest amount on specific date by specifying weights
  void investOnDate(String date, float amount , float commissionFee, HashMap<String,Float> stocks, boolean creating);

  //Dollar cost averagin

  void dollarCost(String startDate, String endDate, int interval, float amount,
                  float commissionFee, HashMap<String, Float> stocks, boolean creating);

  void createUser(String userName, float balance);

  void loadUser(String userName);

  String getActivePortfolio();

  String getListOfPortfolios();


}
