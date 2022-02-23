package slogo.view;

import java.awt.Dimension;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import slogo.Errors;

/**
 * Used to probe what language the whole program will be in
 *
 * @author Andy S He
 */
public class LanguageSplash extends Splashable {

  private static final String TITLE = "Language Probe";
  private static final Dimension SIZE = new Dimension(400, 400);
  private static final String RESOURCE_LANGUAGES = "slogo.languages.LangaugeOptions";
  private static final String ERROR_MESSAGE = "Language not Implemented. Choose out of the following: ";

  private static final Set<String> IMPLEMENTED = Set.of("English");

  private final ResourceBundle myLanguages;
  private final Stage myStage;
  private String language;

  public LanguageSplash() {
    myLanguages = ResourceBundle.getBundle(RESOURCE_LANGUAGES);

    myStage = new Stage();
    Scene scene = new Scene(makeOptions(), SIZE.width, SIZE.height);
    myStage.setTitle(TITLE);
    myStage.setScene(scene);
    try {
      myStage.showAndWait();
    } catch (Exception e) {
      Errors.showAndClose(e.getMessage());
    }
  }

  private TilePane makeOptions() {
    TilePane root = new TilePane();
    for (String key : myLanguages.keySet()) {
      makeOption(root, key);
    }
    root.setAlignment(Pos.CENTER);
    return root;
  }

  private void makeOption(TilePane root, String key) {
    Button b = new Button();
    b.setText(myLanguages.getString(key));
    b.setOnAction(IMPLEMENTED.contains(key) ? e -> {
      language = key;
      myStage.close();
    }
        : e -> Errors.showError(ERROR_MESSAGE + IMPLEMENTED));
    b.setId(key);
    root.getChildren().add(b);
  }


  /**
   * Gets the language that the program will be run in
   *
   * @return language
   */
  @Override
  public String toString() {
    return language;
  }

}
