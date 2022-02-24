package slogo.view.windows;

import java.awt.Dimension;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import slogo.Errors;
import slogo.view.util.OptionGenerator;

/**
 * Used to probe what language the whole program will be in
 *
 * @author Andy S He
 * @see OptionGenerator
 */
public class LanguageSplash extends Splash {

  private static final String TITLE = "Language Probe";
  private static final Dimension SIZE = new Dimension(400, 400);
  private static final String RESOURCE_LANGUAGES = "slogo.languages.LangaugeOptions";
  private static final String SPLASH_TYPE = "Language";
  private static final Set<String> IMPLEMENTED = Set.of("English");

  private ResourceBundle myLanguages;
  private Stage myStage;

  /**
   * Creates the Splash Screen for choosing a Language
   */
  public LanguageSplash() {
    myLanguages = ResourceBundle.getBundle(RESOURCE_LANGUAGES);
    myStage = new Stage();
    myStage = createStage(myStage, TITLE, SIZE,
        makeOptions(new OptionGenerator(myStage, myLanguages, SPLASH_TYPE, IMPLEMENTED)),
        DEFAULT_SPLASH_CSS);
  }

  @Override
  public void show(){
    super.show(myStage);
  }
}
