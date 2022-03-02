package slogo.view.windows.sections;

import java.util.List;
import java.util.Stack;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
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

  private ScrollPane myScrollPane;
  private List<String> commandList;
  private Text myTextField;
  private Runner myRunner;

  private VBox myHistoryButtons;

  public HistorySection(Runner runner) {
    myScrollPane = new ScrollPane();
    myScrollPane.setId(HISTORY_SECTION_ID);
    myHistoryButtons = new VBox();
    myHistoryButtons.setFillWidth(true);
    myTextField = new Text(STARTING_TEXT);
    myTextField.setId(TEXT_SECTION_ID);
    myScrollPane.setContent(myTextField);
    myScrollPane.setPrefViewportWidth(WIDTH);
    commandList = new Stack<>();
    myRunner = runner;
  }

  @Override
  public Region getSection() {
    return myScrollPane;
  }

  /**
   * Adds a new command's text to the history
   *
   * @param newCommand new successfully run command
   */
  public void setNewHistory(String newCommand) {
    commandList.add(newCommand);
    setMyTextField();
  }

  private void makeHistoryButton(String command){
    Run run = new Run();
    Button b = new Button();
    b.setText(command);
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

  private void setMyTextField() {
    String toSet = STARTING_TEXT;
    for (String s : commandList) {
      toSet += s;
      toSet += "\n";
    }
    myTextField.setText(toSet);
  }
}
