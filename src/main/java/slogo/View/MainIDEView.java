package slogo.View;

import java.util.List;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
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
  private static final List<String> BUTTONS = List.of("run");

  private BorderPane myPane;
  private TextArea myCommandArea;

  @Override
  public void createStage(String language) {
    Stage stage = new Stage();
    myPane = new BorderPane();
    myPane.setId(ROOT_ID);
    Scene scene = new Scene(myPane, WIDTH, HEIGHT);
    stage.setTitle(TITLE);
    stage.setScene(scene);
    stage.show();

    createTextArea();
  }

  private void createTextArea() {
    myCommandArea = new TextArea();
    myCommandArea.setId("textArea");
    myPane.setCenter(myCommandArea);
  }

  /**
   * Sends the data from the TextBox over to the Parser - perhaps needs redesign since maybe the
   * parser should not know the view.
   *
   * @return raw text
   */
  public String getRawCommandText() {
    return myCommandArea.getText();
  }

  private void createButton(String button) {

  }
  private void run(){

  }
}
