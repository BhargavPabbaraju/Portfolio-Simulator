package models;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * This class represents stocks created using dollar costing average strategy. In this strategy,
 * the user creates a portfolio of stocks and determines their relative proportion. Then the user
 * invests a fixed amount of money in this portfolio at regular frequency over a long period of
 * time.
 */
public class DollarCostStockImpl implements DollarCostStrategy {

  LocalDate startDate;
  LocalDate endDate = null;
  int interval;
  final HashMap<String, Float> stocks; // Symbol: Amount , AAPL:200/50%;
  float transactionCost;
  float amount;

  /**
   * This constructor creates a Dollar cost average strategy for particular stocks.
   *
   * @param startDate       LocalDate date on which he wants to start the purchase.
   * @param endDate         LocalDate date on which he wants to end the purchase.
   * @param interval        float interval in which the user wants to invest.
   * @param amount          float amount which the user wants to invest.
   * @param transactionCost float which is transaction associated for this transaction.
   * @param stocks          Hashmap of stocks which contains the Symbol as key and weight as value.
   */
  public DollarCostStockImpl(LocalDate startDate, int interval, float amount, float transactionCost, HashMap<String, Float> stocks, LocalDate endDate) {
    if (startDate.compareTo(endDate) > 0) {
      LocalDate temp = endDate;
      endDate = startDate;
      startDate = temp;
    }
    this.startDate = startDate;
    this.interval = interval;
    this.transactionCost = transactionCost;
    this.stocks = stocks;
    this.amount = amount;
    this.endDate = endDate;

  }

  @Override
  public float getCostBasis(LocalDate date) {
    long multiplier = 1;
    if (date.compareTo(this.startDate) >= 0) {
      long dayDiff = DAYS.between(this.startDate, date);
      if (dayDiff < this.interval || date.compareTo(this.startDate) == 0) {
        return this.amount + this.transactionCost;
      }
      if (this.endDate == null || date.compareTo(this.endDate) <= 0) {
        multiplier = dayDiff / this.interval;
      } else if (date.compareTo(this.endDate) > 0) {
        dayDiff = DAYS.between(this.startDate, this.endDate);
        multiplier = dayDiff / this.interval;
      }
      return this.amount + (multiplier * (this.amount + this.transactionCost));
    } else {
      return 0;
    }
  }

  @Override
  public float getTotalValue(LocalDate date, ApiType apiType) {
    ArrayList<LocalDate> datesArray = new ArrayList<>();
    if (date.compareTo(this.startDate) >= 0) {
      long dayDiff = DAYS.between(this.startDate, date);
      datesArray.add(weekendValidation(this.startDate));
      if (this.endDate == null || date.compareTo(this.endDate) <= 0) {
        long multi = dayDiff / this.interval;
        for (long i = 1; i <= multi; i++) {
          datesArray.add(weekendValidation(this.startDate.plusDays(this.interval * i)));
        }
      } else if (date.compareTo(this.endDate) > 0) {
        dayDiff = DAYS.between(this.startDate, this.endDate);
        long multi = dayDiff / this.interval;
        for (long i = 1; i <= multi; i++) {
          datesArray.add(weekendValidation(this.startDate.plusDays(this.interval * i)));
        }
      }
      HashMap<String, Float> totalShares = getTotalShares(datesArray, apiType);
      return getValue(totalShares, date, apiType);
    } else {
      return 0;
    }
  }

  @Override
  public LocalDate getStartDate() {
    return startDate;
  }

  @Override
  public LocalDate getEndDate() {
    return endDate;
  }

  @Override
  public float getAmount() {
    return amount;
  }

  @Override
  public int getInterval() {
    return interval;
  }

  @Override
  public HashMap<String, Float> getStocks() {
    return this.stocks;
  }

  @Override
  public float getTransactionCost() {
    return transactionCost;
  }

  private float getValue(HashMap<String, Float> totalShares, LocalDate date, ApiType apiType) {
    float totalValue = 0;
    for (String key : totalShares.keySet()) {
      totalValue += ApiCallImpl.getData(key, date, apiType) * totalShares.get(key);
    }
    return totalValue;
  }

  private HashMap<String, Float> getTotalShares(ArrayList<LocalDate> datesArray, ApiType apiType) {
    HashMap<String, Float> totalShares = new HashMap<>();
    for (String key : this.stocks.keySet()) {
      float shares = 0;
      for (LocalDate date : datesArray) {
        try {
          float value = ApiCallImpl.getData(key, date, apiType);
          shares += ((this.amount * this.stocks.get(key)) / 100) / value;
        } catch (IllegalArgumentException e) {
          float value = ApiCallImpl.getData(key, weekendValidation(this.startDate.plusDays(1)), apiType);
          shares += ((this.amount * this.stocks.get(key)) / 100) / value;

        }
      }
      totalShares.put(key, shares);
    }
    return totalShares;


  }

  private LocalDate weekendValidation(LocalDate date) {
    if (isWeekend(date)) {
      date = date.plusDays(1);
      return weekendValidation(date);
    }
    return date;
  }

  private boolean isWeekend(LocalDate date) {
    DayOfWeek day = date.getDayOfWeek();
    return day == DayOfWeek.SUNDAY || day == DayOfWeek.SATURDAY;
  }

  @Override
  public String toString() {
    return this.stocks.toString();
  }

}
