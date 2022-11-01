package view;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ViewImplTest {
  private View view;

  @Test
  public void testDisplayInitialMenu(){
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    view = new ViewImpl(out);
    view.displayInitialMenu();
    String expectedResult = "To select a particular option,enter the number next to it\n" +
            "\n" +
            "1.Create User\n" +
            "2.Load User\n";
    assertEquals(expectedResult,bytes.toString());
  }

  @Test
  public void testaskForUsername(){
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    view = new ViewImpl(out);
    view.askForUsername();
    String expectedResult = "Enter username\n";
    assertEquals(expectedResult,bytes.toString());
  }

  @Test
  public void testAskForBalance(){
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    view = new ViewImpl(out);
    view.askForBalance();
    String expectedResult = "Enter initial balance\n";
    assertEquals(expectedResult,bytes.toString());
  }

  @Test
  public void testDisplayMessage(){
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    view = new ViewImpl(out);
    view.displayMessage("message");
    String expectedResult = "\n" +
            "message\n";
    assertEquals(expectedResult,bytes.toString());
  }

  @Test
  public void testDisplayMainMenu(){
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    view = new ViewImpl(out);
    view.displayMainMenu();
    String expectedResult = "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n";
    assertEquals(expectedResult,bytes.toString());
  }

  @Test
  public void testAskForPortfolioName(){
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    view = new ViewImpl(out);
    view.askForPortfolioName();
    String expectedResult = "Enter portfolio name\n";
    assertEquals(expectedResult,bytes.toString());
  }

  @Test
  public void testAskForStockSymbol(){
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    view = new ViewImpl(out);
    view.askForStockSymbol();
    String expectedResult = "Enter Stock Symbol\n";
    assertEquals(expectedResult,bytes.toString());
  }

  @Test
  public void testAskForShares(){
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    view = new ViewImpl(out);
    view.askForShares();
    String expectedResult = "Enter number of shares\n";
    assertEquals(expectedResult,bytes.toString());
  }

  @Test
  public void testAskForDate(){
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    view = new ViewImpl(out);
    view.askForDate();
    String expectedResult = "Enter date(yyyy-mm-dd)\n";
    assertEquals(expectedResult,bytes.toString());
  }

  @Test
  public void testDisplayValue(){
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    view = new ViewImpl(out);
    view.displayValue(198.8754f,"2022-10-28");
    String expectedResult = "\n" +
            "Total Value on 2022-10-28 is 198.8754\n" +
            "\n";
    assertEquals(expectedResult,bytes.toString());
  }

  @Test
  public void testDisplayComposition(){
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    view = new ViewImpl(out);
    view.displayComposition(new StringBuilder("retirement\n APPL\t10"));
    String expectedResult = "retirement\n" +
            " APPL\t10\n";
    assertEquals(expectedResult,bytes.toString());
  }

  @Test
  public void testDisplayAddNewStockMenu(){
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    view = new ViewImpl(out);
    view.displayAddNewStockMenu();
    String expectedResult = "1.Add new stock\n" +
            "2.Back to main menu\n";
    assertEquals(expectedResult,bytes.toString());
  }
}