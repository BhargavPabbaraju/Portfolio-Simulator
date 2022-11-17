package models;

import junit.framework.TestCase;

import org.junit.Test;


import java.io.IOException;

import java.text.ParseException;


import static org.junit.Assert.assertEquals;

/**
 * Tests for New model.
 */

public class NewModelImplTest extends TestCase {


  @Test
  public void testGetTotalValue() throws IOException, ParseException {
    NewModel m = new NewModelImpl();
    m.loadUser("user5");
    m.createPortfolio("flex", true);
    m.buyStock("AAPL", "2022-01-03", 50, 10);
    m.sellStock("AAPL", "2022-01-10", 40, 10);
    m.sellStock("AAPL", "2022-01-05", 5, 10);
    m.buyStock("AAPL", "2021-12-28", 10, 10);
    m.buyStock("IBM", "2022-01-03", 50, 10);
    m.sellStock("IBM", "2022-01-10", 40, 10);
    m.sellStock("IBM", "2022-01-05", 5, 10);
    m.buyStock("IBM", "2021-03-11", 10, 10);
    assertEquals(0, m.getTotalValue("2021-02-11", ApiType.ALPHA_VANTAGE), 0.01);


  }

  @Test
  public void testGetPlot() throws IOException, ParseException {
    NewModel m = new NewModelImpl();
    m.loadUser("user5");
    m.createPortfolio("flex", true);
    m.buyStock("AAPL", "2022-01-03", 50, 10);
    m.sellStock("AAPL", "2022-01-10", 40, 10);
    m.sellStock("AAPL", "2022-01-05", 5, 10);
    m.buyStock("AAPL", "2021-12-28", 10, 10);
    m.buyStock("IBM", "2022-01-03", 50, 10);
    m.sellStock("IBM", "2022-01-04", 20, 10);
    m.buyStock("IBM", "2022-04-01", 60, 10);
    m.sellStock("IBM", "2022-05-02", 60, 10);
    assertEquals("Performance of portfolio flex from 2010 to 2022\n" +
            "2010 : \n" +
            "2011 : \n" +
            "2012 : \n" +
            "2013 : \n" +
            "2014 : \n" +
            "2015 : \n" +
            "2016 : \n" +
            "2017 : \n" +
            "2018 : \n" +
            "2019 : \n" +
            "2020 : \n" +
            "2021 : *\n" +
            "2022 : *****\n" +
            "Scale: * = $1000\n",
            m.getPlot("2010-11-01", "2022-05-20",
                    ApiType.ALPHA_VANTAGE).toString());


  }

  @Test
  public void testSave() throws IOException, ParseException {
    NewModel m = new NewModelImpl();
    m.createUser("user9", 2000);
    m.createPortfolio("retir", true);
    m.buyStock("GOOG", "2022-08-01", 20, 10);
    m.sellStock("GOOG", "2022-11-01", 20, 10);
    m.buyStock("ORCL", "2022-08-01", 10, 10);
    m.createPortfolio("college", false);
    m.addStockToPortfolio("MSFT", 30);
    m.save();
    m.loadUser("user9");
    assertEquals(" Composition of retir upto 2022-08-01\n" +
            "GOOG                                    20.00\n" +
            "ORCL                                    10.00\n",m.getComposition("2022-08-01"));


  }

  @Test
  public void testLoad() throws IOException, ParseException {
    NewModel m = new NewModelImpl();
    m.loadUser("user5");
    m.loadPortfolio("retir");
    assertEquals(" Composition of retir upto 2022-11-02\n" +
                    "GOOG                                    40.00\n" +
                    "ORCL                                    10.00\n",
            m.getComposition("2022-11-02").toString());
    m.loadPortfolio("college");
    assertEquals("                    college\n" +
            "MSFT                                    30.00\n", m.getComposition().toString());
  }

  @Test
  public void testCostBasis() throws IOException, ParseException {
    NewModel m = new NewModelImpl();
    m.loadUser("user5");
    assertEquals(4924, m.getCostBasis("2022-11-02",
            ApiType.ALPHA_VANTAGE), 0.01);
  }

  @Test
  public void testListOfPortfolios() throws IOException, ParseException {
    NewModel m = new NewModelImpl();
    m.loadUser("user5");
    assertEquals("Portfolio                               Type\n" +
                    "college                                 Inflexible\n" +
                    "retir                                   Flexible\n",
            m.getListOfPortfolios().toString());
  }

