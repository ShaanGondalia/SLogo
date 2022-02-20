package slogo;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Class to standardize creation of Alerts and showing it to the user
 *
 * @author Andy S. He
 */
public class Errors {

  /**
   * Will create an Alert, then close all windows
   * @param message message to display to user
   */
  public static void showAndClose(String message) {
    Alert alert = new Alert(AlertType.ERROR, message);
    alert.showAndWait();
    Platform.exit();
    System.exit(0);
  }
}
