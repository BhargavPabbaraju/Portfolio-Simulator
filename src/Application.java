import java.io.IOException;
import java.text.ParseException;

import controller.ControllerImpl;
import controller.Features;
import controller.NewController;
import models.NewModel;
import models.NewerModelImpl;
import models.NewerModel;
import view.NewView;
import view.NewViewImpl;
import view.View;
import view.ViewImpl;

/**
 * This class is the main application class that contains the main method.
 */
public class Application {

  /**
   * This method is used to initialize the model and view and run the controller.
   *
   * @param args inputs from the command line
   * @throws IOException    throws an IO Exception
   * @throws ParseException throws an ParseException if parser fails
   */
//  public static void main(String[] args) throws IOException, ParseException {
//    NewModel model = new NewModelImpl();
//    View view = new ViewImpl(System.out);
//
//    ControllerImpl controller = new ControllerImpl(model, view, System.in);
//    controller.goController();
//  }

    public static void main(String[] args) throws IOException, ParseException {
    NewerModel model =  new NewerModelImpl();
    NewView view = new NewViewImpl();

    Features controller = new NewController(model,view);
  }
}
