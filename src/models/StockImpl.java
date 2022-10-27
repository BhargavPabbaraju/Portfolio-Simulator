package models;


import java.time.LocalDate;


/**
 * This class represents a stock. Each stock contains the symbol(company) , number of shares held in
 * that company and the price at which each share of this stock was bought.
 */
class StockImpl implements Stock {
  final float numberOfShares;
  final LocalDate date;
  final String symbol;

  final ApiCall api = new ApiCallImpl();
  // Price of Single share

  /**
   * @param symbol         The symbol represents the company.
   * @param numberOfShares The number of shares the user has in that company.
   * @throws IllegalArgumentException if symbol is invalid or if numberOfShares is negative.
   */
  StockImpl(String symbol, float numberOfShares) throws IllegalArgumentException {
    //Throw Exception if symbol is invalid
    this.symbol = symbol;
    this.numberOfShares = numberOfShares;
    this.date = LocalDate.now();
  }

  StockImpl(String symbol, float numberOfShares, LocalDate date) throws IllegalArgumentException {
    //Throw Exception if symbol is invalid
    this.symbol = symbol;
    this.numberOfShares = numberOfShares;
    this.date = date;
  }

  public LocalDate getDate() {
    return this.date;
  }

  @Override
  public float getValue(String date) {
    return this.numberOfShares * getValueOfSingleShare(date);
  }

  private float getValueOfSingleShare(String date) {
    //Call api and return the value of a single share on that date.
    return 0;
  }

  public String toString(){
    return this.symbol +" "+this.numberOfShares+" "+this.date;
  }
}
