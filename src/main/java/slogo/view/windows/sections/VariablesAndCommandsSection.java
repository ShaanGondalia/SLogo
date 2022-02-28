package slogo.view.windows.sections;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import slogo.controller.Controller;

public class VariablesAndCommandsSection implements IDESection {

  private ScrollPane variableScrollPane;
  private Text variableTextField;

  private ScrollPane commandScrollPane;
  private Text commandTextField;

  private Controller myController;

  private BorderPane myVarAndComSec;

  private static final String DELIMITER = ": ";
  private static final String NEW_LINE = "\n";
  private static final String VAR_SP_ID = "var_pane";
  private static final String VAR_TF_ID = "var_text";
  private static final String VAR_STARTING_TEXT = "Variables: \n";
  private static final String COM_SP_ID = "com_pane";
  private static final String COM_TF_ID = "com_text";
  private static final String COM_STARTING_TEXT = "User-Defined Commands: \n";
  private static final int VAR_WIDTH = 100;
  private static final int COM_WIDTH = 200;


  public VariablesAndCommandsSection(Controller c) {
    myController = c;

    variableTextField = new Text(VAR_STARTING_TEXT);
    variableTextField.setId(VAR_TF_ID);
    variableScrollPane = new ScrollPane();
    variableScrollPane.setId(VAR_SP_ID);
    variableScrollPane.setContent(variableTextField);
    variableScrollPane.setPrefViewportWidth(VAR_WIDTH);

    commandTextField = new Text(COM_STARTING_TEXT);
    commandTextField.setId(COM_TF_ID);
    commandScrollPane = new ScrollPane();
    commandScrollPane.setId(COM_SP_ID);
    commandScrollPane.setContent(commandTextField);
    commandScrollPane.setPrefViewportWidth(COM_WIDTH);

    myVarAndComSec = new BorderPane();
    myVarAndComSec.setLeft(variableScrollPane);
    myVarAndComSec.setRight(commandScrollPane);
  }

  public void updateList() {
    Map<String, String> varList = myController.getVariables(); // will be replaced by getting information from model
    String toDisplay = VAR_STARTING_TEXT;
    for(String variableName : varList.keySet()){
      String variableValue = varList.get(variableName);
      toDisplay += variableName;
      toDisplay += DELIMITER;
      toDisplay += variableValue;
      toDisplay += NEW_LINE;
    }

    variableTextField.setText(toDisplay);
  }

  @Override
  public Region getSection() {
    return myVarAndComSec;
  }
}
