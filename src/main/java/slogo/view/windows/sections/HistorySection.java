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
public class HistorySection implements IDESection{
  private static final String HISTORY_SECTION_ID = "history_sec";

  private ScrollPane myScrollPane;
  private List<String> commandList;
  private Text myTextField;

  public HistorySection(){
    myScrollPane = new ScrollPane();
    myScrollPane.setId(HISTORY_SECTION_ID);
    myTextField = new Text("");
    myScrollPane.setContent(myTextField);
    commandList = new Stack<>();
  }

  @Override
  public Region getSection() {
    return myScrollPane;
  }

  /**
   * Adds a new command's text to the history
   * @param newCommand
   */
  public void setNewHistory(String newCommand){
    commandList.add(newCommand);
  }

  private void setMyTextField(){
    String toSet = "";
    for(String s : commandList){
      toSet += s;
      toSet += "\n";
    }
    myTextField.setText(toSet);
  }
}
