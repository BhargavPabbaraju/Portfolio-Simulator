package models;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


import customDataType.PlotPair;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * This class represents a flexible portfolio. A flexible portfolio supports buying and selling
 * of shares on various dates provided the selling operations are consistent with previous
 * transactions. A flexible portfolio has a hashmap where symbols are the keys and flexible stocks
 * are the values.
 */

public class FlexiblePortfolioImpl extends AbstractPortfolio implements FlexiblePortfolio {
  String portfolioName;
  HashMap<String, FlexibleStocksList> stockList;// {AAPl:{Stocks},IBM:{Stocks}}

  ArrayList<HigherLevelStrategy> dollarCostStockList;
  User user;

  private class DatePair{
    private final ArrayList<LocalDate> dates;
    private final String pattern;

    DatePair(ArrayList<LocalDate> dates,String pattern){
      this.dates = dates;
      this.pattern = pattern;
    }
  }

  /**
   * This constructor creates a portfolio for this user.
   * Note: Portfolio name must not contain the following characters: {}[],:"\
   *
   * @param portfolioName String which is unique for each user
   * @param user          user object
   * @throws IllegalArgumentException if a user has already created a portfolio with this name.
   */
  FlexiblePortfolioImpl(String portfolioName, User user) throws IllegalArgumentException {
    if (Loader.isInvalidName(portfolioName)) {
      throw new IllegalArgumentException(
              "Portfolio name must not contain any of \\\"{}[],: characters.");
    }
    this.portfolioName = portfolioName;
    this.user = user;
    this.stockList = new HashMap<>();
    this.dollarCostStockList = new ArrayList<>();
    this.setFlexible(true);


  }


  @Override
  public void buyStock(String symbol, LocalDate date, float numberOfShares,
                       float transactionCost) {

    dateValidation(date);
    if (this.stockList.containsKey(symbol)) {
      this.stockList.get(symbol).buyStock(symbol, date, numberOfShares, transactionCost);
    } else {
      FlexibleStocksList list = new FlexibleStocksListImpl(symbol, date, numberOfShares,
              transactionCost);
      this.stockList.put(symbol, list);
    }

  }

  @Override
  public void sellStock(String symbol, LocalDate date, float numberOfShares,
                        float transactionCost) {
    dateValidation(date);
    if (this.stockList.containsKey(symbol)) {
      this.stockList.get(symbol).sellStock(symbol, date, numberOfShares, transactionCost);
    } else {
      throw new IllegalArgumentException("You cannot sell stocks which are not in your portfolio.");
    }

  }

  private ArrayList<LocalDate> dayWiseList(LocalDate startDate, LocalDate endDate,
                                           int numberOfDaysToAdd) {
    ArrayList<LocalDate> list = new ArrayList<>();
    LocalDate current = weekendValidation2(startDate);
    while (current.compareTo(endDate) <= 0) {
      list.add(current);
      current = current.plusDays(numberOfDaysToAdd);
      current = weekendValidation2(current);

    }
    return list;

  }

  private ArrayList<LocalDate> monthWiseList(LocalDate startDate, LocalDate endDate,
                                             int monthsToAdd) {
    ArrayList<LocalDate> list = new ArrayList<>();
    LocalDate endOfMonth = startDate.withDayOfMonth(startDate.lengthOfMonth());
    LocalDate current = weekendValidation(endOfMonth);
    while (current.compareTo(endDate) <= 0) {
      list.add(current);
      current = current.plusMonths(monthsToAdd);
      current = current.withDayOfMonth(current.lengthOfMonth());
      current = weekendValidation(current);

    }
    endOfMonth = endDate.withDayOfMonth(endDate.lengthOfMonth());
    if (endOfMonth.compareTo(endDate) > 0) {
      list.add(weekendValidation(endDate));
    }
    return list;

  }

  private void dateValidation(LocalDate date) {
    LocalDate today = LocalDate.now();
    if (date.compareTo(today) > 0) {
      throw new IllegalArgumentException("Date cannot be a future date.");
    } else if (isWeekend(date)) {
      throw new IllegalArgumentException("Date cannot be a weekend.");
    }
  }


  private float findMinimum(ArrayList<Float> list) {
    float min = Float.MAX_VALUE;
    for (float num : list) {
      min = (num == 0) ? min : Math.min(min, num);
    }
    return min;
  }

  private float fixScale(float min) {
    if (Math.abs(Float.MAX_VALUE - min) < 1) {
      return 1;
    }
    double power = Math.floor(Math.log10(min));
    double multiple = Math.pow(10, power);
    float scale = (float) (Math.floor(min / multiple) * multiple);
    return scale;

  }

