package models;

import java.time.LocalDate;
import java.util.HashMap;

/**
 * This class represents a flexible stocks list. A flexible stocks list has a hashmap with dates
 * as keys and flexible stocks as values.
 */
public class FlexibleStocksListImpl implements FlexibleStocksList {
  float transactionCost = 10;
  HashMap<LocalDate, FlexibleStock> stocksList;// {2022-11-18:{Stock},2022-10-21:{Stock}}

  float currentShares;

  public FlexibleStocksListImpl(String symbol, LocalDate date, float numberOfShares) {
    if(this.stocksList==null){
      this.stocksList = new HashMap<>();
      this.currentShares = 0;
    }
    this.stocksList.put(date,new FlexibleStockImpl(symbol,date,numberOfShares,transactionCost));
    this.currentShares+=numberOfShares;

  }

  @Override
  public void buyStock(String symbol, LocalDate date, float numberOfShares) {
    FlexibleStock stock = new FlexibleStockImpl(symbol, date, numberOfShares,
            this.transactionCost);
    stocksList.put(date, stock);

    currentShares+= numberOfShares;
  }

  @Override
  public void sellStock(String symbol, LocalDate date, float numberOfShares) {
    if(currentShares>=numberOfShares){
      FlexibleStock stock = new FlexibleStockImpl(symbol, date, -numberOfShares,
              this.transactionCost);
      stocksList.put(date, stock);
      currentShares-= numberOfShares;
    }else{
      throw new IllegalArgumentException("You have only "+currentShares+" of "+symbol
              +" after the last transaction");
    }

  }

  @Override
  public StringBuilder getPlot(LocalDate startDate, LocalDate endDate) {
    return null;
  }

  @Override
  public float getValue(LocalDate date) {
    return 0;
  }

  @Override
  public float getComposition(LocalDate date) {
    float shares = 0;
    for (LocalDate key : stocksList.keySet()) {
        if(key.compareTo(date)<=0){
          shares+=this.stocksList.get(key).getShares();
        }

    }
    return shares;

  }

  @Override
  public float getCostBasis(LocalDate date) {
    return 0;
  }
}
