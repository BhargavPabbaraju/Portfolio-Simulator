package models;


import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;


/**
 * This class represents a stock. Each stock contains the symbol(company) , number of shares held in
 * that company and the price at which each share of this stock was bought.
 */
class StockImpl implements Stock {
  float numberOfShares;
  final LocalDate date;
  final String symbol;

  // Price of Single share

  /**
   * @param symbol         The symbol represents the company.
   * @param numberOfShares The number of shares the user has in that company.
   * @throws IllegalArgumentException if symbol is invalid or if numberOfShares is negative.
   */
  StockImpl(String symbol, float numberOfShares) throws IllegalArgumentException {
    //Throw Exception if symbol is invalid
    if (this.symbolvalidation(symbol)) {
      this.symbol = symbol;
    } else {
      throw new IllegalArgumentException(symbol+" Symbol not found");
    }
    this.numberOfShares = numberOfShares;
    this.date = this.dateHandling();

  }

  StockImpl(String symbol, float numberOfShares, LocalDate date) throws IllegalArgumentException {
    if (this.symbolvalidation(symbol)) {
      this.symbol = symbol;
    } else {
      throw new IllegalArgumentException(symbol+" Symbol not found");
    }
    this.numberOfShares = numberOfShares;
    if (this.weekendValidation(date)) {
      throw new IllegalArgumentException("Market is closed on weekend, date passed is " + date);
    }
    this.date = date;
  }

  private boolean symbolvalidation(String symbol) {
    if (ApiCallImpl.validSymbol(symbol)) {
      return true;
    } else {
      return false;
    }

  }

  public LocalDate getDate() {
    return this.date;
  }

  @Override
  public float getShares() {
    return this.numberOfShares;
  }

  @Override
  public float getValue(String date) {
    return this.numberOfShares * getValueOfSingleShare(date);
    //return 100;

  }

  @Override
  public float getValue() throws IOException {
    return this.numberOfShares * getValueOfSingleShare(this.date.toString());

  }

  @Override
  public void addToShares(float number) {
    this.numberOfShares+=number;
  }

  private LocalDate dateHandling() {
    LocalDate dateNow = LocalDate.now();
    LocalTime timenow = LocalTime.now();
    LocalTime timepm = LocalTime.of(20, 2, 50, 0);
    if (timenow.compareTo(timepm) > 0) {
    } else {
      dateNow = dateNow.minusDays(1);
    }
    return shiftingDate(dateNow);
  }

  private LocalDate shiftingDate(LocalDate dateNow) {
    if (!this.weekendValidation(dateNow)) {
      return dateNow;
    } else {
      LocalDate dateprev = dateNow.minusDays(1);
      if (this.weekendValidation(dateprev)) {
        dateprev = dateprev.minusDays(1);
      }
      return dateprev;
    }
  }

  private float getValueOfSingleShare(String date) throws IllegalArgumentException {
    //Call api and return the value of a single share on that date.
    LocalDate dateString;
    try {
      dateString = LocalDate.parse(date,
              DateTimeFormatter.ISO_LOCAL_DATE);
    }catch (IllegalStateException e){
      throw new IllegalArgumentException("Date Should be in yyyy-mm-dd format");
    }
    if (this.weekendValidation(dateString)) {
      throw new IllegalArgumentException("Market is closed on weekend, date passed is " + date);
    }
    LocalDate dateNow = LocalDate.now();
    if (dateNow.compareTo(dateString) < 0) {
      throw new IllegalArgumentException("Date should be less than today's date");
    } else if (dateNow.compareTo(dateString) == 0) {
      dateString = dateHandling();
    }
    return ApiCallImpl.getData(this.symbol, dateString);
  }

  public String toString() {
    return this.symbol + " " + this.numberOfShares + " " + this.date;
  }

  public boolean weekendValidation(LocalDate date) {
    DayOfWeek day = DayOfWeek.of(date.get(ChronoField.DAY_OF_WEEK));
    return day == DayOfWeek.SUNDAY || day == DayOfWeek.SATURDAY;
  }
}
