import org.junit.Test;

import java.io.ByteArrayInputStream;

import controller.ControllerImpl;
import models.Model;
import models.ModelImpl;
import view.View;
import view.ViewImpl;

import static org.junit.Assert.assertEquals;

public class ControllerImplTest {



  @Test
  public void testOne(){
    Model model = new ModelImpl();
    View view = new ViewImpl(System.out);
    ByteArrayInputStream in;

    String input = "1 user1 2 user1";
    in = new ByteArrayInputStream(input.getBytes());

    ControllerImpl controller = new ControllerImpl(model,view,in);
    controller.go();
  }
}