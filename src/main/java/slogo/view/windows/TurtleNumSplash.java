package slogo.view.windows;

import java.awt.Dimension;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.stage.Stage;
import slogo.view.util.OptionGenerator;

public class TurtleNumSplash extends Splash {

  private static final String TITLE = "turtle";
  private static final Dimension SIZE = new Dimension(200, 200);
  private static final Set<String> IMPLEMENTED = Set.of("1", "2", "3", "4", "5");
  private static final String SPLASH_TYPE = "Turtle";
  private static final String RESOURCE_PREFIX = "view.turtleprops.possibilities";

  private Stage myStage;

  public TurtleNumSplash(String language) {
    ResourceBundle titles = ResourceBundle.getBundle(Display.TITLES_RESOURCES_PATH + language);
    ResourceBundle possibilities = ResourceBundle.getBundle(RESOURCE_PREFIX);
    myStage = new Stage();
    myStage = createStage(myStage, titles.getString(TITLE), SIZE, makeOptions(
        new OptionGenerator(myStage, possibilities, SPLASH_TYPE, IMPLEMENTED)), DEFAULT_SPLASH_CSS);
  }

  @Override
  public void show() {
    super.show(myStage);
  }
}
