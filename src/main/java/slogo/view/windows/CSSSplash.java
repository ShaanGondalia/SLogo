package slogo.view.windows;

import java.awt.Dimension;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import slogo.Errors;
import slogo.view.util.OptionGenerator;

/**
 * Screen to display the first time program is running up. Here, can select language / css and
 * anything else needed before initialization
 *
 * @author Andy S. He
 * @see OptionGenerator
 */
public class CSSSplash extends Splash {

  private static final String TITLE = "Choose CSS";
  private static final Dimension SIZE = new Dimension(200, 200);
  private static final String CSS_RESOURCE_ENDING = "CSS";
  private static final Set<String> IMPLEMENTED = Set.of("light", "dark");

  private ResourceBundle myResources;
  private Stage myStage;

  /**
   * Creates a Splash screen to determine what style of nodes to have for MainIDEView
   *
   * @param language
   * @see LanguageSplash
   * @see MainIDEView
   */
  public CSSSplash(String language) {
    myResources = ResourceBundle.getBundle(
        MainIDEView.RESOURCE_PREFIX + language + CSS_RESOURCE_ENDING);
    myStage = new Stage();
    myStage = createStage(myStage, TITLE, SIZE,
        makeOptions(new OptionGenerator(myStage, myResources, CSS_RESOURCE_ENDING, IMPLEMENTED)),
        DEFAULT_SPLASH_CSS);
    try {
      myStage.showAndWait();
    } catch (Exception e) {
      Errors.showAndClose(e.getMessage());
    }
  }

}
