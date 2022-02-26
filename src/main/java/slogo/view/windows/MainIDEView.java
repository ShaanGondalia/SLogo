package slogo.view.windows;

import java.awt.Dimension;
import java.lang.reflect.Method;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import slogo.Errors;
import slogo.controller.Controller;

/**
 * Creates the central window to display where the user can type in text commands
 *
 * @author Andy S. He
 */
public class MainIDEView extends Display {

  private static final String TITLE = "SLOGO TEAM 3";
  private static final Dimension MAIN_SIZE = new Dimension(300, 200);
  private static final String ROOT_ID = "root";
  private static final String HISTORY_SECTION_ID = "history_sec";

  private static final List<String> BUTTONS = List.of("run", "close", "help", "set_image",
      "set_bk_color", "set_pen_color");
  private static final String RESOURCE_PREFIX = "view.buttons.";

  private BorderPane myPane;
  private TilePane myButtons;
  private TextArea myCommandArea;
  private Controller myController;
  private ResourceBundle myResources;
  private ScrollPane myHistoryPane;
  private Text myHistory;

  public MainIDEView(String language, Controller c, String css, Stage stage) {
    myResources = ResourceBundle.getBundle(RESOURCE_PREFIX + language);

    myPane = new BorderPane();
    myPane.setId(ROOT_ID);
    stage = createStage(TITLE, MAIN_SIZE, myPane, css);
    stage.show();

    createTextArea();

    myButtons = new TilePane();
    myButtons.setId(BUTTON_SECTION_ID);
    myPane.setBottom(myButtons);

    for (String button : BUTTONS) {
      createButton(button);
    }
    myController = c;

    myHistory = new Text("");
    myHistoryPane = new ScrollPane();
    myHistoryPane.setId(HISTORY_SECTION_ID);
    myHistoryPane.setContent(myHistory);
    myPane.setRight(myHistoryPane);
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
    b.setText(myResources.getString(button));
    b.setOnAction((e) -> {
      try {
        Class<?> c = Class.forName("slogo.view.windows.MainIDEView");
        Method m = c.getDeclaredMethod(button);
        m.invoke(this);
      } catch (Exception ex) {
        Errors.showAndClose("internal error");
      }
    });
    myButtons.getChildren().add(b);
  }

  private void run() {
    try {
      myController.runText(getRawCommandText());
    } catch (Exception e) {
      Errors.showError(e.getMessage());
      return;
    }

    myHistory.setText(myHistory.getText() + "\n" + getRawCommandText());
    myHistoryPane.setContent(myHistory);

    myCommandArea.clear();
  }

  private void close() {
    Errors.justClose();
  }

  private void set_image() {

  }

  private void set_bk_color() {

  }

  private void set_pen_color() {

  }

  private void help() {

  }

}
