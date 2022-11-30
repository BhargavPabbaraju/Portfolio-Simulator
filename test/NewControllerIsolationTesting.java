import org.junit.Test;

import java.util.HashMap;

import controller.Features;
import controller.NewController;
import models.NewerModel;
import view.NewView;


import static org.junit.Assert.assertEquals;

public class NewControllerIsolationTesting {
  @Test
  public void testCreatePortfolio() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    NewerModel model = new MockNewModel(log, olog);
    NewView view = new MockNewView();
    Features controller = new NewController(model, view);
    controller.createPortfolio("abcd");
    assertEquals("abcd true ", log.toString());
    assertEquals("createPortfolio ", olog.toString());
  }

  @Test
  public void testBuyStocks() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    NewerModel model = new MockNewModel(log, olog);
    NewView view = new MockNewView();
    Features controller = new NewController(model, view);
    controller.buyStocks("AAPL", 10, "2022-11-14", 5,
            true);
    assertEquals("AAPL 2022-11-14 10.0 5.0 ", log.toString());
    assertEquals("buyStock ", olog.toString());
  }

  @Test
  public void testSellStocks() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    NewerModel model = new MockNewModel(log, olog);
    NewView view = new MockNewView();
    Features controller = new NewController(model, view);
    controller.sellStocks("AAPL", 10, "2022-11-14", 5);
    assertEquals("AAPL 2022-11-14 10.0 5.0 ", log.toString());
    assertEquals("sellStock ", olog.toString());
  }

  @Test
  public void testGetCostBasis() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    NewerModel model = new MockNewModel(log, olog);
    NewView view = new MockNewView();
    Features controller = new NewController(model, view);
    controller.getCostBasis("2022-11-14");
    assertEquals("2022-11-14 ", log.toString());
    assertEquals("getCostBasis ", olog.toString());
  }

  @Test
  public void testGetTotalValue() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    NewerModel model = new MockNewModel(log, olog);
    NewView view = new MockNewView();
    Features controller = new NewController(model, view);
    controller.getTotalValue("2022-11-14");
    assertEquals("2022-11-14 ", log.toString());
    assertEquals("getTotalValue ", olog.toString());
  }

  @Test
  public void testSave() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    NewerModel model = new MockNewModel(log, olog);
    NewView view = new MockNewView();
    Features controller = new NewController(model, view);
    controller.save();
    assertEquals("", log.toString());
    assertEquals("save ", olog.toString());
  }

  @Test
  public void testLoadPortfolio() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    NewerModel model = new MockNewModel(log, olog);
    NewView view = new MockNewView();
    Features controller = new NewController(model, view);
    controller.loadPortfolio("abcd");
    assertEquals("abcd ", log.toString());
    assertEquals("loadPortfolio ", olog.toString());
  }

  @Test
  public void testInvestOnDate() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    NewerModel model = new MockNewModel(log, olog);
    NewView view = new MockNewView();
    Features controller = new NewController(model, view);
    HashMap<String, Float> stocks = new HashMap<>();
    stocks.put("META", 40F);
    stocks.put("AAPL", 40F);
    stocks.put("MSFT", 20F);
    controller.investOnDate("2022-11-21", 2000, 10, stocks, true);
    assertEquals("2022-11-21 2000.0 10.0 {MSFT=20.0, META=40.0, AAPL=40.0} ALPHA_VANTAGE",
            log.toString());
    assertEquals("investIntoPortfolio ", olog.toString());
  }

  @Test
  public void testDollarCost() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    NewerModel model = new MockNewModel(log, olog);
    NewView view = new MockNewView();
    Features controller = new NewController(model, view);
    HashMap<String, Float> stocks = new HashMap<>();
    stocks.put("META", 40F);
    stocks.put("AAPL", 40F);
    stocks.put("MSFT", 20F);
    controller.dollarCost("2022-06-21", "2022-11-14", 30, 2000,
            10, stocks, true);
    assertEquals("2022-06-21 2022-11-14 2000.0 10.0 {MSFT=20.0, META=40.0, AAPL=40.0} 30",
            log.toString());
    assertEquals("createDollarCostStrategyPortfolio ", olog.toString());
  }

  @Test
  public void testCreateUser() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    NewerModel model = new MockNewModel(log, olog);
    NewView view = new MockNewView();
    Features controller = new NewController(model, view);
    controller.createUser("user5", 1900);
    assertEquals("user5 1900.0 ", log.toString());
    assertEquals("createUser ", olog.toString());
  }

  @Test
  public void testLoadUser() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    NewerModel model = new MockNewModel(log, olog);
    NewView view = new MockNewView();
    Features controller = new NewController(model, view);
    controller.loadUser("user5");
    assertEquals("user5 ", log.toString());
    assertEquals("loadUser ", olog.toString());
  }

}
