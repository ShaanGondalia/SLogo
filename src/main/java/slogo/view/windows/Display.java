package slogo.view.windows;

import java.awt.Dimension;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import slogo.controller.Controller;

/**
 * Common functionality to display a new stage
 *
 * @author Andy S. He
 */
public abstract class Display {

  public static final String CSS_RESOURCES_PATH = "/view/css/";
  public static final String CSS_FILE_ENDING = ".css";
  public static final String TITLES_RESOURCES_PATH = "view.titles.";
  private Scene myScene;
  /**
   * Used to standardize creation of stages so no duplicate code. Can initialize without a previous
   * stage, or with one, if the stage needs to be referenced in creating the root.
   *
   * @param stage old stage to reconfigure
   * @param title title of the stage
   * @param size  dimensions of the stage
   * @param root  where to add new JavaFX nodes
   * @return the stage but now configured
   */
  protected Stage createStage(Stage stage, String title, Dimension size, Pane root, String style) {
    myScene = new Scene(root, size.width, size.height);
    myScene.getStylesheets()
        .add(getClass().getResource(CSS_RESOURCES_PATH + style + CSS_FILE_ENDING).toExternalForm());
    stage.setScene(myScene);
    stage.setTitle(title);
    return stage;
  }

  /**
   * Same as above, but stage need not referenced in creation of the root
   */
  protected Stage createStage(String title, Dimension size, Pane root, String style) {
    Stage stage = new Stage();
    return createStage(stage, title, size, root, style);
  }

  /**
   * Used only for testing purposes
   * @return
   */
  protected Scene getMyScene(){
    return myScene;
  }
}
