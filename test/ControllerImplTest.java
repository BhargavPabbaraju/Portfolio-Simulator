import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import controller.ControllerImpl;
import models.Model;
import models.ModelImpl;
import view.View;
import view.ViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the Controller, View and Model together.
 */
public class ControllerImplTest {


  @Test
  public void testCreateExistingUser() {
    Model model = new ModelImpl();

    ByteArrayInputStream in;

    String input = "1 user1 1900 2 user1 6";
    in = new ByteArrayInputStream(input.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);

    View view = new ViewImpl(out);

    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();

    String expectedOutput = "To select a particular option,enter the number next to it\n" +
            "\n" +
            "1.Create User\n" +
            "2.Load User\n" +
            "Enter username\n" +
            "Enter initial balance\n" +
            "\n" +
            "User Already exists\n" +
            "To select a particular option,enter the number next to it\n" +
            "\n" +
            "1.Create User\n" +
            "2.Load User\n" +
            "Enter username\n" +
            "\n" +
            "User successfully loaded\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testCreateUserInvalidName() {
    Model model = new ModelImpl();

    ByteArrayInputStream in;

    String input = "1 user{][:,1 1900 2 user1 6";
    in = new ByteArrayInputStream(input.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);

    View view = new ViewImpl(out);

    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();

    String expectedOutput = "To select a particular option,enter the number next to it\n" +
            "\n" +
            "1.Create User\n" +
            "2.Load User\n" +
            "Enter username\n" +
            "Enter initial balance\n" +
            "\n" +
            "Username must not contain any of \\\"{}[],: characters.\n" +
            "To select a particular option,enter the number next to it\n" +
            "\n" +
            "1.Create User\n" +
            "2.Load User\n" +
            "Enter username\n" +
            "\n" +
            "User successfully loaded\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testCreateUserBalanceNegative() {
    Model model = new ModelImpl();

    ByteArrayInputStream in;

    String input = "1 user2 -1900 2 user1 6";
    in = new ByteArrayInputStream(input.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);

    View view = new ViewImpl(out);

    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();

    String expectedOutput = "To select a particular option,enter the number next to it\n" +
            "\n" +
            "1.Create User\n" +
            "2.Load User\n" +
            "Enter username\n" +
            "Enter initial balance\n" +
            "\n" +
            "Balance cannot be negative\n" +
            "To select a particular option,enter the number next to it\n" +
            "\n" +
            "1.Create User\n" +
            "2.Load User\n" +
            "Enter username\n" +
            "\n" +
            "User successfully loaded\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testCreateUserBalanceNAN() {
    Model model = new ModelImpl();

    ByteArrayInputStream in;

    String input = "1 user2 18sadahdaeb 2 user1 6";
    in = new ByteArrayInputStream(input.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);

    View view = new ViewImpl(out);

    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();

    String expectedOutput = "To select a particular option,enter the number next to it\n" +
            "\n" +
            "1.Create User\n" +
            "2.Load User\n" +
            "Enter username\n" +
            "Enter initial balance\n" +
            "\n" +
            "Balance must be a valid floating point number\n" +
            "To select a particular option,enter the number next to it\n" +
            "\n" +
            "1.Create User\n" +
            "2.Load User\n" +
            "Enter username\n" +
            "\n" +
            "User successfully loaded\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testCreateUserSuccess() {
    Model model = new ModelImpl();

    ByteArrayInputStream in;

    String input = "1 user2 1900 6";
    in = new ByteArrayInputStream(input.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);

    View view = new ViewImpl(out);

    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();

    String expectedOutput = "To select a particular option,enter the number next to it\n" +
            "\n" +
            "1.Create User\n" +
            "2.Load User\n" +
            "Enter username\n" +
            "Enter initial balance\n" +
            "\n" +
            "User successfully created\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testLoadNonExistingUser() {
    Model model = new ModelImpl();

    ByteArrayInputStream in;

    String input = "2 user2 2 user1 6";
    in = new ByteArrayInputStream(input.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);

    View view = new ViewImpl(out);

    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();

    String expectedOutput = "To select a particular option,enter the number next to it\n" +
            "\n" +
            "1.Create User\n" +
            "2.Load User\n" +
            "Enter username\n" +
            "\n" +
            "User doesn't exists\n" +
            "To select a particular option,enter the number next to it\n" +
            "\n" +
            "1.Create User\n" +
            "2.Load User\n" +
            "Enter username\n" +
            "\n" +
            "User successfully loaded\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testLoadUserInvalid() {
    Model model = new ModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 2 user1 6";
    in = new ByteArrayInputStream(input.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);

    View view = new ViewImpl(out);

    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();

    String expectedOutput = "To select a particular option,enter the number next to it\n" +
            "\n" +
            "1.Create User\n" +
            "2.Load User\n" +
            "Enter username\n" +
            "\n" +
            "Load file is not in valid format.\n" +
            "To select a particular option,enter the number next to it\n" +
            "\n" +
            "1.Create User\n" +
            "2.Load User\n" +
            "Enter username\n" +
            "\n" +
            "User successfully loaded\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testCreatePortfolioInvalidName() {
    Model model = new ModelImpl();

    ByteArrayInputStream in;

    String input = "1 user3 1900 1 retire:{54 6";
    in = new ByteArrayInputStream(input.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);

    View view = new ViewImpl(out);

    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();
    String expectedOutput = "To select a particular option,enter the number next to it\n" +
            "\n" +
            "1.Create User\n" +
            "2.Load User\n" +
            "Enter username\n" +
            "Enter initial balance\n" +
            "\n" +
            "User successfully created\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n" +
            "Enter portfolio name\n" +
            "\n" +
            "Portfolio name must not contain any of \\\"{}[],: characters.\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n";
    assertEquals(expectedOutput, bytes.toString());

  }

