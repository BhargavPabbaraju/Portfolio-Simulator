import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import controller.ControllerImpl;
import models.Model;
import models.ModelImpl;
import view.View;


import static org.junit.Assert.*;

public class ControllerIsolationForVIewTest {

  @Test
  public void testCreateUser() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    Model model = new ModelImpl();
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    View view = new MockView(log, olog);
    ByteArrayInputStream in;
    String input = "1 user 10000 6";
    in = new ByteArrayInputStream(input.getBytes());
    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.go();
    assertEquals("displayInitialMenu askForUsername askForBalance displayMessage displayMainMenu ", olog.toString());
  }

  @Test
  public void testLoadUser() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    Model model = new ModelImpl();
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    View view = new MockView(log, olog);
    ByteArrayInputStream in;
    String input = "2 user1 6";
    in = new ByteArrayInputStream(input.getBytes());
    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.go();
    assertEquals("displayInitialMenu askForUsername displayMessage displayMainMenu ", olog.toString());
  }

  @Test
  public void testCreatePortfolio() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    Model model = new ModelImpl();
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    View view = new MockView(log, olog);
    ByteArrayInputStream in;
    String input = "1 user 10000 1 personal AAPL 10 2 6";
    in = new ByteArrayInputStream(input.getBytes());
    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.go();
    assertEquals("displayInitialMenu askForUsername askForBalance displayMessage " +
            "displayMainMenu askForPortfolioName askForStockSymbol askForShares " +
            "displayAddNewStockMenu displayMainMenu ", olog.toString());
  }

  @Test
  public void testAddStockToPortfolio() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    Model model = new ModelImpl();
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    View view = new MockView(log, olog);
    ByteArrayInputStream in;
    String input = "1 user 10000 1 personal GOOGL 20 2 6";
    in = new ByteArrayInputStream(input.getBytes());
    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.go();
    assertEquals("displayInitialMenu askForUsername askForBalance displayMessage " +
            "displayMainMenu askForPortfolioName askForStockSymbol askForShares " +
            "displayAddNewStockMenu displayMainMenu ", olog.toString());
  }

  @Test
  public void testLoadPortfolio() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    Model model = new ModelImpl();
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    View view = new MockView(log, olog);
    ByteArrayInputStream in;
    String input = "1 user 10000 2 personal 6";
    in = new ByteArrayInputStream(input.getBytes());
    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.go();
    assertEquals("displayInitialMenu askForUsername askForBalance displayMessage " +
            "displayMainMenu askForPortfolioName displayMessage displayMainMenu ", olog.toString());
  }

  @Test
  public void testSave() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    Model model = new ModelImpl();
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    View view = new MockView(log, olog);
    ByteArrayInputStream in;
    String input = "2 user1 5 6";
    in = new ByteArrayInputStream(input.getBytes());
    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.go();
    assertEquals("displayInitialMenu askForUsername displayMessage displayMainMenu " +
            "displayMessage displayMainMenu ", olog.toString());
  }

  @Test
  public void testGetTotalValue() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    Model model = new ModelImpl();
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    View view = new MockView(log, olog);
    ByteArrayInputStream in;
    String input = "2 user1 4 2022-10-31 6";
    in = new ByteArrayInputStream(input.getBytes());
    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.go();
    assertEquals("26592.998 2022-10-31", log.toString());
    assertEquals("displayInitialMenu askForUsername displayMessage displayMainMenu " +
            "askForDate displayValue displayMainMenu ", olog.toString());
  }

  @Test
  public void testGetComposition() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    Model model = new ModelImpl();
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    View view = new MockView(log, olog);
    ByteArrayInputStream in;
    String input = "2 user1 3 6";
    in = new ByteArrayInputStream(input.getBytes());
    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.go();
    String expectedValue = "                    retirement\n" +
            "MSFT                                    30.00\n" +
            "META                                    30.00\n" +
            "GOOGL                                   30.00\n" +
            "IBM                                     20.00\n" +
            "ORCL                                    40.00\n" +
            "MRNA                                    30.00\n" +
            "AMZN                                    30.00\n";
    assertEquals(expectedValue, log.toString());
    assertEquals("displayInitialMenu askForUsername displayMessage displayMainMenu " +
            "displayComposition displayMainMenu ", olog.toString());
  }

}