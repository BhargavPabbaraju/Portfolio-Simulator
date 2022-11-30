package models;

import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class NewerModelImplTest {
  @Test
  public void testInsertIntoPortfolio() throws IOException, ParseException {
    NewerModel m = new NewerModelImpl();
    m.loadUser("user3");
    m.createPortfolio("flex", true);
    HashMap<String, Float> stocks = new HashMap<>();
    stocks.put("META", 40F);
    stocks.put("AAPL", 40F);
    stocks.put("MSFT", 20F);
    m.investIntoPortfolio("2022-11-21", 2000, 10, stocks, ApiType.ALPHA_VANTAGE);
    System.out.println(m.getComposition("2022-11-21"));
    System.out.println(m.getCostBasis("2022-11-21", ApiType.ALPHA_VANTAGE));

  }

  @Test
  public void testDollarCostBasisWithEndDate() throws IOException, ParseException {
    NewerModel m = new NewerModelImpl();
    m.loadUser("user3");
    m.createPortfolio("flex", true);
    HashMap<String, Float> stocks = new HashMap<>();
    stocks.put("META", 40F);
    stocks.put("AAPL", 40F);
    stocks.put("MSFT", 20F);
    m.createDollarCostStrategyPortfolio("2022-11-21", "2023-04-23", 30, 1000, 10, stocks);
    System.out.println(m.getCostBasis("2022-11-20", ApiType.ALPHA_VANTAGE));
    System.out.println(m.getCostBasis("2022-11-21", ApiType.ALPHA_VANTAGE));
    System.out.println(m.getCostBasis("2022-11-30", ApiType.ALPHA_VANTAGE));
    System.out.println(m.getCostBasis("2022-12-21", ApiType.ALPHA_VANTAGE));
    System.out.println(m.getCostBasis("2022-12-22", ApiType.ALPHA_VANTAGE));
    System.out.println(m.getCostBasis("2023-03-30", ApiType.ALPHA_VANTAGE));
    System.out.println(m.getCostBasis("2023-04-23", ApiType.ALPHA_VANTAGE));
    System.out.println(m.getCostBasis("2023-04-25", ApiType.ALPHA_VANTAGE));
    System.out.println(m.getCostBasis("2026-04-25", ApiType.ALPHA_VANTAGE));
  }

  @Test
  public void testDollarCostBasisWithOutEndDate() throws IOException, ParseException {
    NewerModel m = new NewerModelImpl();
    m.loadUser("user3");
    m.createPortfolio("flex", true);
    HashMap<String, Float> stocks = new HashMap<>();
    stocks.put("META", 40F);
    stocks.put("AAPL", 40F);
    stocks.put("MSFT", 20F);
    m.createDollarCostStrategyPortfolio("2022-11-21", null, 30, 1000, 10, stocks);
    System.out.println(m.getCostBasis("2022-11-20", ApiType.ALPHA_VANTAGE));
    System.out.println(m.getCostBasis("2022-11-21", ApiType.ALPHA_VANTAGE));
    System.out.println(m.getCostBasis("2022-11-30", ApiType.ALPHA_VANTAGE));
    System.out.println(m.getCostBasis("2022-12-21", ApiType.ALPHA_VANTAGE));
    System.out.println(m.getCostBasis("2022-12-22", ApiType.ALPHA_VANTAGE));
    System.out.println(m.getCostBasis("2023-03-30", ApiType.ALPHA_VANTAGE));
    System.out.println(m.getCostBasis("2023-04-23", ApiType.ALPHA_VANTAGE));
    System.out.println(m.getCostBasis("2023-04-25", ApiType.ALPHA_VANTAGE));
    System.out.println(m.getCostBasis("2026-04-25", ApiType.ALPHA_VANTAGE));
  }

  @Test
  public void testDollarGetValue() throws IOException, ParseException {
    NewerModel m = new NewerModelImpl();
    m.loadUser("user3");
    m.createPortfolio("flex", true);
    HashMap<String, Float> stocks = new HashMap<>();
    stocks.put("META", 40F);
    stocks.put("AAPL", 40F);
    stocks.put("MSFT", 20F);
    m.createDollarCostStrategyPortfolio("2022-09-20", "2023-11-21", 30, 1000, 10, stocks);
    System.out.println(m.getTotalValue("2022-09-19", ApiType.ALPHA_VANTAGE));
    System.out.println(m.getTotalValue("2022-09-21", ApiType.ALPHA_VANTAGE));
    System.out.println(m.getTotalValue("2022-09-30", ApiType.ALPHA_VANTAGE));
    System.out.println(m.getTotalValue("2022-10-21", ApiType.ALPHA_VANTAGE));
    System.out.println(m.getTotalValue("2022-10-24", ApiType.ALPHA_VANTAGE));
    System.out.println(m.getTotalValue("2022-10-31", ApiType.ALPHA_VANTAGE));
    System.out.println(m.getTotalValue("2022-11-21", ApiType.ALPHA_VANTAGE));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvestOnDateNegativeAmount() throws IOException, ParseException {
    NewerModel m = new NewerModelImpl();
    m.loadUser("user3");
    m.createPortfolio("flex", true);
    HashMap<String, Float> stocks = new HashMap<>();
    stocks.put("META", 40F);
    stocks.put("AAPL", 40F);
    stocks.put("MSFT", 20F);
    m.investIntoPortfolio("2022-11-21", -2000, 10, stocks, ApiType.ALPHA_VANTAGE);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvestOnDateZeroAmount() throws IOException, ParseException {
    NewerModel m = new NewerModelImpl();
    m.loadUser("user3");
    m.createPortfolio("flex", true);
    HashMap<String, Float> stocks = new HashMap<>();
    stocks.put("META", 40F);
    stocks.put("AAPL", 40F);
    stocks.put("MSFT", 20F);
    m.investIntoPortfolio("2022-11-21", 0, 10, stocks, ApiType.ALPHA_VANTAGE);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvestOnDateNegativeTransactionCost() throws IOException, ParseException {
    NewerModel m = new NewerModelImpl();
    m.loadUser("user3");
    m.createPortfolio("flex", true);
    HashMap<String, Float> stocks = new HashMap<>();
    stocks.put("META", 40F);
    stocks.put("AAPL", 40F);
    stocks.put("MSFT", 20F);
    m.investIntoPortfolio("2022-11-21", 2000, -10, stocks, ApiType.ALPHA_VANTAGE);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvestOnDateInvalid() throws IOException, ParseException {
    NewerModel m = new NewerModelImpl();
    m.loadUser("user3");
    m.createPortfolio("flex", true);
    HashMap<String, Float> stocks = new HashMap<>();
    stocks.put("META", 40F);
    stocks.put("AAPL", 40F);
    stocks.put("MSFT", 20F);
    m.investIntoPortfolio("2022-11-2jhsgdf", 2000, 10, stocks, ApiType.ALPHA_VANTAGE);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvestOnDateFuture() throws IOException, ParseException {
    NewerModel m = new NewerModelImpl();
    m.loadUser("user3");
    m.createPortfolio("flex", true);
    HashMap<String, Float> stocks = new HashMap<>();
    stocks.put("META", 40F);
    stocks.put("AAPL", 40F);
    stocks.put("MSFT", 20F);
    m.investIntoPortfolio("2022-12-17", 2000, 10, stocks, ApiType.ALPHA_VANTAGE);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvestOnDateWeekend() throws IOException, ParseException {
    NewerModel m = new NewerModelImpl();
    m.loadUser("user3");
    m.createPortfolio("flex", true);
    HashMap<String, Float> stocks = new HashMap<>();
    stocks.put("META", 40F);
    stocks.put("AAPL", 40F);
    stocks.put("MSFT", 20F);
    m.investIntoPortfolio("2022-11-20", 2000, 10, stocks, ApiType.ALPHA_VANTAGE);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvestOnHoliday() throws IOException, ParseException {
    NewerModel m = new NewerModelImpl();
    m.loadUser("user3");
    m.createPortfolio("flex", true);
    HashMap<String, Float> stocks = new HashMap<>();
    stocks.put("META", 40F);
    stocks.put("AAPL", 40F);
    stocks.put("MSFT", 20F);
    m.investIntoPortfolio("2022-11-24", 2000, 10, stocks, ApiType.ALPHA_VANTAGE);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvestOnDateInvalidSymbol() throws IOException, ParseException {
    NewerModel m = new NewerModelImpl();
    m.loadUser("user3");
    m.createPortfolio("flex", true);
    HashMap<String, Float> stocks = new HashMap<>();
    stocks.put("MEA", 40F);
    stocks.put("AAPL", 40F);
    stocks.put("MSFT", 20F);
    m.investIntoPortfolio("2022-11-21", 2000, 10, stocks, ApiType.ALPHA_VANTAGE);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvestOnDateNegativePercentage() throws IOException, ParseException {
    NewerModel m = new NewerModelImpl();
    m.loadUser("user3");
    m.createPortfolio("flex", true);
    HashMap<String, Float> stocks = new HashMap<>();
    stocks.put("META", -40F);
    stocks.put("AAPL", 40F);
    stocks.put("MSFT", 20F);
    m.investIntoPortfolio("2022-11-21", 2000, 10, stocks, ApiType.ALPHA_VANTAGE);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvestOnDateInvalidPercentage() throws IOException, ParseException {
    NewerModel m = new NewerModelImpl();
    m.loadUser("user3");
    m.createPortfolio("flex", true);
    HashMap<String, Float> stocks = new HashMap<>();
    stocks.put("META", 0F);
    stocks.put("AAPL", 40F);
    stocks.put("MSFT", 20F);
    m.investIntoPortfolio("2022-11-21", 2000, 10, stocks, ApiType.ALPHA_VANTAGE);
  }

  @Test
  public void testInvestOnDateSuccess() throws IOException, ParseException {
    NewerModel m = new NewerModelImpl();
    m.loadUser("user3");
    m.createPortfolio("flex", true);
    HashMap<String, Float> stocks = new HashMap<>();
    stocks.put("META", 40F);
    stocks.put("AAPL", 40F);
    stocks.put("MSFT", 20F);
    m.investIntoPortfolio("2022-11-21", 2000, 10, stocks, ApiType.ALPHA_VANTAGE);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDollarCostBasisNegativeAmount() throws IOException, ParseException {
    NewerModel m = new NewerModelImpl();
    m.loadUser("user3");
    m.createPortfolio("flex", true);
    HashMap<String, Float> stocks = new HashMap<>();
    stocks.put("META", 40F);
    stocks.put("AAPL", 40F);
    stocks.put("MSFT", 20F);
    m.createDollarCostStrategyPortfolio("2022-11-21", "2023-04-23", 30, -1000, 10, stocks);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDollarCostZeroAmount() throws IOException, ParseException {
    NewerModel m = new NewerModelImpl();
    m.loadUser("user3");
    m.createPortfolio("flex", true);
    HashMap<String, Float> stocks = new HashMap<>();
    stocks.put("META", 40F);
    stocks.put("AAPL", 40F);
    stocks.put("MSFT", 20F);
    m.createDollarCostStrategyPortfolio("2022-11-21", "2023-04-23", 30, 0, 10, stocks);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDollarCostNegativeComission() throws IOException, ParseException {
    NewerModel m = new NewerModelImpl();
    m.loadUser("user3");
    m.createPortfolio("flex", true);
    HashMap<String, Float> stocks = new HashMap<>();
    stocks.put("META", 40F);
    stocks.put("AAPL", 40F);
    stocks.put("MSFT", 20F);
    m.createDollarCostStrategyPortfolio("2022-11-21", "2023-04-23", 30, 2000, -10, stocks);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDollarCostNegativeInterval() throws IOException, ParseException {
    NewerModel m = new NewerModelImpl();
    m.loadUser("user3");
    m.createPortfolio("flex", true);
    HashMap<String, Float> stocks = new HashMap<>();
    stocks.put("META", 40F);
    stocks.put("AAPL", 40F);
    stocks.put("MSFT", 20F);
    m.createDollarCostStrategyPortfolio("2022-11-21", "2023-04-23", -30, 2000, 10, stocks);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDollarCostZeroInterval() throws IOException, ParseException {
    NewerModel m = new NewerModelImpl();
    m.loadUser("user3");
    m.createPortfolio("flex", true);
    HashMap<String, Float> stocks = new HashMap<>();
    stocks.put("META", 40F);
    stocks.put("AAPL", 40F);
    stocks.put("MSFT", 20F);
    m.createDollarCostStrategyPortfolio("2022-11-21", "2023-04-23", 0, 2000, 10, stocks);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDollarCostInvalidsymbol() throws IOException, ParseException {
    NewerModel m = new NewerModelImpl();
    m.loadUser("user3");
    m.createPortfolio("flex", true);
    HashMap<String, Float> stocks = new HashMap<>();
    stocks.put("MEA", 40F);
    stocks.put("AAPL", 40F);
    stocks.put("MSFT", 20F);
    m.createDollarCostStrategyPortfolio("2022-11-21", "2023-04-23", 30, 2000, 10, stocks);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDollarCostInvalidWeight() throws IOException, ParseException {
    NewerModel m = new NewerModelImpl();
    m.loadUser("user3");
    m.createPortfolio("flex", true);
    HashMap<String, Float> stocks = new HashMap<>();
    stocks.put("META", 0F);
    stocks.put("AAPL", 40F);
    stocks.put("MSFT", 20F);
    m.createDollarCostStrategyPortfolio("2022-11-21", "2023-04-23", 30, 2000, 10, stocks);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDollarCostNegativeWeight() throws IOException, ParseException {
    NewerModel m = new NewerModelImpl();
    m.loadUser("user3");
    m.createPortfolio("flex", true);
    HashMap<String, Float> stocks = new HashMap<>();
    stocks.put("META", -40F);
    stocks.put("AAPL", 40F);
    stocks.put("MSFT", 20F);
    m.createDollarCostStrategyPortfolio("2022-11-21", "2023-04-23", 30, 2000, 10, stocks);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDollarCostInvalidStartDate() throws IOException, ParseException {
    NewerModel m = new NewerModelImpl();
    m.loadUser("user3");
    m.createPortfolio("flex", true);
    HashMap<String, Float> stocks = new HashMap<>();
    stocks.put("META", 40F);
    stocks.put("AAPL", 40F);
    stocks.put("MSFT", 20F);
    m.createDollarCostStrategyPortfolio("2022-11-2asd1", "2023-04-23", 30, 2000, 10, stocks);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDollarCostInvalidEndDate() throws IOException, ParseException {
    NewerModel m = new NewerModelImpl();
    m.loadUser("user3");
    m.createPortfolio("flex", true);
    HashMap<String, Float> stocks = new HashMap<>();
    stocks.put("META", 40F);
    stocks.put("AAPL", 40F);
    stocks.put("MSFT", 20F);
    m.createDollarCostStrategyPortfolio("2022-11-21", "2023-04-2wer3", 30, 2000, 10, stocks);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDollarCostWeekendStartDate() throws IOException, ParseException {
    NewerModel m = new NewerModelImpl();
    m.loadUser("user3");
    m.createPortfolio("flex", true);
    HashMap<String, Float> stocks = new HashMap<>();
    stocks.put("META", 40F);
    stocks.put("AAPL", 40F);
    stocks.put("MSFT", 20F);
    m.createDollarCostStrategyPortfolio("2022-11-19", "2023-04-23", 30, 2000, 10, stocks);
  }

  @Test
  public void testDollarCostEndDateNull() throws IOException, ParseException {
    NewerModel m = new NewerModelImpl();
    m.loadUser("user3");
    m.createPortfolio("flex", true);
    HashMap<String, Float> stocks = new HashMap<>();
    stocks.put("META", 40F);
    stocks.put("AAPL", 40F);
    stocks.put("MSFT", 20F);
    m.createDollarCostStrategyPortfolio("2022-11-19", "", 30, 2000, 10, stocks);
    assertEquals(26120.0, m.getCostBasis("2023-11-23", ApiType.ALPHA_VANTAGE), 0.1);
  }

  @Test
  public void testDollarCostEndDateFuture() throws IOException, ParseException {
    NewerModel m = new NewerModelImpl();
    m.loadUser("user3");
    m.createPortfolio("flex", true);
    HashMap<String, Float> stocks = new HashMap<>();
    stocks.put("META", 40F);
    stocks.put("AAPL", 40F);
    stocks.put("MSFT", 20F);
    m.createDollarCostStrategyPortfolio("2022-11-19", "2023-11-14", 30, 2000, 10, stocks);
    assertEquals(26120.0, m.getCostBasis("2023-11-23", ApiType.ALPHA_VANTAGE), 0.1);
  }

  @Test//need to add in view
  public void testDollarCostStartGreaterThanEndDate() throws IOException, ParseException {
    NewerModel m = new NewerModelImpl();
    m.loadUser("user3");
    m.createPortfolio("flex", true);
    HashMap<String, Float> stocks = new HashMap<>();
    stocks.put("META", 40F);
    stocks.put("AAPL", 40F);
    stocks.put("MSFT", 20F);
    m.createDollarCostStrategyPortfolio("2022-11-23", "2022-11-14", 30, 2000, 10, stocks);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDollarCostGetValueFutureDate() throws IOException, ParseException {
    NewerModel m = new NewerModelImpl();
    m.loadUser("user3");
    m.createPortfolio("flex", true);
    HashMap<String, Float> stocks = new HashMap<>();
    stocks.put("META", 40F);
    stocks.put("AAPL", 40F);
    stocks.put("MSFT", 20F);
    m.createDollarCostStrategyPortfolio("2022-06-10", "2022-11-14", 30, 2000, 10, stocks);
    assertEquals(26120.0, m.getTotalValue("2023-11-23", ApiType.ALPHA_VANTAGE), 0.1);
  }

  @Test
  public void testDollarCostGetValueBeforeStartDate() throws IOException, ParseException {
    NewerModel m = new NewerModelImpl();
    m.loadUser("user3");
    m.createPortfolio("flex", true);
    HashMap<String, Float> stocks = new HashMap<>();
    stocks.put("META", 40F);
    stocks.put("AAPL", 40F);
    stocks.put("MSFT", 20F);
    m.createDollarCostStrategyPortfolio("2022-06-10", "2022-11-14", 30, 2000, 10, stocks);
    assertEquals(0.0, m.getTotalValue("2022-06-09", ApiType.ALPHA_VANTAGE), 0.0);
  }

  @Test
  public void testDollarCostGetValueLessThanInterval() throws IOException, ParseException {
    NewerModel m = new NewerModelImpl();
    m.loadUser("user3");
    m.createPortfolio("flex", true);
    HashMap<String, Float> stocks = new HashMap<>();
    stocks.put("META", 40F);
    stocks.put("AAPL", 40F);
    stocks.put("MSFT", 20F);
    m.createDollarCostStrategyPortfolio("2022-06-10", "2022-11-14", 30, 2000, 10, stocks);
    assertEquals(1900.871826171875, m.getTotalValue("2022-06-13", ApiType.ALPHA_VANTAGE), 0.0);
  }

  @Test
  public void testDollarCostGetValueNormal() throws IOException, ParseException {
    NewerModel m = new NewerModelImpl();
    m.loadUser("user3");
    m.createPortfolio("flex", true);
    HashMap<String, Float> stocks = new HashMap<>();
    stocks.put("META", 40F);
    stocks.put("AAPL", 40F);
    stocks.put("MSFT", 20F);
    m.createDollarCostStrategyPortfolio("2022-06-10", "2022-11-14", 30, 2000, 10, stocks);
    assertEquals(8974.609375, m.getTotalValue("2022-10-13", ApiType.ALPHA_VANTAGE), 0.0);
  }

  @Test
  public void testDollarCostGetValueEqualEndDate() throws IOException, ParseException {
    NewerModel m = new NewerModelImpl();
    m.loadUser("user3");
    m.createPortfolio("flex", true);
    HashMap<String, Float> stocks = new HashMap<>();
    stocks.put("META", 40F);
    stocks.put("AAPL", 40F);
    stocks.put("MSFT", 20F);
    m.createDollarCostStrategyPortfolio("2022-06-10", "2022-11-14", 30, 2000, 10, stocks);
    assertEquals(10993.7216796875, m.getTotalValue("2022-11-14", ApiType.ALPHA_VANTAGE), 0.0);
  }

  @Test
  public void testDollarCostGetValueAfterEndDate() throws IOException, ParseException {
    NewerModel m = new NewerModelImpl();
    m.loadUser("user3");
    m.createPortfolio("flex", true);
    HashMap<String, Float> stocks = new HashMap<>();
    stocks.put("META", 40F);
    stocks.put("AAPL", 40F);
    stocks.put("MSFT", 20F);
    m.createDollarCostStrategyPortfolio("2022-06-10", "2022-11-14", 30, 2000, 10, stocks);
    assertEquals(11076.84765625, m.getTotalValue("2022-11-23", ApiType.ALPHA_VANTAGE), 0.0);
  }

  @Test
  public void testDollarCostGetValueEndDateNull() throws IOException, ParseException {
    NewerModel m = new NewerModelImpl();
    m.loadUser("user3");
    m.createPortfolio("flex", true);
    HashMap<String, Float> stocks = new HashMap<>();
    stocks.put("META", 40F);
    stocks.put("AAPL", 40F);
    stocks.put("MSFT", 20F);
    m.createDollarCostStrategyPortfolio("2022-06-10", "", 30, 2000, 10, stocks);
    assertEquals(11076.84765625, m.getTotalValue("2022-11-23", ApiType.ALPHA_VANTAGE), 0.0);
  }

  @Test
  public void testDollarCostGetCostBasisFutureDate() throws IOException, ParseException {
    NewerModel m = new NewerModelImpl();
    m.loadUser("user3");
    m.createPortfolio("flex", true);
    HashMap<String, Float> stocks = new HashMap<>();
    stocks.put("META", 40F);
    stocks.put("AAPL", 40F);
    stocks.put("MSFT", 20F);
    m.createDollarCostStrategyPortfolio("2022-06-10", "2022-11-14", 30, 2000, 10, stocks);
    assertEquals(12050.0, m.getCostBasis("2023-11-23", ApiType.ALPHA_VANTAGE), 0.1);
  }

  @Test
  public void testDollarCostGetCostBasisBeforeStartDate() throws IOException, ParseException {
    NewerModel m = new NewerModelImpl();
    m.loadUser("user3");
    m.createPortfolio("flex", true);
    HashMap<String, Float> stocks = new HashMap<>();
    stocks.put("META", 40F);
    stocks.put("AAPL", 40F);
    stocks.put("MSFT", 20F);
    m.createDollarCostStrategyPortfolio("2022-06-10", "2022-11-14", 30, 2000, 10, stocks);
    assertEquals(0.0, m.getCostBasis("2022-06-09", ApiType.ALPHA_VANTAGE), 0.0);
  }

  @Test
  public void testDollarCostGetCostBasisLessThanInterval() throws IOException, ParseException {
    NewerModel m = new NewerModelImpl();
    m.loadUser("user3");
    m.createPortfolio("flex", true);
    HashMap<String, Float> stocks = new HashMap<>();
    stocks.put("META", 40F);
    stocks.put("AAPL", 40F);
    stocks.put("MSFT", 20F);
    m.createDollarCostStrategyPortfolio("2022-06-10", "2022-11-14", 30, 2000, 10, stocks);
    assertEquals(2010.0, m.getCostBasis("2022-06-13", ApiType.ALPHA_VANTAGE), 0.0);
  }

  @Test
  public void testDollarCostGetCostBasisNormal() throws IOException, ParseException {
    NewerModel m = new NewerModelImpl();
    m.loadUser("user3");
    m.createPortfolio("flex", true);
    HashMap<String, Float> stocks = new HashMap<>();
    stocks.put("META", 40F);
    stocks.put("AAPL", 40F);
    stocks.put("MSFT", 20F);
    m.createDollarCostStrategyPortfolio("2022-06-10", "2022-11-14", 30, 2000, 10, stocks);
    assertEquals(10040.0, m.getCostBasis("2022-10-13", ApiType.ALPHA_VANTAGE), 0.0);
  }

  @Test
  public void testDollarCostGetCostBasisEqualEndDate() throws IOException, ParseException {
    NewerModel m = new NewerModelImpl();
    m.loadUser("user3");
    m.createPortfolio("flex", true);
    HashMap<String, Float> stocks = new HashMap<>();
    stocks.put("META", 40F);
    stocks.put("AAPL", 40F);
    stocks.put("MSFT", 20F);
    m.createDollarCostStrategyPortfolio("2022-06-10", "2022-11-14", 30, 2000, 10, stocks);
    assertEquals(12050.0, m.getCostBasis("2022-11-14", ApiType.ALPHA_VANTAGE), 0.0);
  }

  @Test
  public void testDollarCostGetCostBasisAfterEndDate() throws IOException, ParseException {
    NewerModel m = new NewerModelImpl();
    m.loadUser("user3");
    m.createPortfolio("flex", true);
    HashMap<String, Float> stocks = new HashMap<>();
    stocks.put("META", 40F);
    stocks.put("AAPL", 40F);
    stocks.put("MSFT", 20F);
    m.createDollarCostStrategyPortfolio("2022-06-10", "2022-11-14", 30, 2000, 10, stocks);
    assertEquals(12050.0, m.getCostBasis("2022-11-23", ApiType.ALPHA_VANTAGE), 0.0);
  }

  @Test
  public void testDollarCostGetCostBasisEndDateNull() throws IOException, ParseException {
    NewerModel m = new NewerModelImpl();
    m.loadUser("user3");
    m.createPortfolio("flex", true);
    HashMap<String, Float> stocks = new HashMap<>();
    stocks.put("META", 40F);
    stocks.put("AAPL", 40F);
    stocks.put("MSFT", 20F);
    m.createDollarCostStrategyPortfolio("2022-06-10", "", 30, 2000, 10, stocks);
    assertEquals(12050.0, m.getCostBasis("2022-11-23", ApiType.ALPHA_VANTAGE), 0.0);
  }


}