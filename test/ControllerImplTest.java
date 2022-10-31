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
    controller.go();

    String expectedOutput = "To select a particular option,enter the number next to it\n" +
            "1.Create User\n" +
            "2.Load User\n" +
            "Enter username\n" +
            "Enter initial balance\n" +
            "User Already exists\n" +
            "To select a particular option,enter the number next to it\n" +
            "1.Create User\n" +
            "2.Load User\n" +
            "Enter username\n" +
            "User successfully loaded\n" +
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
    controller.go();

    String expectedOutput = "To select a particular option,enter the number next to it\n" +
            "1.Create User\n" +
            "2.Load User\n" +
            "Enter username\n" +
            "Enter initial balance\n" +
            "Username must not contain any of \\\"{}[],: characters.\n" +
            "To select a particular option,enter the number next to it\n" +
            "1.Create User\n" +
            "2.Load User\n" +
            "Enter username\n" +
            "User successfully loaded\n" +
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
    controller.go();

    String expectedOutput = "To select a particular option,enter the number next to it\n" +
            "1.Create User\n" +
            "2.Load User\n" +
            "Enter username\n" +
            "Enter initial balance\n" +
            "Balance cannot be negative\n" +
            "To select a particular option,enter the number next to it\n" +
            "1.Create User\n" +
            "2.Load User\n" +
            "Enter username\n" +
            "User successfully loaded\n" +
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
    controller.go();

    String expectedOutput = "To select a particular option,enter the number next to it\n" +
            "1.Create User\n" +
            "2.Load User\n" +
            "Enter username\n" +
            "Enter initial balance\n" +
            "Balance must be a valid floating point number\n" +
            "To select a particular option,enter the number next to it\n" +
            "1.Create User\n" +
            "2.Load User\n" +
            "Enter username\n" +
            "User successfully loaded\n" +
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
    controller.go();

    String expectedOutput = "To select a particular option,enter the number next to it\n" +
            "1.Create User\n" +
            "2.Load User\n" +
            "Enter username\n" +
            "Enter initial balance\n" +
            "User successfully created\n" +
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
    controller.go();

    String expectedOutput = "To select a particular option,enter the number next to it\n" +
            "1.Create User\n" +
            "2.Load User\n" +
            "Enter username\n" +
            "User doesn't exists\n" +
            "To select a particular option,enter the number next to it\n" +
            "1.Create User\n" +
            "2.Load User\n" +
            "Enter username\n" +
            "User successfully loaded\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }

  @Test
  public void testLoadUserNameInvalid() {
    Model model = new ModelImpl();

    ByteArrayInputStream in;

    String input = "2 user5 2 user1 6";
    in = new ByteArrayInputStream(input.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);

    View view = new ViewImpl(out);

    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.go();

    String expectedOutput = "To select a particular option,enter the number next to it\n" +
            "1.Create User\n" +
            "2.Load User\n" +
            "Enter username\n" +
            "Load file is not in valid format.\n" +
            "To select a particular option,enter the number next to it\n" +
            "1.Create User\n" +
            "2.Load User\n" +
            "Enter username\n" +
            "User successfully loaded\n" +
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
    controller.go();
    String expectedOutput = "To select a particular option,enter the number next to it\n" +
            "1.Create User\n" +
            "2.Load User\n" +
            "Enter username\n" +
            "Enter initial balance\n" +
            "User successfully created\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n" +
            "Enter portfolio name\n" +
            "Portfolio name must not contain any of \\\"{}[],: characters.\n" +
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
    controller.go();
    String expectedOutput = "To select a particular option,enter the number next to it\n" +
            "1.Create User\n" +
            "2.Load User\n" +
            "Enter username\n" +
            "User successfully loaded\n" +
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n" +
            "Enter portfolio name\n" +
            "Portfolio already exists\n" +
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
    controller.go();
    String expectedOutput = "To select a particular option,enter the number next to it\n" +
            "1.Create User\n" +
            "2.Load User\n" +
            "Enter username\n" +
            "User successfully loaded\n" +
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
            "1.Create Portfolio\n" +
            "2.Load Portfolio\n" +
            "3.Get Composition\n" +
            "4.Get Total Value on certain date\n" +
            "5.Save\n" +
            "6.Exit\n";
    assertEquals(expectedOutput, bytes.toString());
  }
}