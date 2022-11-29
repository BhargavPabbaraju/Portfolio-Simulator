package view;

import controller.Features;
import controller.Validator;


/**
 * This interface represents the methods offered by a view. It can be implemented by any kind of
 * view (either text-based or GUI).
 */
public interface NewView {

  /**
   * This method is used to set a validator object which will validate user inputs.
   * @param validator The validator object.
   */
  void setValidator(Validator validator);

  /**
   * This method is used to integrate the controller with the view by sending a features object.
   * @param features the features object.
   */

  void setFeatures(Features features);

  /**
   * This method is used to show the main menu. This is where most of the operations are available.
   */
  void showMainMenu();

  /**
   * This method is used to display a general message on the view.
   * @param message the message to be displayed.
   * @param error whether this message is an error.
   */

  void showMessage(String message,boolean error);

  /**
   * This method is used to show the initial menu. Users can be created or loaded from this menu.
   */

  void showInitialMenu();

  /**
   * This method is used to show new user menu. This menu will contain only those operations
   * that a newly created user can peform.
   */

  void showNewUserMenu();
}
