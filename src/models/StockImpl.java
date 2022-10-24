package models;

import java.util.Date;

/**
 * This class represents a stock. Each stock contains the symbol(company) , number of shares held in
 * that company and the price at which each share of this stock was bought.
 */
class StockImpl implements Stock {
  final float numberOfShares;
  final float costPrice; // Price of Single share
  final String symbol;

  /**
   *
   * @param symbol The symbol represents the company.
   * @param numberOfShares The number of shares the user has in that company.
   * @throws IllegalArgumentException if symbol is invalid or if numberOfShares is negative.
   */
  StockImpl(String symbol, float numberOfShares) throws IllegalArgumentException{
    //Throw Exception if symbol is invalid
    this.symbol = symbol;
    this.numberOfShares = numberOfShares;
    this.costPrice = getValueOfSingleShare(new Date());
  }

  public float getCostPrice(){
    return this.costPrice;
  }


  @Override
  public float getValue(Date date) {
    return this.numberOfShares * getValueOfSingleShare(date);
  }

  private float getValueOfSingleShare(Date date){
    //Call api and return the value of a single share on that date.
    return 0;
  }
}
