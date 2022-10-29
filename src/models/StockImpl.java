package models;


import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoField;


/**
 * This class represents a stock. Each stock contains the symbol(company) , number of shares held in
 * that company and the price at which each share of this stock was bought.
 */
class StockImpl implements Stock {
  final float numberOfShares;
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
    this.symbol = symbol;
    this.numberOfShares = numberOfShares;
    this.date = this.dateHandling();

  }

  StockImpl(String symbol, float numberOfShares, LocalDate date) throws IllegalArgumentException {
    //Throw Exception if symbol is invalid
    this.symbol = symbol;
    this.numberOfShares = numberOfShares;
    if (this.weekendValidation(date)) {
      throw new IllegalArgumentException("Market is closed on weekend, date passed is " + date);
    }
    this.date = date;
  }

  public LocalDate getDate() {
    return this.date;
  }

  @Override
  public float getShares() {
    return this.numberOfShares;
  }

  @Override
  public float getValue(LocalDate date) {
    //return this.numberOfShares * getValueOfSingleShare(date);
    return 100;

  }
  @Override
  public float getValue() throws IOException {
    return this.numberOfShares * getValueOfSingleShare(this.date);

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

  private float getValueOfSingleShare(LocalDate date) throws IOException,IllegalArgumentException{
    //Call api and return the value of a single share on that date.
    if(this.weekendValidation(date)){
      throw new IllegalArgumentException("Market is closed on weekend, date passed is " + date);
    }
    LocalDate dateNow = LocalDate.now();
    if(dateNow.compareTo(date)<0){
      throw new IllegalArgumentException("Date should less than today's date");
    }
    else if(dateNow.compareTo(date)==0){
      date = dateHandling();
    }
    float result = ApiCallImpl.getData(this.symbol, date);
    System.out.println(result);
    return result;
  }

  public String toString() {
    return this.symbol + " " + this.numberOfShares + " " + this.date;
  }

  public boolean weekendValidation(LocalDate date) {
    DayOfWeek day = DayOfWeek.of(date.get(ChronoField.DAY_OF_WEEK));
    return day == DayOfWeek.SUNDAY || day == DayOfWeek.SATURDAY;
  }
}
