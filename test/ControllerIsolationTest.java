import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import controller.ControllerImpl;
import models.NewModel;
import view.View;
import view.ViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the Controller in isolation with a mock model.
 */
public class ControllerIsolationTest {
  @Test
  public void testCreateUser() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    NewModel model = new MockNewModel(log, olog);
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    View view = new ViewImpl(out);
    ByteArrayInputStream in;
    String input = "1 user2 1900 11";
    in = new ByteArrayInputStream(input.getBytes());
    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();
    assertEquals("user2 user2 1900.0 ", log.toString());
    assertEquals("userExists createUser ", olog.toString());
  }

  @Test
  public void testLoadUser() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    NewModel model = new MockNewModel(log, olog);
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    View view = new ViewImpl(out);
    ByteArrayInputStream in;
    String input = "2 user5 11";
    in = new ByteArrayInputStream(input.getBytes());
    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();
    assertEquals("user5 ", log.toString());
    assertEquals("loadUser isFlexiblePortfolio ", olog.toString());
  }

  @Test
  public void testCreatePortfolio() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    NewModel model = new MockNewModel(log, olog);
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    View view = new ViewImpl(out);
    ByteArrayInputStream in;
    String input = "2 user5 1 personal 2 AAPL 10 1 ORCL 20 2 7";
    in = new ByteArrayInputStream(input.getBytes());
    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();
    assertEquals("user5 personal personal false AAPL 10 ORCL 20 ", log.toString());
    assertEquals("loadUser isFlexiblePortfolio portfolioExists createPortfolio " +
            "addStockToPortfolio addStockToPortfolio ", olog.toString());
  }


  @Test
  public void testCreatePortfolioFlexible() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    NewModel model = new MockNewModel(log, olog);
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    View view = new ViewImpl(out);
    ByteArrayInputStream in;
    String input = "2 user5 1 personal 1 AAPL 1 2022-10-11 10 11";
    in = new ByteArrayInputStream(input.getBytes());
    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();
    assertEquals("user5 personal AAPL 2022-10-11 ", log.toString());
    assertEquals("loadUser isFlexiblePortfolio portfolioExists isFlexiblePortfolio" +
            " portfolioExists isFlexiblePortfolio portfolioExists isFlexiblePortfolio" +
            " portfolioExists save isFlexiblePortfolio ", olog.toString());
  }

  @Test
  public void testAddStockToPortfolio() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    NewModel model = new MockNewModel(log, olog);
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    View view = new ViewImpl(out);
    ByteArrayInputStream in;
    String input = "2 user5 1 personal 2 AAPL 10 1 ORCL 20 2 7 ";
    in = new ByteArrayInputStream(input.getBytes());
    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();
    assertEquals("user5 personal personal false AAPL 10 ORCL 20 ", log.toString());
    assertEquals("loadUser isFlexiblePortfolio portfolioExists createPortfolio " +
            "addStockToPortfolio addStockToPortfolio ", olog.toString());
  }

  @Test
  public void testLoadPortfolio() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    NewModel model = new MockNewModel(log, olog);
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    View view = new ViewImpl(out);
    ByteArrayInputStream in;
    String input = "2 user5 2 college 7";
    in = new ByteArrayInputStream(input.getBytes());
    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();
    assertEquals("user5 college ", log.toString());
    assertEquals("loadUser isFlexiblePortfolio loadPortfolio " +
            "isFlexiblePortfolio ", olog.toString());
  }

  @Test
  public void testSwitchingBetweenPortfolio() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    NewModel model = new MockNewModel(log, olog);
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    View view = new ViewImpl(out);
    ByteArrayInputStream in;
    String input = "2 user5 2 college 2 retir 11";
    in = new ByteArrayInputStream(input.getBytes());
    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();
    assertEquals("user5 college retir ", log.toString());
    assertEquals("loadUser isFlexiblePortfolio loadPortfolio isFlexiblePortfolio " +
            "loadPortfolio isFlexiblePortfolio ", olog.toString());
  }

  @Test
  public void testSave() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    NewModel model = new MockNewModel(log, olog);
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    View view = new ViewImpl(out);
    ByteArrayInputStream in;
    String input = "2 user5 1 retirement 2 GOOG 10 1 ORCL 5 2 5 7";
    in = new ByteArrayInputStream(input.getBytes());
    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();
    assertEquals("user ", log.toString());
    assertEquals("loadUser save ", olog.toString());
  }

  @Test
  public void testGetTotalValue() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    NewModel model = new MockNewModel(log, olog);
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    View view = new ViewImpl(out);
    ByteArrayInputStream in;
    String input = "2 user5 2 college 4 2022-10-31 7";
    in = new ByteArrayInputStream(input.getBytes());
    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();
    assertEquals("user 2022-10-30 ", log.toString());
    assertEquals("loadUser getTotalValue ", olog.toString());
  }

  @Test
  public void testGetTotalValueFlexible() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    NewModel model = new MockNewModel(log, olog);
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    View view = new ViewImpl(out);
    ByteArrayInputStream in;
    String input = "2 user5 8 2022-10-31 11";
    in = new ByteArrayInputStream(input.getBytes());
    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();
    assertEquals("user5 2022-10-31 ", log.toString());
    assertEquals("loadUser isFlexiblePortfolio portfolioExists isValidDate getTotalValue" +
            " isFlexiblePortfolio ", olog.toString());
  }

  @Test
  public void testGetComposition() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    NewModel model = new MockNewModel(log, olog);
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    View view = new ViewImpl(out);
    ByteArrayInputStream in;
    String input = "2 user5 2 college 3 7";
    in = new ByteArrayInputStream(input.getBytes());
    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();
    assertEquals("user ", log.toString());
    assertEquals("loadUser getComposition ", olog.toString());
  }

  @Test
  public void testGetCompositionFlexible() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    NewModel model = new MockNewModel(log, olog);
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    View view = new ViewImpl(out);
    ByteArrayInputStream in;
    String input = "2 user5 7 2022-11-14 11";
    in = new ByteArrayInputStream(input.getBytes());
    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();
    assertEquals("user5 2022-11-14 ", log.toString());
    assertEquals("loadUser isFlexiblePortfolio portfolioExists isValidDate " +
            "getComposition " +
            "isFlexiblePortfolio ", olog.toString());
  }

  @Test
  public void testGetCostBasis() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    NewModel model = new MockNewModel(log, olog);
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    View view = new ViewImpl(out);
    ByteArrayInputStream in;
    String input = "2 user5 6 2022-11-14 11";
    in = new ByteArrayInputStream(input.getBytes());
    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();
    assertEquals("user5 2022-11-14 ", log.toString());
    assertEquals("loadUser isFlexiblePortfolio portfolioExists isValidDate " +
            "getCostBasis isFlexiblePortfolio ", olog.toString());
  }

  @Test
  public void testGetPlot() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    NewModel model = new MockNewModel(log, olog);
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    View view = new ViewImpl(out);
    ByteArrayInputStream in;
    String input = "2 user5 9 2021-11-15 2022-11-14 11";
    in = new ByteArrayInputStream(input.getBytes());
    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();
    assertEquals("user5 2021-11-15 2022-11-14 ", log.toString());
    assertEquals("loadUser isFlexiblePortfolio portfolioExists isValidDate isValidDate " +
            "getPlot isFlexiblePortfolio ", olog.toString());
  }

}