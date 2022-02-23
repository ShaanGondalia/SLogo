package slogo.view;

import java.lang.reflect.Method;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import slogo.Errors;
import slogo.controller.Controller;
import slogo.model.Compiler;

/**
 * Creates the central window to display where the user can type in text commands
 *
 * @author Andy S. He
 */
public class MainIDEView implements Displayable {

  private static final String TITLE = "SLOGO TEAM 3";
  private static final int HEIGHT = 200;
  private static final int WIDTH = 300;
  private static final String ROOT_ID = "root";
  private static final List<String> BUTTONS = List.of("run","close");

  private BorderPane myPane;
  private TilePane myButtons;
  private TextArea myCommandArea;
  private Controller myController;

  @Override
  public void createStage(String language, Controller c) {
    Stage stage = new Stage();
    myPane = new BorderPane();
    myPane.setId(ROOT_ID);
    Scene scene = new Scene(myPane, WIDTH, HEIGHT);
    stage.setTitle(TITLE);
    stage.setScene(scene);
    stage.show();

    createTextArea();
    myButtons = new TilePane();
    myPane.setBottom(myButtons);

    for (String button : BUTTONS){
      createButton(button);
    }
    myController = c;

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
    Button b = new Button();
    b.setText(button);
    b.setOnAction((e) -> {
      try {
        Class<?> c = Class.forName("slogo.view.MainIDEView");
        Method m = c.getDeclaredMethod(button);
        m.invoke(this);
      } catch (Exception ex){
        Errors.showError(ex.getMessage());
      }
    });
    myButtons.getChildren().add(b);
  }

  private void run() {
    try{
      myController.runText(getRawCommandText());
    }
    catch (Exception e){
      Errors.showError(e.getMessage());
    }
    myCommandArea.clear();
  }

  private void close(){
    Errors.justClose();
  }
}
