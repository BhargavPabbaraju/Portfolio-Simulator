package models;

import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

import static org.junit.Assert.*;

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

  @Test
  public void testSave() throws IOException {
    NewerModel m = new NewerModelImpl();
    m.createUser("user",1900);
    m.createPortfolio("flex", true);
    HashMap<String, Float> stocks = new HashMap<>();
    stocks.put("META", 40F);
    stocks.put("AAPL", 40F);
    stocks.put("MSFT", 20F);

    m.createDollarCostStrategyPortfolio("2022-09-20", "2023-11-21", 30, 67457, 10, stocks);
    m.buyStock("AAPL","2022-11-01",10,10);
    m.buyStock("GOOG","2022-11-01",10,10);
    m.createDollarCostStrategyPortfolio("2022-09-20", "", 10, 2000, 10, stocks);
    m.createPortfolio("bus",true);
    m.createDollarCostStrategyPortfolio("2022-09-20", "", 10, 2000, 10, stocks);
    m.buyStock("AAPL","2022-11-01",10,10);
    m.save();
  }

  @Test
  public void testLoad() throws IOException, ParseException {
    NewerModel m = new NewerModelImpl();
    m.loadUser("user9");
    System.out.println(m.getTotalValue("2022-11-21", ApiType.ALPHA_VANTAGE));
  }

}