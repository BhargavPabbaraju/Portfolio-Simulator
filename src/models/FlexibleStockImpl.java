package models;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;

/**
 * This class represents a stock. Each stock contains the symbol(company) , number of shares held in
 * that company, the date on which this stock was brought and the transaction associated with it.
 */
public class FlexibleStockImpl implements FlexibleStock {
  LocalDate date;
  String symbol;
  float numberOfShares;
  float transactionCost;

  /**
   * Constructs FlexibleStockImpl and initialises values and performs validations such as
   * checking if the stock symbol is valid or not.
   *
   * @param symbol          The symbol represents the company.
   * @param numberOfShares  The number of shares the user has in that company.
   * @param date            date on which this stock was brought.
   * @param transactionCost transaction associated with the stock.
   * @throws IllegalArgumentException if symbol is invalid.
   */
  FlexibleStockImpl(String symbol, LocalDate date, float numberOfShares, float transactionCost) {
    if (this.symbolvalidation(symbol)) {
      this.symbol = symbol;
    } else {
      throw new IllegalArgumentException(symbol + " Symbol not found");
    }
    if (this.weekendValidation(date)) {
      throw new IllegalArgumentException("Market is closed on weekend, date passed is " + date);
    }

    this.date = date;
    this.numberOfShares = numberOfShares;
    this.transactionCost = transactionCost;
  }

  private boolean symbolvalidation(String symbol) {
    return ApiCallImpl.validSymbol(symbol);
  }

  @Override
  public StringBuilder getPlot(LocalDate startDate, LocalDate endDate, ApiType apiType) {
    return null;
  }

  @Override
  public float getValue(LocalDate date, ApiType apiType) {
    float value = 0;
    value = ApiCallImpl.getData(symbol, date, apiType);
    return (value * numberOfShares) - transactionCost;
  }


  @Override
  public float getCostBasis(LocalDate date, ApiType apiType) {
    float cost = 0;
    if (numberOfShares <= 0) {
      return cost;
    } else {
      cost = ApiCallImpl.getData(symbol, this.date, apiType);
      return (cost * numberOfShares) + transactionCost;
    }
  }

  @Override
  public float getShares() {
    return this.numberOfShares;
  }

  @Override
  public float getTransactionCost() {
    return this.transactionCost;
  }

  private boolean weekendValidation(LocalDate date) {
    DayOfWeek day = DayOfWeek.of(date.get(ChronoField.DAY_OF_WEEK));
    return day == DayOfWeek.SUNDAY || day == DayOfWeek.SATURDAY;
  }


}
