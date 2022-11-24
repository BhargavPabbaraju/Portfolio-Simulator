package controller;

public interface Features {
  void createPortfolio(String portfolioName);
  void buyStocks(int numberOfShares , String date , float commissionFee);
  void sellStocks(float numberOfShares , String date, float commissionFee);
  float getCostBasis(String date);
  float getTotalValue(String date);
  void save();
  void loadPortfolio(String portfolioName);

  //Invest amount on specific date by specifying weights
  void investOnDate(String date, float amount , float commissionFee,String symbol,float weight);

  //Dollar cost averaging
  void dollarCost(String startDate,String endDate,int interval, float amount , float commissionFee,
                  String symbol,float weight);

  void addStock(String symbol,float weight);

  void createUser(String userName,float balance);

  void loadUser(String userName);

}
