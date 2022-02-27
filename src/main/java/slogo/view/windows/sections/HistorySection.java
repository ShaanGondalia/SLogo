package slogo.view.windows.sections;

import java.util.List;
import java.util.Stack;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

/**
 * Section for History
 *
 * @author Andy S. He
 */
public class HistorySection implements IDESection {

  private static final String HISTORY_SECTION_ID = "history_sec";
  private static final String TEXT_SECTION_ID = "history_text";

  private static final String STARTING_TEXT = "Past Commands:\n";
  private static final int WIDTH = 100;

  private ScrollPane myScrollPane;
  private List<String> commandList;
  private Text myTextField;

  public HistorySection() {
    myScrollPane = new ScrollPane();
    myScrollPane.setId(HISTORY_SECTION_ID);
    myTextField = new Text(STARTING_TEXT);
    myTextField.setId(TEXT_SECTION_ID);
    myScrollPane.setContent(myTextField);
    myScrollPane.setPrefViewportWidth(WIDTH);
    commandList = new Stack<>();
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

  private void setMyTextField() {
    String toSet = STARTING_TEXT;
    for (String s : commandList) {
      toSet += s;
      toSet += "\n";
    }
    myTextField.setText(toSet);
  }
}
