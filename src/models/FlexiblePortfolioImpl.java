package models;

import java.time.LocalDate;
import java.util.HashMap;

/**
 * This class represents a flexible portfolio. A flexible portfolio supports buying and selling
 * of shares on various dates provided the selling operations are consistent with previous
 * transactions. A flexible portfolio has a hashmap where symbols are the keys and flexible stocks
 * are the values.
 */

public class FlexiblePortfolioImpl extends AbstractPortfolio implements FlexiblePortfolio {
  String portfolioName;
  HashMap<String,FlexibleStocksList> stockList;// {AAPl:{Stocks},IBM:{Stocks}}
  User user;

  FlexiblePortfolioImpl(String portfolioName, User user) throws IllegalArgumentException {
    if (Loader.isInvalidName(portfolioName)) {
      throw new IllegalArgumentException(
              "Portfolio name must not contain any of \\\"{}[],: characters.");
    }
    this.portfolioName = portfolioName;
    this.user = user;
    this.stockList = new HashMap<>();

  }

  
  @Override
  public void buyStock(String symbol, LocalDate date, float numberOfShares) {
    if(this.stockList.containsKey(symbol)){
      this.stockList.get(symbol).buyStock(symbol,date,numberOfShares);
    }else{
      FlexibleStocksList list = new FlexibleStocksListImpl(symbol,date,numberOfShares);
      this.stockList.put(symbol,list);
    }

  }

  @Override
  public void sellStock(String symbol, LocalDate date, float numberOfShares) {
    this.stockList.get(symbol).sellStock(symbol,date,numberOfShares);
  }

  @Override
  public StringBuilder getPlot(LocalDate startDate, LocalDate endDate) {
    return null;
  }

  @Override
  public float getTotalValue(String date) {
    return 0;
  }


  @Override
  public StringBuilder getComposition(LocalDate date) {
    StringBuilder composition = new StringBuilder();
    String format = "%-40s%.2f%n";
    composition.append(String.format("%-20sComposition of %s upto %s%n", "",
            portfolioName,date.toString()));

    for (String key : stockList.keySet()) {
      composition.append(String.format(format, key, stockList.get(key).getComposition(date)));

    }
    return composition;
  }

  @Override
  public float getCostBasis(LocalDate date) {
    return 0;
  }
}
