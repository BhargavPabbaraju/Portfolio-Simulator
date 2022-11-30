package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import customDataType.PlotPair;

/**
 * This class represents a general portfolio. An abstract portfolio can be either an Inflexible
 * portfolio or a flexible portfolio.
 */
public abstract class AbstractPortfolio implements Portfolio, FlexiblePortfolio{

  protected boolean flexible = false;

  protected void setFlexible(boolean flexible) {
    this.flexible = flexible;
  }

  @Override
  public void buyStock(String symbol, LocalDate date, float numberOfShares, float transactionCost) {
    //throw exception for inflexible
  }

  @Override
  public void sellStock(String symbol, LocalDate date, float numberOfShares,
                        float transactionCost) {
    //throw exception for inflexible
  }

  @Override
  public StringBuilder getPlot(LocalDate startDate, LocalDate endDate, ApiType apiType) {
    //throw exception for inflexible
    return null;
  }

  @Override
  public StringBuilder getComposition() {
    return null;
  }

  @Override
  public StringBuilder getComposition(LocalDate date) {
    return null;
  }

  @Override
  public float getCostBasis(LocalDate date, ApiType apiType) {
    //throw exception for inflexible
    return 0;
  }

  @Override
  public void createStock(String symbol, float numberOfShares) throws IllegalArgumentException {
    //throw exception for flexible
  }

  @Override
  public void createStock(String symbol, float numberOfShares, LocalDate date) {
    //throw exception for flexible
  }

  @Override
  public abstract float getTotalValue(String date, ApiType apiType);


  @Override
  public void addStock(String symbol, float numberOfShares, LocalDate dateBought) {
    ////throw exception for flexible
  }

  @Override
  public HashMap<String, Stock> getStocks() {
    //throw exception for flexible
    return null;
  }


  @Override
  public HashMap<String, FlexibleStocksList> getStocksList() {
    //throw exception for inflexible
    return null;
  }

  @Override
  public boolean stockExists(String symbol) {
    //Throw exception for inflexible
    return false;
  }
  @Override
  public void investIntoPortfolio(LocalDate date, float amount, float transactionCost, HashMap<String, Float> stocks,ApiType apiType) {

  }

  @Override
  public void createDollarCostStrategyPortfolio(LocalDate startDate, LocalDate endDate, int interval, float amount, float transactionCost, HashMap<String, Float> stocks) {

  }

  @Override
  public String getName(){
    return null;
  }

  @Override
  public PlotPair newGetPlot(LocalDate startDate, LocalDate endDate, ApiType apiType,
                             int maximumPlots){

    return null;
  }

  public ArrayList<HigherLevelStrategy> getDollarCostStocks() {
    return null;
  }
}