  private ArrayList<Float> getValues(ArrayList<LocalDate> dates,ApiType apiType){
    ArrayList<Float> values = new ArrayList<>();
    Iterator<LocalDate> itr = dates.iterator();
    while (itr.hasNext()) {
      LocalDate date = itr.next();
      try {
        values.add(getTotalValue(date.toString(), apiType));
      } catch (Exception e) { //Holidays
        values.add(getTotalValue(weekendValidation(date.minusDays(1)).toString(),
                apiType));

      }
    }
    return values;
  }

  private StringBuilder getPlotHelper(ArrayList<LocalDate> dates, String pattern,
                                      LocalDate startDate, LocalDate endDate, ApiType apiType) {


    ArrayList<Float> values = getValues(dates,apiType);
    float min = findMinimum(values);
    float scale = fixScale(min);
    return drawStars(dates, values, scale, pattern, startDate, endDate);

  }

  private DatePair getDates(LocalDate startDate, LocalDate endDate, int maximumPlots){
    ArrayList<LocalDate> dates;
    if (startDate.compareTo(endDate) > 0) {
      LocalDate temp = endDate;
      endDate = startDate;
      startDate = temp;
    }

    long daysBetween = DAYS.between(startDate, endDate);
    StringBuilder plot = new StringBuilder();

    try {
      if (daysBetween <= maximumPlots) { //Day wise
        dates = dayWiseList(startDate, endDate, 1);
        return new DatePair(dates,"MMM dd");
      } else if (notFiveMonthsApart(daysBetween)) {
        int toDivide = maximumPlots == 10 ? 8 : 5;
        int numberOfDaysToAdd = (int) daysBetween / toDivide;
        dates = dayWiseList(startDate, endDate, numberOfDaysToAdd);
        return new DatePair(dates,"MMM dd");
      } else if (withinTheSameYear(daysBetween)) {
        dates = monthWiseList(startDate, endDate, 1);
        return new DatePair(dates,"MMM");
      } else if (notFiveYearsApart(daysBetween)) {
        dates = monthWiseList(startDate, endDate, 3);
        return new DatePair(dates,"MMM yyyy");
      } else {
        int yearsToAdd = 1;
        if (daysBetween / 365 > 10) {
          yearsToAdd = (int) daysBetween / 365 / 10;
        }
        dates = yearWiseList(startDate, endDate, yearsToAdd);
        return new DatePair(dates,"yyyy");
      }
    } catch (Exception e) {
      throw new IllegalArgumentException("This stock wasn't yet established on " + startDate);
    }
  }

  @Override
  public StringBuilder getPlot(LocalDate startDate, LocalDate endDate, ApiType apiType) {
    DatePair datePair = getDates(startDate,endDate,30);
    return getPlotHelper(datePair.dates, datePair.pattern, startDate, endDate, apiType);

  }

  private ArrayList<LocalDate> yearWiseList(LocalDate startDate, LocalDate endDate,
                                            int yearsToAdd) {
    ArrayList<LocalDate> list = new ArrayList<>();
    LocalDate endOfYear = startDate.withDayOfYear(startDate.lengthOfYear());
    LocalDate current = weekendValidation2(endOfYear);
    while (current.compareTo(endDate) <= 0) {
      list.add(current);
      current = current.plusYears(yearsToAdd);
      current = current.withDayOfYear(current.lengthOfYear());
      current = weekendValidation(current);

    }
    endOfYear = endDate.withDayOfYear(endDate.lengthOfYear());
    if (endOfYear.compareTo(endDate) > 0) {
      list.add(weekendValidation(endDate));
    }
    return list;
  }

  private boolean notFiveYearsApart(long daysBetween) {
    return daysBetween / 365 < 4;
  }

  private boolean withinTheSameYear(long daysBetween) {
    return daysBetween < 365;
  }

  private boolean notFiveMonthsApart(long daysBetween) {
    return daysBetween / 30 < 4;
  }

  private String formatDate(LocalDate date, String pattern) {
    return date.format(DateTimeFormatter.ofPattern(pattern));
  }

  private StringBuilder drawStars(ArrayList<LocalDate> dates, ArrayList<Float> values, float scale,
                                  String pattern, LocalDate startDate, LocalDate endDate) {


    StringBuilder plot = new StringBuilder();
    boolean moreThan50 = false;
    plot.append(String.format("Performance of portfolio %s from %s to %s\n",
            portfolioName, formatDate(startDate, pattern), formatDate(endDate, pattern)));

    for (int i = 0; i < dates.size(); i++) {
      int numberOfStars = (int) Math.floor(values.get(i) / scale);
      String value = "";
      if (numberOfStars > 50) {
        numberOfStars = 50;
        moreThan50 = true;
        value = String.format("($%.2f)", values.get(i));
      }
      String date = formatDate(dates.get(i), pattern);
      String stars = formStars(numberOfStars);
      plot.append(date + " : " + stars + value + "\n");
    }
    plot.append("Scale: * = $" + (int) scale + "\n");
    if (moreThan50) {
      plot.append("Maximum plot length is 50 *'s. Remaining *'s are truncated");
    }

    return plot;
  }

