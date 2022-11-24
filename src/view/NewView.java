package view;

import controller.Features;
import controller.Validator;

public interface NewView {

  void setValidator(Validator validator);

  void setFeatures(Features features);

  void showMainMenu();

  void showMessage(String message,boolean error);

  void showInitialMenu();
}
