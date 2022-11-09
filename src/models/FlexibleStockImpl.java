package models;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;

public class FlexibleStockImpl implements FlexibleStock {
  LocalDate date;
  String symbol;
  float numberOfShares;
  float transactionCost;

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
  public StringBuilder getPlot(LocalDate startDate, LocalDate endDate) {
    return null;
  }

  @Override
  public float getValue(LocalDate date) {
    float value =0;
    value = ApiCallImpl.getData(symbol,date);
    return (value*numberOfShares)-transactionCost;
  }


  @Override
  public float getCostBasis(LocalDate date) {
    float cost = 0;
    if (numberOfShares <= 0) {
      return cost;
    } else {
      cost = ApiCallImpl.getData(symbol, this.date);
      return (cost * numberOfShares) + transactionCost;
    }
  }

  @Override
  public float getShares() {
    return this.numberOfShares;
  }

  private boolean weekendValidation(LocalDate date) {
    DayOfWeek day = DayOfWeek.of(date.get(ChronoField.DAY_OF_WEEK));
    return day == DayOfWeek.SUNDAY || day == DayOfWeek.SATURDAY;
  }
}