  @Test
  public void testCompositionBefore() throws IOException, ParseException {
    NewModel m = new NewModelImpl();
    m.loadUser("user5");
    assertEquals("Portfolio is empty at this date.",
            m.getComposition("2020-11-13").toString());

  }

  @Test
  public void testCostBasisBefore() throws IOException, ParseException {
    NewModel m = new NewModelImpl();
    m.loadUser("user5");
    assertEquals(0, m.getCostBasis("2020-11-13", ApiType.ALPHA_VANTAGE), 0.01);

  }

  @Test
  public void testCreatePortfolioSameStockTwice() throws IOException, ParseException {
    NewModel m = new NewModelImpl();
    m.loadUser("user5");
    m.createPortfolio("personal", true);
    m.buyStock("AAPL", "2022-11-01", 10, 10);
    m.buyStock("AAPL", "2022-11-01", 10, 10);
    assertEquals(" Composition of personal upto 2022-11-02\n" +
                    "AAPL                                    20.00\n",
            m.getComposition("2022-11-02").toString());

  }

  @Test
  public void testBuyFutureDate() throws IOException, ParseException {
    NewModel m = new NewModelImpl();
    m.loadUser("user5");
    m.createPortfolio("personal", true);
    try {
      m.buyStock("AAPL", "2023-11-01", 10, 10);
      fail("Exception is expected");
    } catch (IllegalArgumentException e) {
      assertEquals("Date cannot be a future date.", e.getMessage());
    }
  }

  @Test
  public void testSellFutureDate() throws IOException, ParseException {
    NewModel m = new NewModelImpl();
    m.loadUser("user5");
    m.createPortfolio("personal", true);
    try {
      m.sellStock("AAPL", "2023-11-01", 10, 10);
      fail("Exception is expected");
    } catch (IllegalArgumentException e) {
      assertEquals("Date cannot be a future date.", e.getMessage());
    }
  }

  @Test
  public void testBuyWeekend() throws IOException, ParseException {
    NewModel m = new NewModelImpl();
    m.loadUser("user5");
    m.createPortfolio("personal", true);
    try {
      m.buyStock("AAPL", "2022-11-13", 10, 10);
      fail("Exception is expected");
    } catch (IllegalArgumentException e) {
      assertEquals("Date cannot be a weekend.", e.getMessage());
    }
  }

  @Test
  public void testSellWeekend() throws IOException, ParseException {
    NewModel m = new NewModelImpl();
    m.loadUser("user5");
    m.createPortfolio("personal", true);
    try {
      m.sellStock("AAPL", "2022-11-13", 10, 10);
      fail("Exception is expected");
    } catch (IllegalArgumentException e) {
      assertEquals("Date cannot be a weekend.", e.getMessage());
    }
  }

  @Test
  public void testBuyInvalidSymbol() throws IOException, ParseException {
    NewModel m = new NewModelImpl();
    m.loadUser("user5");
    m.createPortfolio("personal", true);
    try {
      m.buyStock("ssd", "2022-11-15", 10, 10);
      fail("Exception is expected");
    } catch (IllegalArgumentException e) {
      assertEquals("ssd Symbol not found", e.getMessage());
    }
  }

  @Test
  public void testSellInvalidSymbol() throws IOException, ParseException {
    NewModel m = new NewModelImpl();
    m.loadUser("user5");
    m.createPortfolio("personal", true);
    try {
      m.sellStock("ssd", "2022-11-15", 10, 10);
      fail("Exception is expected");
    } catch (IllegalArgumentException e) {
      assertEquals("You cannot sell stocks which are not in your portfolio.",
              e.getMessage());
    }
  }

  @Test
  public void testSellBeforeBuying() throws IOException, ParseException {
    NewModel m = new NewModelImpl();
    m.loadUser("user5");
    m.createPortfolio("personal", true);
    m.buyStock("GOOG", "2022-11-02", 10, 10);
    try {
      m.sellStock("GOOG", "2022-11-01", 10, 10);
      fail("Exception is expected");
    } catch (IllegalArgumentException e) {
      assertEquals("You need to buy a stock before you sell",
              e.getMessage());
    }

  }


}