package slogo.view.windows.sections;

import static java.lang.Integer.MAX_VALUE;

import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import slogo.view.util.Runner;
import slogo.view.windows.MainIDEView;

/**
 * Section for History - Displayed on the right of the screen
 *
 * @author Andy S. He
 */
public class HistorySection implements IDESection {

  private static final String HISTORY_SECTION_ID = "history_sec";
  private static final String TEXT_SECTION_ID = "history_text";
  private static final String DELIMITER = "\n";
  private static final String STARTING_TEXT = "History";

  private static final int WIDTH = 100;
  private static final int MAX_WIDTH = 200;

  private final ScrollPane myScrollPane;
  private final List<String> commandList;
  private final Runner myRunner;

  private final VBox myHistoryButtons;

  /**
   * Default constructor that creates the history section
   *
   * @param runner   allows for commands to be run on-click
   * @param language determines display language
   */
  public HistorySection(Runner runner, String language) {
    myScrollPane = new ScrollPane();
    myScrollPane.setId(HISTORY_SECTION_ID);
    Text descriptor = new Text(ResourceBundle.getBundle(MainIDEView.IDE_RESOURCES_ROOT + language)
        .getString(STARTING_TEXT));
    descriptor.setId(TEXT_SECTION_ID);
    BorderPane borderPane = new BorderPane();
    borderPane.setTop(descriptor);
    myHistoryButtons = new VBox();
    myHistoryButtons.setMinWidth(WIDTH);
    myHistoryButtons.setMaxWidth(MAX_WIDTH);
    myHistoryButtons.setAlignment(Pos.CENTER);
    borderPane.setCenter(myHistoryButtons);
    myScrollPane.setContent(borderPane);
    commandList = new Stack<>();
    myRunner = runner;
  }

  /**
   * get the region that can be set to a specific location on the MainIDEView
   *
   * @return a ScrollPane
   */
  @Override
  public Region getSection() {
    return myScrollPane;
  }

  /**
   * Adds a new command's text to the history - now uses buttons so user can click on them
   *
   * @param newCommand new successfully run command
   */
  public void setNewHistory(String newCommand) {
    commandList.add(newCommand);
    makeHistoryButton(newCommand);
  }

  //makes a button that can be run on-click
  private void makeHistoryButton(String command) {
    Button b = new Button(command);
    b.setOnAction((e) -> myRunner.runAndSave(command));
    b.setMinWidth(WIDTH);
    myHistoryButtons.getChildren().add(b);
  }

  /**
   * Returns the text of the history as to print it out to a file
   *
   * @return formatted text that can be rerun
   * @see slogo.view.windows.buttons.CommandHistoryOut
   */
  public String getHistoryText() {
    String s = "";

    for (String command : commandList) {
      s += command + DELIMITER;
    }

    return s;
  }

}
