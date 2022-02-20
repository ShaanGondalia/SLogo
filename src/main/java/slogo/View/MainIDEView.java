package slogo.View;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Creates the central window to display where the user can type in text commands
 *
 * @author Andy S. He
 */
public class MainIDEView implements Displayable {
  private static final String TITLE = "SLOGO TEAM 3";
  private static final int HEIGHT = 800;
  private static final int WIDTH = 1000;
  private static final String ROOT_ID = "root";

  @Override
  public void createStage() {
    Stage stage = new Stage();
    BorderPane root = new BorderPane();
    root.setId(ROOT_ID);
    Scene scene = new Scene(root, WIDTH, HEIGHT);
    stage.setTitle(TITLE);
    stage.setScene(scene);
    stage.show();
  }

  private void createTextArea() {

  }

}
