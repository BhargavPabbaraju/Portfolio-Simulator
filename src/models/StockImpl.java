package models;

/**
 * This class represents a stock. Each stock contains the symbol(company) , number of shares held in
 * that company and the price at which each share of this stock was bought.
 */
class StockImpl implements Stock {
  final float numberOfShares;
  final String date;
  final String symbol;

  final ApiCall api = new ApiCallImpl();

  final float costPrice; // Price of Single share

  /**
   * @param symbol         The symbol represents the company.
   * @param numberOfShares The number of shares the user has in that company.
   * @throws IllegalArgumentException if symbol is invalid or if numberOfShares is negative.
   */
  StockImpl(String symbol, float numberOfShares) throws IllegalArgumentException {
    //Throw Exception if symbol is invalid
    this.symbol = symbol;
    this.numberOfShares = numberOfShares;
    this.date = "2022-10-25";
    this.costPrice=100.0F;
  }

  StockImpl(String symbol, float numberOfShares, String date, float costPrice) throws IllegalArgumentException {
    //Throw Exception if symbol is invalid
    this.symbol = symbol;
    this.numberOfShares = numberOfShares;
    this.date = date;
    this.costPrice = 100.00F;
  }

  public String getDate() {
    return this.date;
  }

  @Override
  public float getCostPrice() {
    return this.costPrice;
  }
  @Override
  public float getValue(String date) {
    return this.numberOfShares * getValueOfSingleShare(date);
  }

  private float getValueOfSingleShare(String date) {
    //Call api and return the value of a single share on that date.
    return 0;
  }
}
