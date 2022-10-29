import java.io.IOException;
import java.text.ParseException;

import controller.ControllerImpl;
import models.Model;
import models.ModelImpl;
import view.View;
import view.ViewImpl;

public class Application {

  public static void main(String args[]) throws IOException, ParseException {
    Model model = new ModelImpl();
    View view = new ViewImpl(System.out);

    ControllerImpl controller = new ControllerImpl(model,view,System.in);
    controller.go();
  }
}
