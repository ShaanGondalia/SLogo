package slogo.view.windows;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import slogo.Errors;
import slogo.view.util.OptionGenerator;
import slogo.view.windows.Display;

import javafx.stage.Stage;

/**
 * Common functionality of Splashes
 *
 * @author Andy S. He
 */
public abstract class Splash extends Display {

  private String value;
  private static final String ERROR_MESSAGE = " not Implemented. Choose out of the following: ";
  public static final String DEFAULT_SPLASH_CSS = "splash";
  /**
   * Makes the buttons to select from using a Record that contains all the relevant information
   *
   * @param gen parameters for creating the buttons
   * @see OptionGenerator
   * @return
   */
  protected TilePane makeOptions(OptionGenerator gen) {
    TilePane root = new TilePane();
    for (String key : gen.res().keySet()) {
      makeOption(root, key, gen);
    }
    root.setAlignment(Pos.CENTER);
    return root;
  }

  private void makeOption(TilePane root, String key, OptionGenerator gen) {
    Button b = new Button();
    b.setText(gen.res().getString(key));
    b.setOnAction(gen.implemented().contains(key) ? e -> {
      value = key;
      gen.stage().close();
    }
        : e -> Errors.showError(gen.splashType() + ERROR_MESSAGE + gen.implemented()));
    b.setId(key);
    root.getChildren().add(b);
  }

  /**
   * Gets what the splash screen's achieved value is - Can Only return one value, but can easily
   * make iit return more if necessary
   *
   * @return what the splash screen achieved
   */
  @Override
  public String toString() {
    return value;
  }

  public abstract void show();

  protected void show(Stage stage){
    try {
      stage.showAndWait();
    } catch (Exception e) {
      Errors.showAndClose(e.getMessage());
    }
  }
}
