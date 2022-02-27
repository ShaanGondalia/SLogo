package slogo.view.windows.sections;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import slogo.controller.Controller;

public class VariablesSection implements IDESection {

  private ScrollPane scrollPane;
  private Text myTextField;

  private Controller myController;

  private static final String DELIMITER = ": ";
  private static final String NEW_LINE = "\n";
  private static final String SP_ID = "var_pane";
  private static final String TF_ID = "var_text";
  private static final String STARTING_TEXT = "Variables: \n";
  private static final int WIDTH = 100;


  public VariablesSection(Controller c) {
    myController = c;
    myTextField = new Text(STARTING_TEXT);
    myTextField.setId(TF_ID);
    scrollPane = new ScrollPane();
    scrollPane.setId(SP_ID);
    scrollPane.setContent(myTextField);
    scrollPane.setPrefViewportWidth(WIDTH);
    updateList();
  }

  public void updateList() {
    Map<String, String> varList = new HashMap<>(); // will be replaced by getting information from model
    String toDisplay = STARTING_TEXT;
    for(String variableName : varList.keySet()){
      String variableValue = varList.get(variableName);
      toDisplay += variableName;
      toDisplay += DELIMITER;
      toDisplay += variableValue;
      toDisplay += NEW_LINE;
    }

    myTextField.setText(toDisplay);
  }

  @Override
  public Region getSection() {
    return scrollPane;
  }
}
