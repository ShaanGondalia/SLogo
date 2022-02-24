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

  protected TilePane makeOptions(OptionGenerator gen) {
    TilePane root = new TilePane();
    for (String key : gen.res().keySet()) {
      makeOption(root, key, gen);
    }
    root.setAlignment(Pos.CENTER);
    return root;
  }

  protected void makeOption(TilePane root, String key, OptionGenerator gen) {
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

  @Override
  public String toString() {
    return value;
  }

}