  @Test
  public void testCreatePortfolioExistingName() {
    Model model = new ModelImpl();

    ByteArrayInputStream in;

    String input = "2 user1 1 retirement 6";
    in = new ByteArrayInputStream(input.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);

    View view = new ViewImpl(out);

    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();
    String expectedOutput = "To select a particular option,enter the number next to it\n" +
            "\n" +
            "1.Create User\n" +
            "2.Load User\n" +
            "Enter username\n" +
            "\n" +
            "User successfully loaded\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n" +
            "Enter portfolio name\n" +
            "\n" +
            "Portfolio already exists\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testCreatePortfolio() {
    Model model = new ModelImpl();

    ByteArrayInputStream in;

    String input = "2 user1 1 personal AAPL 10 1 ORCL 20 2 6";
    in = new ByteArrayInputStream(input.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);

    View view = new ViewImpl(out);

    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();
    String expectedOutput = "To select a particular option,enter the number next to it\n" +
            "\n" +
            "1.Create User\n" +
            "2.Load User\n" +
            "Enter username\n" +
            "\n" +
            "User successfully loaded\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n" +
            "Enter portfolio name\n" +
            "Enter Stock Symbol\n" +
            "Enter number of shares\n" +
            "1.Add new stock\n" +
            "2.Back to main menu\n" +
            "Enter Stock Symbol\n" +
            "Enter number of shares\n" +
            "1.Add new stock\n" +
            "2.Back to main menu\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testLoadUserValid() {
    Model model = new ModelImpl();

    ByteArrayInputStream in;

    String input = "2 user1 6";
    in = new ByteArrayInputStream(input.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);

    View view = new ViewImpl(out);

    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();

    String expectedOutput = "To select a particular option,enter the number next to it\n" +
            "\n" +
            "1.Create User\n" +
            "2.Load User\n" +
            "Enter username\n" +
            "\n" +
            "User successfully loaded\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testLoadPortfolioInvalid() {
    Model model = new ModelImpl();

    ByteArrayInputStream in;

    String input = "2 user1 2 shares 6";
    in = new ByteArrayInputStream(input.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);

    View view = new ViewImpl(out);

    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();

    String expectedOutput = "To select a particular option,enter the number next to it\n" +
            "\n" +
            "1.Create User\n" +
            "2.Load User\n" +
            "Enter username\n" +
            "\n" +
            "User successfully loaded\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n" +
            "Enter portfolio name\n" +
            "\n" +
            "Portfolio doesn't exist\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testLoadPortfolioValid() {
    Model model = new ModelImpl();

    ByteArrayInputStream in;

    String input = "2 user1 2 college 6";
    in = new ByteArrayInputStream(input.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);

    View view = new ViewImpl(out);

    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();

    String expectedOutput = "To select a particular option,enter the number next to it\n" +
            "\n" +
            "1.Create User\n" +
            "2.Load User\n" +
            "Enter username\n" +
            "\n" +
            "User successfully loaded\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n" +
            "Enter portfolio name\n" +
            "\n" +
            "Loaded college\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testGetComposition() {
    Model model = new ModelImpl();

    ByteArrayInputStream in;

    String input = "2 user1 2 retirement 3 6";
    in = new ByteArrayInputStream(input.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);

    View view = new ViewImpl(out);

    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();

    String expectedOutput = "To select a particular option,enter the number next to it\n" +
            "\n" +
            "1.Create User\n" +
            "2.Load User\n" +
            "Enter username\n" +
            "\n" +
            "User successfully loaded\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n" +
            "Enter portfolio name\n" +
            "\n" +
            "Loaded retirement\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n" +
            "                    retirement\n" +
            "MSFT                                    30.00\n" +
            "META                                    30.00\n" +
            "GOOGL                                   30.00\n" +
            "IBM                                     20.00\n" +
            "ORCL                                    40.00\n" +
            "MRNA                                    30.00\n" +
            "AMZN                                    30.00\n" +
            "\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testSave() {
    Model model = new ModelImpl();

    ByteArrayInputStream in;

    String input = "1 user3 1990.787 1 retirement GOOG 10 1 ORCL 5 2 5 6";
    in = new ByteArrayInputStream(input.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);

    View view = new ViewImpl(out);

    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();

    String expectedOutput = "To select a particular option,enter the number next to it\n" +
            "\n" +
            "1.Create User\n" +
            "2.Load User\n" +
            "Enter username\n" +
            "Enter initial balance\n" +
            "\n" +
            "User successfully created\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n" +
            "Enter portfolio name\n" +
            "Enter Stock Symbol\n" +
            "Enter number of shares\n" +
            "1.Add new stock\n" +
            "2.Back to main menu\n" +
            "Enter Stock Symbol\n" +
            "Enter number of shares\n" +
            "1.Add new stock\n" +
            "2.Back to main menu\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n" +
            "\n" +
            "Successfully saved.\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testCreatePortfolioInvalidSymbol() {
    Model model = new ModelImpl();

    ByteArrayInputStream in;

    String input = "2 user1 1 personal ABCD 10 AAPL 2 2 6";
    in = new ByteArrayInputStream(input.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);

    View view = new ViewImpl(out);

    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();
    String expectedOutput = "To select a particular option,enter the number next to it\n" +
            "\n" +
            "1.Create User\n" +
            "2.Load User\n" +
            "Enter username\n" +
            "\n" +
            "User successfully loaded\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n" +
            "Enter portfolio name\n" +
            "Enter Stock Symbol\n" +
            "Enter number of shares\n" +
            "\n" +
            "ABCD Symbol not found\n" +
            "Enter Stock Symbol\n" +
            "Enter number of shares\n" +
            "1.Add new stock\n" +
            "2.Back to main menu\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testCreatePortfolioNegativeShare() {
    Model model = new ModelImpl();

    ByteArrayInputStream in;

    String input = "2 user1 1 personal AAPL -2 AAPL 10 2 6";
    in = new ByteArrayInputStream(input.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);

    View view = new ViewImpl(out);

    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();
    String expectedOutput = "To select a particular option,enter the number next to it\n" +
            "\n" +
            "1.Create User\n" +
            "2.Load User\n" +
            "Enter username\n" +
            "\n" +
            "User successfully loaded\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n" +
            "Enter portfolio name\n" +
            "Enter Stock Symbol\n" +
            "Enter number of shares\n" +
            "\n" +
            "Shares must be a valid positive integer\n" +
            "Enter Stock Symbol\n" +
            "Enter number of shares\n" +
            "1.Add new stock\n" +
            "2.Back to main menu\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testCreatePortfolioNonDigitShares() {
    Model model = new ModelImpl();

    ByteArrayInputStream in;

    String input = "2 user1 1 personal AAPL a AAPL 10 2 6";
    in = new ByteArrayInputStream(input.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);

    View view = new ViewImpl(out);

    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();
    String expectedOutput = "To select a particular option,enter the number next to it\n" +
            "\n" +
            "1.Create User\n" +
            "2.Load User\n" +
            "Enter username\n" +
            "\n" +
            "User successfully loaded\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n" +
            "Enter portfolio name\n" +
            "Enter Stock Symbol\n" +
            "Enter number of shares\n" +
            "\n" +
            "Shares must be a valid positive integer\n" +
            "Enter Stock Symbol\n" +
            "Enter number of shares\n" +
            "1.Add new stock\n" +
            "2.Back to main menu\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testCreatePortfolioFloatShares() {
    Model model = new ModelImpl();

    ByteArrayInputStream in;

    String input = "2 user1 1 personal AAPL 192.34 AAPL 10 2 6";
    in = new ByteArrayInputStream(input.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);

    View view = new ViewImpl(out);

    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();
    String expectedOutput = "To select a particular option,enter the number next to it\n" +
            "\n" +
            "1.Create User\n" +
            "2.Load User\n" +
            "Enter username\n" +
            "\n" +
            "User successfully loaded\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n" +
            "Enter portfolio name\n" +
            "Enter Stock Symbol\n" +
            "Enter number of shares\n" +
            "\n" +
            "Shares must be a valid positive integer\n" +
            "Enter Stock Symbol\n" +
            "Enter number of shares\n" +
            "1.Add new stock\n" +
            "2.Back to main menu\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testGetValueNormal() {
    Model model = new ModelImpl();

    ByteArrayInputStream in;

    String input = "2 user1 4 2022-10-31 6";
    in = new ByteArrayInputStream(input.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);

    View view = new ViewImpl(out);

    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();
    String expectedOutput = "To select a particular option,enter the number next to it\n" +
            "\n" +
            "1.Create User\n" +
            "2.Load User\n" +
            "Enter username\n" +
            "\n" +
            "User successfully loaded\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "\n" +
            "Total Value on 2022-10-31 is 6139.4\n" +
            "\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testGetOnWeekend() {
    Model model = new ModelImpl();

    ByteArrayInputStream in;

    String input = "2 user1 4 2022-10-30 2022-10-31 6";
    in = new ByteArrayInputStream(input.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);

    View view = new ViewImpl(out);

    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();
    String expectedOutput = "To select a particular option,enter the number next to it\n" +
            "\n" +
            "1.Create User\n" +
            "2.Load User\n" +
            "Enter username\n" +
            "\n" +
            "User successfully loaded\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "\n" +
            "Market is closed on weekend, date passed is 2022-10-30\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "\n" +
            "Total Value on 2022-10-31 is 6139.4\n" +
            "\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testGetOnFutureDate() {
    Model model = new ModelImpl();

    ByteArrayInputStream in;

    String input = "2 user1 4 2022-11-01 2022-10-31 6";
    in = new ByteArrayInputStream(input.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);

    View view = new ViewImpl(out);

    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();
    String expectedOutput = "To select a particular option,enter the number next to it\n" +
            "\n" +
            "1.Create User\n" +
            "2.Load User\n" +
            "Enter username\n" +
            "\n" +
            "User successfully loaded\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "\n" +
            "Total Value on 2022-11-01 is 6139.4\n" +
            "\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n" +
            "\n" +
            "Option must be one of the following numbers\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testGetOnWithoutPortfolio() {
    Model model = new ModelImpl();

    ByteArrayInputStream in;

    String input = "1 sree 10000 4 2022-10-31 6";
    in = new ByteArrayInputStream(input.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);

    View view = new ViewImpl(out);

    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();
    String expectedOutput = "To select a particular option,enter the number next to it\n" +
            "\n" +
            "1.Create User\n" +
            "2.Load User\n" +
            "Enter username\n" +
            "Enter initial balance\n" +
            "\n" +
            "User successfully created\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "\n" +
            "No portfolio created yet\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testGetOnInvalidTest() {
    Model model = new ModelImpl();

    ByteArrayInputStream in;

    String input = "2 user1 4 jkegrhbs  2022-10-31 6";
    in = new ByteArrayInputStream(input.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);

    View view = new ViewImpl(out);

    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();
    String expectedOutput = "To select a particular option,enter the number next to it\n" +
            "\n" +
            "1.Create User\n" +
            "2.Load User\n" +
            "Enter username\n" +
            "\n" +
            "User successfully loaded\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "\n" +
            "Text 'jkegrhbs' could not be parsed at index 0\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "\n" +
            "Total Value on 2022-10-31 is 6139.4\n" +
            "\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testCreatePortfolioShareZero() {
    Model model = new ModelImpl();

    ByteArrayInputStream in;

    String input = "1 sree 10000 1 personal AAPL 0 AAPL 10 2 6";
    in = new ByteArrayInputStream(input.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);

    View view = new ViewImpl(out);

    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();
    String expectedOutput = "To select a particular option,enter the number next to it\n" +
            "\n" +
            "1.Create User\n" +
            "2.Load User\n" +
            "Enter username\n" +
            "Enter initial balance\n" +
            "\n" +
            "User successfully created\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n" +
            "Enter portfolio name\n" +
            "Enter Stock Symbol\n" +
            "Enter number of shares\n" +
            "\n" +
            "Shares must be a valid positive integer\n" +
            "Enter Stock Symbol\n" +
            "Enter number of shares\n" +
            "1.Add new stock\n" +
            "2.Back to main menu\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testCreatePortfolioSameStockTwice() {
    Model model = new ModelImpl();

    ByteArrayInputStream in;

    String input = "1 sree 10000 1 personal AAPL 10 1 ORCL 10 1 ORCL 30 2 3 6";
    in = new ByteArrayInputStream(input.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);

    View view = new ViewImpl(out);

    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();
    String expectedOutput = "To select a particular option,enter the number next to it\n" +
            "\n" +
            "1.Create User\n" +
            "2.Load User\n" +
            "Enter username\n" +
            "Enter initial balance\n" +
            "\n" +
            "User successfully created\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n" +
            "Enter portfolio name\n" +
            "Enter Stock Symbol\n" +
            "Enter number of shares\n" +
            "1.Add new stock\n" +
            "2.Back to main menu\n" +
            "Enter Stock Symbol\n" +
            "Enter number of shares\n" +
            "1.Add new stock\n" +
            "2.Back to main menu\n" +
            "Enter Stock Symbol\n" +
            "Enter number of shares\n" +
            "1.Add new stock\n" +
            "2.Back to main menu\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n" +
            "                    personal\n" +
            "AAPL                                    10.00\n" +
            "ORCL                                    40.00\n" +
            "\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }
}