  private String formStars(int numberOfStars) {
    StringBuilder str = new StringBuilder();
    for (int i = 0; i < numberOfStars; i++) {
      str.append('*');
    }
    return str.toString();
  }

  private boolean isWeekend(LocalDate date) {
    DayOfWeek day = date.getDayOfWeek();
    return day == DayOfWeek.SUNDAY || day == DayOfWeek.SATURDAY;
  }

  private LocalDate weekendValidation(LocalDate date) {
    if (isWeekend(date)) {
      date = date.minusDays(1);
      return weekendValidation(date);
    }
    return date;
  }

  private LocalDate weekendValidation2(LocalDate date) {
    if (isWeekend(date)) {
      date = date.plusDays(1);
      return weekendValidation2(date);
    }
    return date;
  }

  @Override
  public float getTotalValue(String date, ApiType apiType) {
    LocalDate dateValue;
    try {
      dateValue = LocalDate.parse(date,
              DateTimeFormatter.ISO_LOCAL_DATE);
    } catch (Exception e) {
      throw new IllegalArgumentException("Date must be in yyyy-mm-dd format");
    }
    dateValidation(dateValue);
    float value = 0;
    for (String key : stockList.keySet()) {
      value += this.stockList.get(key).getValue(dateValue, apiType);
    }
    return value + getTotalValueDollarStrategy(dateValue, apiType);
  }


  @Override
  public StringBuilder getComposition(LocalDate date) {
    StringBuilder composition = new StringBuilder();
    String format = "%-40s%.2f%n";
    composition.append(String.format("%-1sComposition of %s upto %s%n", "",
            portfolioName, date.toString()));
    int c = 0;
    for (String key : stockList.keySet()) {
      float value = stockList.get(key).getComposition(date);
      if (value >= 0) {
        composition.append(String.format(format, key, value));
        c = c + 1;
      }
    }
    if (c == 0) {
      return new StringBuilder("Portfolio is empty at this date.");
    }
    return composition;
  }

  @Override
  public float getCostBasis(LocalDate date, ApiType apiType) {
    float cost = 0;
    for (String key : stockList.keySet()) {
      cost += this.stockList.get(key).getCostBasis(date, apiType);
    }
    float dollarStrategyCostBasis = costbasisDollarStrategy(date);
    return cost + dollarStrategyCostBasis;
  }

  @Override
  public HashMap<String, FlexibleStocksList> getStocksList() {
    return this.stockList;
  }

  @Override
  public boolean stockExists(String symbol) {
    return this.stockList.containsKey(symbol);

  }

  @Override
  public void investIntoPortfolio(LocalDate date, float amount, float transactionCost,
                                  HashMap<String, Float> stocks, ApiType apiType) {
    float individualTranscationCost = transactionCost / stocks.size();
    for (String key : stocks.keySet()) {
      float value = ApiCallImpl.getData(key, date, apiType);
      float stockAmount = (stocks.get(key) / 100) * amount;
      float numberOfShares = stockAmount / value;
      this.buyStock(key, date, numberOfShares, individualTranscationCost);
    }
  }

  @Override
  public void createDollarCostStrategyPortfolio(LocalDate startDate, LocalDate endDate,
                                                int interval, float amount, float transactionCost,
                                                HashMap<String, Float> stocks) {
    this.dollarCostStockList.add(new DollarCostStockImpl(startDate, interval, amount,
            transactionCost, stocks, endDate));
  }

  @Override
  public PlotPair newGetPlot(LocalDate startDate, LocalDate endDate, ApiType apiType,
                             int maximumPlots) {
    DatePair datePair = getDates(startDate,endDate,maximumPlots);
    ArrayList<Float> values = getValues(datePair.dates,apiType);
    ArrayList<String> dates = new ArrayList<>();
    int size = datePair.dates.size();
    for(int i=0;i<size;i++){
      String date = formatDate(datePair.dates.get(i), datePair.pattern);
      dates.add(date);

    }
    return new PlotPair(dates,values);
  }

  private float costbasisDollarStrategy(LocalDate date) {
    float cost = 0;
    for (HigherLevelStrategy dollarCostStock : this.dollarCostStockList) {
      cost += dollarCostStock.getCostBasis(date);
    }
    return cost;

  }

  private float getTotalValueDollarStrategy(LocalDate date, ApiType apiType) {
    float value = 0;
    for (HigherLevelStrategy dollarCostStock : this.dollarCostStockList) {
      value += dollarCostStock.getTotalValue(date, apiType);
    }
    return value;


  }

  @Override
  public String getName(){
    return this.portfolioName;
  }

  @Override
  public ArrayList<HigherLevelStrategy> getDollarCostStocks() {
    return this.dollarCostStockList;
  }

  @Override
  public String toString(){
    return "Stocks:"+stockList.toString()+"\n"+"Dollar Cost:"+dollarCostStockList.toString();
  }

}
