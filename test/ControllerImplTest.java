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
  public void testCreateExistingUser(){
    Model model = new ModelImpl();

    ByteArrayInputStream in;

    String input = "1 user1 1900 2 user1 6";
    in = new ByteArrayInputStream(input.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);

    View view = new ViewImpl(out);

    ControllerImpl controller = new ControllerImpl(model,view,in);
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
    assertEquals(expectedOutput,bytes.toString());
  }
}