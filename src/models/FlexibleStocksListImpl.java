package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class represents a flexible stocks list. A flexible stocks list has a hashmap with dates
 * as keys and flexible stocks as values.
 */
public class FlexibleStocksListImpl implements FlexibleStocksList {
  HashMap<LocalDate, FlexibleStock> stocksList;// {2022-11-18:{Stock},2022-10-21:{Stock}}

  float currentShares;

  public FlexibleStocksListImpl(String symbol, LocalDate date, float numberOfShares,
                                float transactionCost) {
    if (this.stocksList == null) {
      this.stocksList = new HashMap<>();
      this.currentShares = 0;
    }
    this.stocksList.put(date, new FlexibleStockImpl(symbol, date, numberOfShares,transactionCost));
    this.currentShares += numberOfShares;

  }



  @Override
  public void buyStock(String symbol, LocalDate date, float numberOfShares,float transactionCost) {
    for (LocalDate key : stocksList.keySet()) {
      if (key.compareTo(date) == 0) {
        float shares = this.stocksList.get(key).getShares();
        numberOfShares+=shares;
      }

    }
    FlexibleStock stock = new FlexibleStockImpl(symbol, date, numberOfShares,
            transactionCost);
    stocksList.put(date, stock);

    currentShares += numberOfShares;
  }

  @Override
  public void sellStock(String symbol, LocalDate date, float numberOfShares,float transactionCost) {
    if(!checkIfStocksExists(date)){
      throw new IllegalArgumentException("You need to buy a stock before you sell");
    }

    if (currentShares >= numberOfShares) {
      FlexibleStock stock = new FlexibleStockImpl(symbol, date, -numberOfShares,
              transactionCost);
      stocksList.put(date, stock);
      currentShares -= numberOfShares;
    } else {
      throw new IllegalArgumentException("You have only " + currentShares + " of " + symbol
              + " after the last transaction");
    }

  }

  @Override
  public ArrayList<Float> getPlot(ArrayList<LocalDate> datesList) {
    ArrayList<Float> values = new ArrayList<>();
    for(LocalDate date:datesList){
      values.add(getValue(date));
    }
    return values;
  }

  @Override
  public float getValue(LocalDate date) {
    float value=0;
    for (LocalDate key : stocksList.keySet()) {
      if (date.compareTo(key) >= 0) {
        value += this.stocksList.get(key).getValue(date);
      }
    }
    return value;

  }

  @Override
  public float getComposition(LocalDate date) {
    if(!checkIfStocksExists(date)){
      return -1.0f;
    }
    float shares = 0;
    for (LocalDate key : stocksList.keySet()) {
      if (key.compareTo(date) <= 0) {
        shares += this.stocksList.get(key).getShares();
      }

    }
    return shares;

  }

  @Override
  public float getCostBasis(LocalDate date) {
    float cost = 0;
    for (LocalDate key : stocksList.keySet()) {
      if (date.compareTo(key) >= 0) {
        cost += this.stocksList.get(key).getCostBasis(date);
      }
    }
    return cost;
  }

  @Override
  public HashMap<LocalDate, FlexibleStock> getStocks() {
    return this.stocksList;
  }
  private boolean checkIfStocksExists(LocalDate date){
    int c=0;
    for (LocalDate key : stocksList.keySet()) {
      if (date.compareTo(key) >= 0 && stocksList.get(key).getShares()>0) {
        c=c+1;
      }
    }
    if(c==0){

      return false;
    }else{
      return true;
    }

  }


}
