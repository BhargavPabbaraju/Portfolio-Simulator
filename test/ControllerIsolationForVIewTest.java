import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import controller.ControllerImpl;
import models.NewModel;
import models.NewModelImpl;
import view.View;


import static org.junit.Assert.assertEquals;

/**
 * This class tests the Controller in isolation with a mock view.
 */
public class ControllerIsolationForVIewTest {

  @Test
  public void testCreateUser() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    NewModel model = new NewModelImpl();
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    View view = new MockView(log, olog);
    ByteArrayInputStream in;
    String input = "1 user2 1900 11";
    in = new ByteArrayInputStream(input.getBytes());
    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();
    assertEquals("displayInitialMenu askForUsername askForBalance displayMessage " +
            "displayFlexibleMenu ", olog.toString());
  }

  @Test
  public void testLoadUser() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    NewModel model = new NewModelImpl();
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    View view = new MockView(log, olog);
    ByteArrayInputStream in;
    String input = "2 user5 11";
    in = new ByteArrayInputStream(input.getBytes());
    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();
    assertEquals("displayInitialMenu askForUsername displayMessage " +
            "displayFlexibleMenu ", olog.toString());
  }

  @Test
  public void testCreatePortfolioInFlexible() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    NewModel model = new NewModelImpl();
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    View view = new MockView(log, olog);
    ByteArrayInputStream in;
    String input = "2 user5 1 personal 2 AAPL 10 1 ORCL 20 2 7";
    in = new ByteArrayInputStream(input.getBytes());
    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();
    assertEquals("displayInitialMenu askForUsername displayMessage displayFlexibleMenu" +
            " askForPortfolioName displayPortfolioTypesMenu askForStockSymbol askForShares " +
            "displayAddNewStockMenu askForStockSymbol askForShares displayAddNewStockMenu " +
            "displayMainMenu ", olog.toString());
  }

  @Test
  public void testCreatePortfolioFlexible() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    NewModel model = new NewModelImpl();
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    View view = new MockView(log, olog);
    ByteArrayInputStream in;
    String input = "2 user5 1 personal 1 AAPL 1 2022-10-11 10 11";
    in = new ByteArrayInputStream(input.getBytes());
    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();
    assertEquals("displayInitialMenu askForUsername displayMessage displayFlexibleMenu " +
            "askForPortfolioName displayPortfolioTypesMenu askForStockSymbol " +
            "askForTransactionCost " +
            "askForDate askForShares displayMessage displayFlexibleMenu ", olog.toString());
  }

  @Test
  public void testAddStockToPortfolio() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    NewModel model = new NewModelImpl();
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    View view = new MockView(log, olog);
    ByteArrayInputStream in;
    String input = "2 user5 1 personal 2 AAPL 10 1 ORCL 20 2 7";
    in = new ByteArrayInputStream(input.getBytes());
    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();
    assertEquals("displayInitialMenu askForUsername displayMessage displayFlexibleMenu " +
            "askForPortfolioName displayPortfolioTypesMenu askForStockSymbol askForShares " +
            "displayAddNewStockMenu askForStockSymbol askForShares displayAddNewStockMenu " +
            "displayMainMenu ", olog.toString());
  }

  @Test
  public void testLoadPortfolio() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    NewModel model = new NewModelImpl();
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    View view = new MockView(log, olog);
    ByteArrayInputStream in;
    String input = "2 user5 2 college 7";
    in = new ByteArrayInputStream(input.getBytes());
    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();
    assertEquals("displayInitialMenu askForUsername displayMessage displayFlexibleMenu " +
            "askForPortfolioName displayMessage displayMainMenu ", olog.toString());
  }

  @Test
  public void testSwitchingBetweenPortfolio() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    NewModel model = new NewModelImpl();
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    View view = new MockView(log, olog);
    ByteArrayInputStream in;
    String input = "2 user5 2 college 2 retir 11";
    in = new ByteArrayInputStream(input.getBytes());
    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();
    assertEquals("displayInitialMenu askForUsername displayMessage displayFlexibleMenu " +
            "askForPortfolioName displayMessage displayMainMenu askForPortfolioName " +
            "displayMessage " +
            "displayFlexibleMenu ", olog.toString());
  }

  @Test
  public void testSave() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    NewModel model = new NewModelImpl();
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    View view = new MockView(log, olog);
    ByteArrayInputStream in;
    String input = "1 user1 1990.787 1 retirement 2 GOOG 10 1 ORCL 5 2 5 7";
    in = new ByteArrayInputStream(input.getBytes());
    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();
    assertEquals("displayInitialMenu askForUsername askForBalance displayMessage " +
            "displayFlexibleMenu askForPortfolioName displayPortfolioTypesMenu askForStockSymbol " +
            "askForShares displayAddNewStockMenu askForStockSymbol askForShares " +
            "displayAddNewStockMenu displayMainMenu displayListOfPortfolios " +
            "displayMainMenu ", olog.toString());
  }

  @Test
  public void testGetTotalValue() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    NewModel model = new NewModelImpl();
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    View view = new MockView(log, olog);
    ByteArrayInputStream in;
    String input = "2 user5 2 college 4 2022-10-31 7";
    in = new ByteArrayInputStream(input.getBytes());
    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();
    assertEquals("6963.9004 2022-10-31", log.toString());
    assertEquals("displayInitialMenu askForUsername displayMessage displayFlexibleMenu" +
            " askForPortfolioName displayMessage displayMainMenu askForDate displayValue" +
            " displayMainMenu ", olog.toString());
  }

  @Test
  public void testGetTotalValueFlexible() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    NewModel model = new NewModelImpl();
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    View view = new MockView(log, olog);
    ByteArrayInputStream in;
    String input = "2 user5 8 2022-10-31 11";
    in = new ByteArrayInputStream(input.getBytes());
    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();
    assertEquals("2653.9001 2022-10-31", log.toString());
    assertEquals("displayInitialMenu askForUsername displayMessage displayFlexibleMenu" +
            " askForDate displayValue displayFlexibleMenu ", olog.toString());
  }

  @Test
  public void testGetComposition() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    NewModel model = new NewModelImpl();
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    View view = new MockView(log, olog);
    ByteArrayInputStream in;
    String input = "2 user5 2 college 3 7";
    in = new ByteArrayInputStream(input.getBytes());
    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();
    String expectedValue = "                    college\n" +
            "MSFT                                    30.00\n";
    assertEquals(expectedValue, log.toString());
    assertEquals("displayInitialMenu askForUsername displayMessage displayFlexibleMenu" +
            " askForPortfolioName displayMessage displayMainMenu displayComposition " +
            "displayMainMenu ", olog.toString());
  }

  @Test
  public void testGetCompositionFlexible() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    NewModel model = new NewModelImpl();
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    View view = new MockView(log, olog);
    ByteArrayInputStream in;
    String input = "2 user5 7 2022-11-14 11";
    in = new ByteArrayInputStream(input.getBytes());
    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();
    String expectedValue = " Composition of retir upto 2022-11-14\n" +
            "GOOG                                    40.00\n" +
            "ORCL                                    10.00\n";
    assertEquals(expectedValue, log.toString());
    assertEquals("displayInitialMenu askForUsername displayMessage displayFlexibleMenu" +
            " askForDate displayComposition displayFlexibleMenu ", olog.toString());
  }

  @Test
  public void testGetCostBasis() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    NewModel model = new NewModelImpl();
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    View view = new MockView(log, olog);
    ByteArrayInputStream in;
    String input = "2 user5 6 2022-11-14 11";
    in = new ByteArrayInputStream(input.getBytes());
    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();
    assertEquals("2022-11-14 4924.0", log.toString());
    assertEquals("displayInitialMenu askForUsername displayMessage displayFlexibleMenu" +
            " askForDate displayCostBasis displayFlexibleMenu ", olog.toString());
  }

  @Test
  public void testGetPlot() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    NewModel model = new NewModelImpl();
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    View view = new MockView(log, olog);
    ByteArrayInputStream in;
    String input = "2 user5 9 2021-11-15 2022-11-14 11";
    in = new ByteArrayInputStream(input.getBytes());
    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();
    assertEquals("Performance of portfolio retir from Nov 2021 to Nov 2022\n" +
            "Nov 2021 : \n" +
            "Dec 2021 : \n" +
            "Jan 2022 : \n" +
            "Feb 2022 : \n" +
            "Mar 2022 : \n" +
            "Apr 2022 : \n" +
            "May 2022 : \n" +
            "Jun 2022 : \n" +
            "Jul 2022 : \n" +
            "Aug 2022 : *\n" +
            "Sep 2022 : *\n" +
            "Oct 2022 : *\n" +
            "Nov 2022 : **\n" +
            "Scale: * = $2000\n", log.toString());
    assertEquals("displayInitialMenu askForUsername displayMessage displayFlexibleMenu" +
            " askForStartDate askForEndDate displayComposition " +
            "displayFlexibleMenu ", olog.toString());
  }

}