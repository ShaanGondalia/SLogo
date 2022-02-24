package slogo.view.windows;

import javafx.scene.layout.TilePane;
import slogo.view.util.OptionGenerator;
import slogo.view.windows.Display;

import javafx.stage.Stage;

/**
 * Common functionality of Splashes
 * @author Andy S. He
 */
public abstract class Splash extends Display {

  private TilePane makeOptions(OptionGenerator gen){
    return null;
  }
  @Override
  public abstract String toString();

}
