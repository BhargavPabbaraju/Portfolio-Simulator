import java.io.IOException;
import java.text.ParseException;

import controller.ControllerImpl;
import models.Model;
import models.ModelImpl;
import view.View;
import view.ViewImpl;

/**
 * This class is the main application class that contains the main method.
 */
public class Application {

  /**
   * This method is used to initialize the model and view and run the controller.
   * @param args
   * @throws IOException
   * @throws ParseException
   */
  public static void main(String args[]) throws IOException, ParseException {
    Model model = new ModelImpl();
    View view = new ViewImpl(System.out);

    ControllerImpl controller = new ControllerImpl(model,view,System.in);
    controller.go();
  }
}
