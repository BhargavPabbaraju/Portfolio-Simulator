import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import controller.ControllerImpl;
import models.Model;
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
    Model model = new MockModel(log, olog);
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    View view = new ViewImpl(out);
    ByteArrayInputStream in;
    String input = "1 user 10000 6";
    in = new ByteArrayInputStream(input.getBytes());
    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();
    assertEquals("user 10000.0 ", log.toString());
    assertEquals("createUser ", olog.toString());
  }

  @Test
  public void testLoadUser() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    Model model = new MockModel(log, olog);
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    View view = new ViewImpl(out);
    ByteArrayInputStream in;
    String input = "2 user 6";
    in = new ByteArrayInputStream(input.getBytes());
    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();
    assertEquals("user ", log.toString());
    assertEquals("loadUser ", olog.toString());
  }

  @Test
  public void testCreatePortfolio() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    Model model = new MockModel(log, olog);
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    View view = new ViewImpl(out);
    ByteArrayInputStream in;
    String input = "1 user 10000 1 personal AAPL 10 2 6";
    in = new ByteArrayInputStream(input.getBytes());
    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();
    assertEquals("user 10000.0 personal AAPL 10 ", log.toString());
    assertEquals("createUser createPortfolio addStockToPortfolio ", olog.toString());
  }

  @Test
  public void testAddStockToPortfolio() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    Model model = new MockModel(log, olog);
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    View view = new ViewImpl(out);
    ByteArrayInputStream in;
    String input = "1 user 10000 1 personal GOOGL 20 2 6";
    in = new ByteArrayInputStream(input.getBytes());
    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();
    assertEquals("user 10000.0 personal GOOGL 20 ", log.toString());
    assertEquals("createUser createPortfolio addStockToPortfolio ", olog.toString());
  }

  @Test
  public void testLoadPortfolio() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    Model model = new MockModel(log, olog);
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    View view = new ViewImpl(out);
    ByteArrayInputStream in;
    String input = "1 user 10000 2 personal 6";
    in = new ByteArrayInputStream(input.getBytes());
    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();
    assertEquals("user 10000.0 personal ", log.toString());
    assertEquals("createUser loadPortfolio ", olog.toString());
  }

  @Test
  public void testSave() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    Model model = new MockModel(log, olog);
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    View view = new ViewImpl(out);
    ByteArrayInputStream in;
    String input = "2 user 5 6";
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
    Model model = new MockModel(log, olog);
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    View view = new ViewImpl(out);
    ByteArrayInputStream in;
    String input = "2 user 4 2022-10-30 6";
    in = new ByteArrayInputStream(input.getBytes());
    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();
    assertEquals("user 2022-10-30 ", log.toString());
    assertEquals("loadUser getTotalValue ", olog.toString());
  }

  @Test
  public void testGetComposition() {
    StringBuilder log = new StringBuilder();
    StringBuilder olog = new StringBuilder();
    Model model = new MockModel(log, olog);
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    View view = new ViewImpl(out);
    ByteArrayInputStream in;
    String input = "2 user 3 6";
    in = new ByteArrayInputStream(input.getBytes());
    ControllerImpl controller = new ControllerImpl(model, view, in);
    controller.goController();
    assertEquals("user ", log.toString());
    assertEquals("loadUser getComposition ", olog.toString());
  }

}