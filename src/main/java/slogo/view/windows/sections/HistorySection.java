package slogo.view.windows.sections;

import static java.lang.Integer.MAX_VALUE;

import java.util.List;
import java.util.Stack;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import slogo.view.util.Runner;
import slogo.view.windows.buttons.Run;

/**
 * Section for History
 *
 * @author Andy S. He
 */
public class HistorySection implements IDESection {

  private static final String HISTORY_SECTION_ID = "history_sec";
  private static final String TEXT_SECTION_ID = "history_text";
  private static final String DELIMITER = "\n";
  private static final String STARTING_TEXT = "Past Commands:" + DELIMITER;

  private static final int WIDTH = 100;
  private static final int MAX_WIDTH = 200;

  private ScrollPane myScrollPane;
  private List<String> commandList;
  private Runner myRunner;

  private VBox myHistoryButtons;

  public HistorySection(Runner runner) {
    myScrollPane = new ScrollPane();
    myScrollPane.setId(HISTORY_SECTION_ID);
    Text descriptor = new Text(STARTING_TEXT);
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

  private void makeHistoryButton(String command){
    Button b = new Button(command);
    b.setOnAction((e) -> myRunner.runAndSave(command));
    b.setMinWidth(WIDTH);
    myHistoryButtons.getChildren().add(b);
  }

  /**
   * Returns the text of the history as to print it out to a file
   *
   * @see slogo.view.windows.buttons.CommandHistoryOut
   * @return formatted text that can be rerun
   */
  public String getHistoryText(){
    String s = "";

    for(String command : commandList){
      s += command + DELIMITER;
    }

    return s;
  }

}
