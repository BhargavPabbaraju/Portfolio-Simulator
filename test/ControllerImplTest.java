import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import controller.ControllerImpl;
import models.NewModel;
import models.NewModelImpl;
import view.View;
import view.ViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the Controller, View and NewModel together.
 */
public class ControllerImplTest {


  @Test
  public void testCreateExistingUser() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "1 user3 1 user11 1900 11";
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
            "User already exists\n" +
            "To select a particular option,enter the number next to it\n" +
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testCreateUserInvalidName() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "1 user{][:,1 1900 2 user5 11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testCreateUserBalanceNegative() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "1 user2 -1900 2 user5 11";
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
            "Enter initial balance\n" +
            "\n" +
            "User successfully created\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "\n" +
            "Option must be one of the following numbers\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testCreateUserBalanceNAN() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "1 user2 18sadahdaeb 2 user5 11";
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
            "Enter initial balance\n" +
            "\n" +
            "User successfully created\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "\n" +
            "Option must be one of the following numbers\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testCreateUserSuccess() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "1 user2 1900 11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testLoadNonExistingUser() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user2 2 user5 11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testLoadUserInvalid() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user10 2 user5 11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testCreatePortfolioInvalidName() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "1 user1 1900 1 retire:{54 11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter portfolio name\n" +
            "\n" +
            "Portfolio name must not contain any of \\\"{}[],: characters.\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());

  }

  @Test
  public void testCreatePortfolioExistingName() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 1 retir 11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter portfolio name\n" +
            "\n" +
            "Portfolio already exists\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testCreateFlexiblePortfolio() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 1 personal 1 AAPL 1 2022-10-11 10 11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter portfolio name\n" +
            "Select which kind of portfolio you wish to create\n" +
            "1.Flexible Portfolio\n" +
            "2.Inflexible Portfolio\n" +
            "Enter Stock Symbol\n" +
            "Enter transaction cost\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "Enter number of shares\n" +
            "\n" +
            "Successfully bought stocks\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testCreateInFlexiblePortfolio() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 1 personal 2 AAPL 10 1 ORCL 20 2 7";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter portfolio name\n" +
            "Select which kind of portfolio you wish to create\n" +
            "1.Flexible Portfolio\n" +
            "2.Inflexible Portfolio\n" +
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
            "5.View list of portfolios\n" +
            "6.Save\n" +
            "7.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testLoadUserValid() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testLoadPortfolioInvalid() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 2 shares 11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter portfolio name\n" +
            "\n" +
            "Portfolio doesn't exist\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testLoadPortfolioValid() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 2 college 7";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter portfolio name\n" +
            "\n" +
            "Loaded college\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.View list of portfolios\n" +
            "6.Save\n" +
            "7.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testGetCompositionInFlexiblePortfolio() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 2 college 3 7";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter portfolio name\n" +
            "\n" +
            "Loaded college\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.View list of portfolios\n" +
            "6.Save\n" +
            "7.Exit\n" +
            "                    college\n" +
            "MSFT                                    30.00\n" +
            "\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.View list of portfolios\n" +
            "6.Save\n" +
            "7.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testSave() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "1 user1 1990.787 1 retirement 2 GOOG 10 1 ORCL 5 2 5 6";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter portfolio name\n" +
            "Select which kind of portfolio you wish to create\n" +
            "1.Flexible Portfolio\n" +
            "2.Inflexible Portfolio\n" +
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
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 1 personal 2 ABCD AAPL 2 2 7";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter portfolio name\n" +
            "Select which kind of portfolio you wish to create\n" +
            "1.Flexible Portfolio\n" +
            "2.Inflexible Portfolio\n" +
            "Enter Stock Symbol\n" +
            "\n" +
            "Invalid symbol\n" +
            "Enter Stock Symbol\n" +
            "Enter number of shares\n" +
            "1.Add new stock\n" +
            "2.Back to main menu\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.View list of portfolios\n" +
            "6.Save\n" +
            "7.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testCreatePortfolioNegativeShare() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 1 personal 2 AAPL -2 10 2 7";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter portfolio name\n" +
            "Select which kind of portfolio you wish to create\n" +
            "1.Flexible Portfolio\n" +
            "2.Inflexible Portfolio\n" +
            "Enter Stock Symbol\n" +
            "Enter number of shares\n" +
            "\n" +
            "Shares must be a valid positive integer\n" +
            "Enter number of shares\n" +
            "1.Add new stock\n" +
            "2.Back to main menu\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.View list of portfolios\n" +
            "6.Save\n" +
            "7.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testCreateFlexiblePortfolioNegativeShare() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;
    String input = "2 user5 1 personal 1 AAPL 10 2022-11-14 -10 10 11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter portfolio name\n" +
            "Select which kind of portfolio you wish to create\n" +
            "1.Flexible Portfolio\n" +
            "2.Inflexible Portfolio\n" +
            "Enter Stock Symbol\n" +
            "Enter transaction cost\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "Enter number of shares\n" +
            "\n" +
            "Shares must be a valid positive integer\n" +
            "Enter number of shares\n" +
            "\n" +
            "Successfully bought stocks\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testCreatePortfolioNonDigitShares() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 1 personal 2 AAPL a 10 2 7";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter portfolio name\n" +
            "Select which kind of portfolio you wish to create\n" +
            "1.Flexible Portfolio\n" +
            "2.Inflexible Portfolio\n" +
            "Enter Stock Symbol\n" +
            "Enter number of shares\n" +
            "\n" +
            "Shares must be a valid positive integer\n" +
            "Enter number of shares\n" +
            "1.Add new stock\n" +
            "2.Back to main menu\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.View list of portfolios\n" +
            "6.Save\n" +
            "7.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testCreateFlexiblePortfolioNonDigitShares() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 1 personal 1 AAPL 10 2022-11-14 a 10 11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter portfolio name\n" +
            "Select which kind of portfolio you wish to create\n" +
            "1.Flexible Portfolio\n" +
            "2.Inflexible Portfolio\n" +
            "Enter Stock Symbol\n" +
            "Enter transaction cost\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "Enter number of shares\n" +
            "\n" +
            "Shares must be a valid positive integer\n" +
            "Enter number of shares\n" +
            "\n" +
            "Successfully bought stocks\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testCreateFlexiblePortfolioFloatShares() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;
    String input = "2 user5 1 personal 1 AAPL 10 2022-11-14 123.23 10 11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter portfolio name\n" +
            "Select which kind of portfolio you wish to create\n" +
            "1.Flexible Portfolio\n" +
            "2.Inflexible Portfolio\n" +
            "Enter Stock Symbol\n" +
            "Enter transaction cost\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "Enter number of shares\n" +
            "\n" +
            "Shares must be a valid positive integer\n" +
            "Enter number of shares\n" +
            "\n" +
            "Successfully bought stocks\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testCreatePortfolioFloatShares() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 1 personal 2 AAPL 192.34 10 2 7";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter portfolio name\n" +
            "Select which kind of portfolio you wish to create\n" +
            "1.Flexible Portfolio\n" +
            "2.Inflexible Portfolio\n" +
            "Enter Stock Symbol\n" +
            "Enter number of shares\n" +
            "\n" +
            "Shares must be a valid positive integer\n" +
            "Enter number of shares\n" +
            "1.Add new stock\n" +
            "2.Back to main menu\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.View list of portfolios\n" +
            "6.Save\n" +
            "7.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testCreateFlexiblePortfolioNegativeTransactionValue() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 1 personal 1 AAPL -10 10 2022-11-14 a 10 11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter portfolio name\n" +
            "Select which kind of portfolio you wish to create\n" +
            "1.Flexible Portfolio\n" +
            "2.Inflexible Portfolio\n" +
            "Enter Stock Symbol\n" +
            "Enter transaction cost\n" +
            "\n" +
            "Transaction Cost must be a valid floating point number\n" +
            "Enter transaction cost\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "Enter number of shares\n" +
            "\n" +
            "Shares must be a valid positive integer\n" +
            "Enter number of shares\n" +
            "\n" +
            "Successfully bought stocks\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testCreateFlexiblePortfolioNonDigitTransactionValue() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 1 personal 1 AAPL a 10 2022-11-14 a 10 11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter portfolio name\n" +
            "Select which kind of portfolio you wish to create\n" +
            "1.Flexible Portfolio\n" +
            "2.Inflexible Portfolio\n" +
            "Enter Stock Symbol\n" +
            "Enter transaction cost\n" +
            "\n" +
            "Transaction Cost must be a valid floating point number\n" +
            "Enter transaction cost\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "Enter number of shares\n" +
            "\n" +
            "Shares must be a valid positive integer\n" +
            "Enter number of shares\n" +
            "\n" +
            "Successfully bought stocks\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testCreateFlexiblePortfolioFloatTransactionValue() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 1 personal 1 AAPL 10.12 2022-11-14 a 10 11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter portfolio name\n" +
            "Select which kind of portfolio you wish to create\n" +
            "1.Flexible Portfolio\n" +
            "2.Inflexible Portfolio\n" +
            "Enter Stock Symbol\n" +
            "Enter transaction cost\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "Enter number of shares\n" +
            "\n" +
            "Shares must be a valid positive integer\n" +
            "Enter number of shares\n" +
            "\n" +
            "Successfully bought stocks\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testGetValueNormal() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 2 college 4 2022-10-31 7";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter portfolio name\n" +
            "\n" +
            "Loaded college\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.View list of portfolios\n" +
            "6.Save\n" +
            "7.Exit\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "\n" +
            "Total Value on 2022-10-31 is $6963.9004\n" +
            "\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.View list of portfolios\n" +
            "6.Save\n" +
            "7.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testGetValueNormalFlexible() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 8 2022-10-31 11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "\n" +
            "Total Value on 2022-10-31 is $2653.9001\n" +
            "\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testGetOnWeekend() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 2 college 4 2022-10-30 2022-10-31 7";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter portfolio name\n" +
            "\n" +
            "Loaded college\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.View list of portfolios\n" +
            "6.Save\n" +
            "7.Exit\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "\n" +
            "Date cannot be a weekend\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "\n" +
            "Total Value on 2022-10-31 is $6963.9004\n" +
            "\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.View list of portfolios\n" +
            "6.Save\n" +
            "7.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testGetOnWeekendFlexible() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 8 2022-10-31 2022-10-31 11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "\n" +
            "Total Value on 2022-10-31 is $2653.9001\n" +
            "\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "\n" +
            "Option must be one of the following numbers\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testGetOnFutureDate() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 2 college 4 2022-12-01 2022-10-31 7";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter portfolio name\n" +
            "\n" +
            "Loaded college\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.View list of portfolios\n" +
            "6.Save\n" +
            "7.Exit\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "\n" +
            "Date cannot be a future date\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "\n" +
            "Total Value on 2022-10-31 is $6963.9004\n" +
            "\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.View list of portfolios\n" +
            "6.Save\n" +
            "7.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testGetOnFutureDateFlexible() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 8 2022-12-01 2022-10-31 11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "\n" +
            "Date cannot be a future date\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "\n" +
            "Total Value on 2022-10-31 is $2653.9001\n" +
            "\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testGetOnWithoutPortfolio() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "1 sree 10000 8 11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "\n" +
            "You should create at least one portfolio\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testGetOnInvalidTest() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 2 college 4 jkegrhbs  2022-10-31 7";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter portfolio name\n" +
            "\n" +
            "Loaded college\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.View list of portfolios\n" +
            "6.Save\n" +
            "7.Exit\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "\n" +
            "Date must be in yyyy-mm-dd format\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "\n" +
            "Total Value on 2022-10-31 is $6963.9004\n" +
            "\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.View list of portfolios\n" +
            "6.Save\n" +
            "7.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testGetOnInvalidDateFlexible() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 8 2022-adsfs-01 2022-10-31 11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "\n" +
            "Date must be in yyyy-mm-dd format\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "\n" +
            "Total Value on 2022-10-31 is $2653.9001\n" +
            "\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testCreatePortfolioShareZero() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;
    String input = "2 user5 1 personal 2 AAPL 0 10 2 7";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter portfolio name\n" +
            "Select which kind of portfolio you wish to create\n" +
            "1.Flexible Portfolio\n" +
            "2.Inflexible Portfolio\n" +
            "Enter Stock Symbol\n" +
            "Enter number of shares\n" +
            "\n" +
            "Shares must be a valid positive integer\n" +
            "Enter number of shares\n" +
            "1.Add new stock\n" +
            "2.Back to main menu\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.View list of portfolios\n" +
            "6.Save\n" +
            "7.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testCreateFlexiblePortfolioShareZero() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;
    String input = "2 user5 1 personal 1 AAPL 10 2022-11-14 0 10 11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter portfolio name\n" +
            "Select which kind of portfolio you wish to create\n" +
            "1.Flexible Portfolio\n" +
            "2.Inflexible Portfolio\n" +
            "Enter Stock Symbol\n" +
            "Enter transaction cost\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "Enter number of shares\n" +
            "\n" +
            "Shares must be a valid positive integer\n" +
            "Enter number of shares\n" +
            "\n" +
            "Successfully bought stocks\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testCreateFlexiblePortfolioInvalidDate() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;
    String input = "2 user5 1 personal 1 AAPL 10 2022-11-1412 2022-11-14 10 11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter portfolio name\n" +
            "Select which kind of portfolio you wish to create\n" +
            "1.Flexible Portfolio\n" +
            "2.Inflexible Portfolio\n" +
            "Enter Stock Symbol\n" +
            "Enter transaction cost\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "\n" +
            "Date must be in yyyy-mm-dd format\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "Enter number of shares\n" +
            "\n" +
            "Successfully bought stocks\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testCreateFlexiblePortfolioFutureDate() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;
    String input = "2 user5 1 personal 1 AAPL 10 2022-11-18 2022-11-14 10 11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter portfolio name\n" +
            "Select which kind of portfolio you wish to create\n" +
            "1.Flexible Portfolio\n" +
            "2.Inflexible Portfolio\n" +
            "Enter Stock Symbol\n" +
            "Enter transaction cost\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "\n" +
            "Date cannot be a future date\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "Enter number of shares\n" +
            "\n" +
            "Successfully bought stocks\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }


  @Test
  public void testCreateFlexiblePortfolioWeekendDate() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;
    String input = "2 user5 1 personal 1 AAPL 10 2022-11-13 2022-11-14 10 11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter portfolio name\n" +
            "Select which kind of portfolio you wish to create\n" +
            "1.Flexible Portfolio\n" +
            "2.Inflexible Portfolio\n" +
            "Enter Stock Symbol\n" +
            "Enter transaction cost\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "\n" +
            "Date cannot be a weekend\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "Enter number of shares\n" +
            "\n" +
            "Successfully bought stocks\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testCreateFlexiblePortfolioSameStockDiffDate() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;
    String input = "2 user5 1 personal 1 AAPL 10 2022-11-10 10 3 AAPL 10 2022-11-14 20 7 " +
            "2022-11-14 11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter portfolio name\n" +
            "Select which kind of portfolio you wish to create\n" +
            "1.Flexible Portfolio\n" +
            "2.Inflexible Portfolio\n" +
            "Enter Stock Symbol\n" +
            "Enter transaction cost\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "Enter number of shares\n" +
            "\n" +
            "Successfully bought stocks\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter Stock Symbol\n" +
            "Enter transaction cost\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "Enter number of shares\n" +
            "\n" +
            "Successfully bought stocks\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter date(yyyy-mm-dd)\n" +
            " Composition of personal upto 2022-11-14\n" +
            "AAPL                                    30.00\n" +
            "\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testCreateFlexiblePortfolioSameStockSameDate() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;
    String input = "2 user5 1 personal 1 AAPL 10 2022-11-10 10 3 AAPL 10 2022-11-10 20 7 " +
            "2022-11-14 11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter portfolio name\n" +
            "Select which kind of portfolio you wish to create\n" +
            "1.Flexible Portfolio\n" +
            "2.Inflexible Portfolio\n" +
            "Enter Stock Symbol\n" +
            "Enter transaction cost\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "Enter number of shares\n" +
            "\n" +
            "Successfully bought stocks\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter Stock Symbol\n" +
            "Enter transaction cost\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "Enter number of shares\n" +
            "\n" +
            "Successfully bought stocks\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter date(yyyy-mm-dd)\n" +
            " Composition of personal upto 2022-11-14\n" +
            "AAPL                                    30.00\n" +
            "\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testCreatePortfolioSameStockTwice() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "1 sree 10000 1 personal 2 AAPL 10 1 ORCL 10 1 ORCL 30 2 3 7";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter portfolio name\n" +
            "Select which kind of portfolio you wish to create\n" +
            "1.Flexible Portfolio\n" +
            "2.Inflexible Portfolio\n" +
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
            "5.View list of portfolios\n" +
            "6.Save\n" +
            "7.Exit\n" +
            "                    personal\n" +
            "AAPL                                    10.00\n" +
            "ORCL                                    40.00\n" +
            "\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.View list of portfolios\n" +
            "6.Save\n" +
            "7.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testCostBasis() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 6 2022-11-14 11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "\n" +
            "Cost basis till 2022-11-14 is $4924.0\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testCostBasisBefore() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 6 2020-11-13 11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "\nCost basis till 2020-11-13 is $0.0\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testCostBasisWeekend() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 6 2020-11-14 2022-11-14 11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "\n" +
            "Date cannot be a weekend\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "\n" +
            "Cost basis till 2022-11-14 is $4924.0\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testCostBasisInvalid() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 6 2020-11-14634 2022-11-14 11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "\n" +
            "Date must be in yyyy-mm-dd format\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "\n" +
            "Cost basis till 2022-11-14 is $4924.0\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testComposition() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 7 2022-11-14 11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter date(yyyy-mm-dd)\n" +
            " Composition of retir upto 2022-11-14\n" +
            "GOOG                                    40.00\n" +
            "ORCL                                    10.00\n" +
            "\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testCompositionBefore() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 7 2020-11-13 11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "Portfolio is empty at this date.\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testCompositionInvalid() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 7 2020-11-13234a 2022-11-14 11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "\n" +
            "Date must be in yyyy-mm-dd format\n" +
            "Enter date(yyyy-mm-dd)\n" +
            " Composition of retir upto 2022-11-14\n" +
            "GOOG                                    40.00\n" +
            "ORCL                                    10.00\n" +
            "\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testCompositionWeekend() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 7 2022-11-13 2022-11-14 11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "\n" +
            "Date cannot be a weekend\n" +
            "Enter date(yyyy-mm-dd)\n" +
            " Composition of retir upto 2022-11-14\n" +
            "GOOG                                    40.00\n" +
            "ORCL                                    10.00\n" +
            "\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testBuyStock() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 3 AAPL 10 2022-11-04 10 11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter Stock Symbol\n" +
            "Enter transaction cost\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "Enter number of shares\n" +
            "\n" +
            "Successfully bought stocks\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testBuyStockInvalidSymbol() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 3 AsP AAPL 10 2022-11-04 10 11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter Stock Symbol\n" +
            "\n" +
            "Invalid symbol\n" +
            "Enter Stock Symbol\n" +
            "Enter transaction cost\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "Enter number of shares\n" +
            "\n" +
            "Successfully bought stocks\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testBuyStockWeekend() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 3 AsP AAPL 10 2022-11-06 2022-11-04 10 11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter Stock Symbol\n" +
            "\n" +
            "Invalid symbol\n" +
            "Enter Stock Symbol\n" +
            "Enter transaction cost\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "\n" +
            "Date cannot be a weekend\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "Enter number of shares\n" +
            "\n" +
            "Successfully bought stocks\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testBuyStockFutureDate() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 3 AAPL 10 2022-11-22 2022-11-04 10 11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter Stock Symbol\n" +
            "Enter transaction cost\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "\n" +
            "Date cannot be a future date\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "Enter number of shares\n" +
            "\n" +
            "Successfully bought stocks\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testBuyStockInvalidDate() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 3 AsP AAPL 10 2022-11-2asd2 2022-11-04 10 11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter Stock Symbol\n" +
            "\n" +
            "Invalid symbol\n" +
            "Enter Stock Symbol\n" +
            "Enter transaction cost\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "\n" +
            "Date must be in yyyy-mm-dd format\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "Enter number of shares\n" +
            "\n" +
            "Successfully bought stocks\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testSellStock() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 4 GOOG 10 2022-11-14 5 11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter Stock Symbol\n" +
            "Enter transaction cost\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "Enter number of shares\n" +
            "\n" +
            "Successfully sold stocks\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testSellStockInvalidSymbol() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 4 GOO GOOG 10 2022-11-14 5 11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter Stock Symbol\n" +
            "\n" +
            "Invalid symbol\n" +
            "Enter Stock Symbol\n" +
            "Enter transaction cost\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "Enter number of shares\n" +
            "\n" +
            "Successfully sold stocks\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testSellStockBeforeBuying() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 4 GOOG 10 2020-11-13 5  11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter Stock Symbol\n" +
            "Enter transaction cost\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "Enter number of shares\n" +
            "\n" +
            "You need to buy a stock before you sell\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testSellMoreStocks() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 4 GOOG 10 2022-11-14 500  11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter Stock Symbol\n" +
            "Enter transaction cost\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "Enter number of shares\n" +
            "\n" +
            "You have only 40.0 of GOOG after the last transaction\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testSellingFutureDate() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 4 GOOG 10 2022-11-30 2022-11-14 5 11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter Stock Symbol\n" +
            "Enter transaction cost\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "\n" +
            "Date cannot be a future date\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "Enter number of shares\n" +
            "\n" +
            "Successfully sold stocks\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testSellingWeekendDate() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 4 GOOG 10 2022-11-13 2022-11-14 5 11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter Stock Symbol\n" +
            "Enter transaction cost\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "\n" +
            "Date cannot be a weekend\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "Enter number of shares\n" +
            "\n" +
            "Successfully sold stocks\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testSellingInvalidDate() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 4 GOOG 10 2022-11-1as3 2022-11-14 5 11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter Stock Symbol\n" +
            "Enter transaction cost\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "\n" +
            "Date must be in yyyy-mm-dd format\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "Enter number of shares\n" +
            "\n" +
            "Successfully sold stocks\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testSellingbuyingsyncronously1() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 1 abcd 1 AAPL 10 2022-01-03 50 4 AAPL 10 2022-01-10 50 4 "
            + "AAPL 10 2022-01-06 30 11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter portfolio name\n" +
            "Select which kind of portfolio you wish to create\n" +
            "1.Flexible Portfolio\n" +
            "2.Inflexible Portfolio\n" +
            "Enter Stock Symbol\n" +
            "Enter transaction cost\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "Enter number of shares\n" +
            "\n" +
            "Successfully bought stocks\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter Stock Symbol\n" +
            "Enter transaction cost\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "Enter number of shares\n" +
            "\n" +
            "Successfully sold stocks\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter Stock Symbol\n" +
            "Enter transaction cost\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "Enter number of shares\n" +
            "\n" +
            "You have only 0.0 of AAPL after the last transaction\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testSellingbuyingsyncronously2() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 1 abcd 1 AAPL 10 2022-01-03 50 4 AAPL 10 2022-01-04 40 3 AAPL 10 " +
            "2022-01-10 30 4 AAPL 10 2022-01-04 30 11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter portfolio name\n" +
            "Select which kind of portfolio you wish to create\n" +
            "1.Flexible Portfolio\n" +
            "2.Inflexible Portfolio\n" +
            "Enter Stock Symbol\n" +
            "Enter transaction cost\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "Enter number of shares\n" +
            "\n" +
            "Successfully bought stocks\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter Stock Symbol\n" +
            "Enter transaction cost\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "Enter number of shares\n" +
            "\n" +
            "Successfully sold stocks\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter Stock Symbol\n" +
            "Enter transaction cost\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "Enter number of shares\n" +
            "\n" +
            "Successfully bought stocks\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter Stock Symbol\n" +
            "Enter transaction cost\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "Enter number of shares\n" +
            "\n" +
            "You have only 10.0 of AAPL before the entered date\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testSellingbuyingsameday() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 1 abcd 1 AAPL 10 2022-01-03 50 4 AAPL 10 2022-01-03 40 11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter portfolio name\n" +
            "Select which kind of portfolio you wish to create\n" +
            "1.Flexible Portfolio\n" +
            "2.Inflexible Portfolio\n" +
            "Enter Stock Symbol\n" +
            "Enter transaction cost\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "Enter number of shares\n" +
            "\n" +
            "Successfully bought stocks\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter Stock Symbol\n" +
            "Enter transaction cost\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "Enter number of shares\n" +
            "\n" +
            "Successfully sold stocks\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testPlotDaily() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 1 abcd 1 AAPL 10 2022-01-03 50 4 AAPL 10 2022-01-04 40 3 GOOG 10 "
            + "2022-01-05 10 9 2021-12-31 2022-01-05 11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter portfolio name\n" +
            "Select which kind of portfolio you wish to create\n" +
            "1.Flexible Portfolio\n" +
            "2.Inflexible Portfolio\n" +
            "Enter Stock Symbol\n" +
            "Enter transaction cost\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "Enter number of shares\n" +
            "\n" +
            "Successfully bought stocks\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter Stock Symbol\n" +
            "Enter transaction cost\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "Enter number of shares\n" +
            "\n" +
            "Successfully sold stocks\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter Stock Symbol\n" +
            "Enter transaction cost\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "Enter number of shares\n" +
            "\n" +
            "Successfully bought stocks\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter start date(yyyy-mm-dd)\n" +
            "Enter end date(yyyy-mm-dd)\n" +
            "Performance of portfolio abcd from Dec 31 to Jan 05\n" +
            "Dec 31 : \n" +
            "Jan 03 : *********\n" +
            "Jan 04 : *\n" +
            "Jan 05 : *****************************\n" +
            "Scale: * = $1000\n" +
            "\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testPlotYears() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 1 abcd 1 AAPL 10 2015-12-03 10 4 AAPL 10 2020-05-06 5 3 GOOG 10 "
            + "2021-11-01 10 9 2013-12-03 2022-11-04 11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter portfolio name\n" +
            "Select which kind of portfolio you wish to create\n" +
            "1.Flexible Portfolio\n" +
            "2.Inflexible Portfolio\n" +
            "Enter Stock Symbol\n" +
            "Enter transaction cost\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "Enter number of shares\n" +
            "\n" +
            "Successfully bought stocks\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter Stock Symbol\n" +
            "Enter transaction cost\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "Enter number of shares\n" +
            "\n" +
            "Successfully sold stocks\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter Stock Symbol\n" +
            "Enter transaction cost\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "Enter number of shares\n" +
            "\n" +
            "Successfully bought stocks\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter start date(yyyy-mm-dd)\n" +
            "Enter end date(yyyy-mm-dd)\n" +
            "Performance of portfolio abcd from 2013 to 2022\n" +
            "2013 : \n" +
            "2014 : \n" +
            "2015 : *\n" +
            "2016 : *\n" +
            "2017 : **\n" +
            "2018 : **\n" +
            "2019 : ****\n" +
            "2020 : *\n" +
            "2021 : *************************************************\n" +
            "2022 : **\n" +
            "Scale: * = $600\n" +
            "\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }


  @Test
  public void testPlotMonthsOfYears() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 1 abcd 1 AAPL 10 2019-12-03 10 4 AAPL 10 2020-05-06 5 3 GOOG 10 "
            + "2021-11-01 10 9 2019-12-03 2022-11-04 11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter portfolio name\n" +
            "Select which kind of portfolio you wish to create\n" +
            "1.Flexible Portfolio\n" +
            "2.Inflexible Portfolio\n" +
            "Enter Stock Symbol\n" +
            "Enter transaction cost\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "Enter number of shares\n" +
            "\n" +
            "Successfully bought stocks\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter Stock Symbol\n" +
            "Enter transaction cost\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "Enter number of shares\n" +
            "\n" +
            "Successfully sold stocks\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter Stock Symbol\n" +
            "Enter transaction cost\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "Enter number of shares\n" +
            "\n" +
            "Successfully bought stocks\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter start date(yyyy-mm-dd)\n" +
            "Enter end date(yyyy-mm-dd)\n" +
            "Performance of portfolio abcd from Dec 2019 to Nov 2022\n" +
            "Dec 2019 : *****\n" +
            "Mar 2020 : *****\n" +
            "Jun 2020 : ***\n" +
            "Sep 2020 : *\n" +
            "Dec 2020 : *\n" +
            "Mar 2021 : *\n" +
            "Jun 2021 : *\n" +
            "Sep 2021 : *\n" +
            "Dec 2021 : **************************************************($29793.75)\n" +
            "Mar 2022 : **************************************************($28772.95)\n" +
            "Jun 2022 : *********************************************\n" +
            "Sep 2022 : ***\n" +
            "Nov 2022 : ***\n" +
            "Scale: * = $500\n" +
            "Maximum plot length is 50 *'s. Remaining *'s are truncated\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testPlotMonthly() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 1 abcd 1 AAPL 10 2022-01-03 50 4 AAPL 10 2022-05-03 40 3 GOOG 10 "
            + "2022-11-01 30 9 2022-01-04 2022-11-04 11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter portfolio name\n" +
            "Select which kind of portfolio you wish to create\n" +
            "1.Flexible Portfolio\n" +
            "2.Inflexible Portfolio\n" +
            "Enter Stock Symbol\n" +
            "Enter transaction cost\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "Enter number of shares\n" +
            "\n" +
            "Successfully bought stocks\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter Stock Symbol\n" +
            "Enter transaction cost\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "Enter number of shares\n" +
            "\n" +
            "Successfully sold stocks\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter Stock Symbol\n" +
            "Enter transaction cost\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "Enter number of shares\n" +
            "\n" +
            "Successfully bought stocks\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter start date(yyyy-mm-dd)\n" +
            "Enter end date(yyyy-mm-dd)\n" +
            "Performance of portfolio abcd from Jan 2022 to Nov 2022\n" +
            "Jan 2022 : ********\n" +
            "Feb 2022 : ********\n" +
            "Mar 2022 : ********\n" +
            "Apr 2022 : *******\n" +
            "May 2022 : *\n" +
            "Jun 2022 : *\n" +
            "Jul 2022 : *\n" +
            "Aug 2022 : *\n" +
            "Sep 2022 : *\n" +
            "Oct 2022 : *\n" +
            "Nov 2022 : ***\n" +
            "Scale: * = $1000\n" +
            "\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testPlotDaysofMonths() {
    NewModel model = new NewModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 1 abcd 1 AAPL 10 2022-01-03 50 4 AAPL 10 2022-03-03 40 3 GOOG 10 "
            + "2022-04-01 30 9 2022-01-04 2022-04-01 11";
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
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter portfolio name\n" +
            "Select which kind of portfolio you wish to create\n" +
            "1.Flexible Portfolio\n" +
            "2.Inflexible Portfolio\n" +
            "Enter Stock Symbol\n" +
            "Enter transaction cost\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "Enter number of shares\n" +
            "\n" +
            "Successfully bought stocks\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter Stock Symbol\n" +
            "Enter transaction cost\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "Enter number of shares\n" +
            "\n" +
            "Successfully sold stocks\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter Stock Symbol\n" +
            "Enter transaction cost\n" +
            "Enter date(yyyy-mm-dd)\n" +
            "Enter number of shares\n" +
            "\n" +
            "Successfully bought stocks\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n" +
            "Enter start date(yyyy-mm-dd)\n" +
            "Enter end date(yyyy-mm-dd)\n" +
            "Performance of portfolio abcd from Jan 04 to Apr 01\n" +
            "Jan 04 : ********\n" +
            "Jan 21 : ********\n" +
            "Feb 07 : ********\n" +
            "Feb 24 : ********\n" +
            "Mar 14 : *\n" +
            "Mar 31 : *\n" +
            "Scale: * = $1000\n" +
            "\n" +
            "\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Buy Stock\n" +
            "4.Sell Stock\n" +
            "5.View List of Portfolios\n" +
            "6.Get Cost basis on certain date\n" +
            "7.Get Composition on certain date\n" +
            "8.Get Total Value on certain date\n" +
            "9.Get Plot within a certain date range\n" +
            "10.Save\n" +
            "11.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }
}
