package slogo.view;

import javafx.stage.Stage;

/**
 * Common functionality of Splashes
 * @author Andy S. He
 */
public abstract class Splash extends Display{

  private void show(Stage stage){
    stage.showAndWait();
  }
}
