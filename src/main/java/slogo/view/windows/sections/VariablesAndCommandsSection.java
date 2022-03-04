package slogo.view.windows.sections;

import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import slogo.Errors;
import slogo.controller.Controller;
import slogo.model.compiler.Parser;
import slogo.view.util.Runner;
import slogo.view.windows.MainIDEView;

/**
 * Section that displays to the user the user-defined commands and variables
 *
 * @author Andy S. He
 */
public class VariablesAndCommandsSection implements IDESection {

  private static final String DELIMITER = ": ";
  private static final String NEW_LINE = "\n";
  private static final String VAR_SP_ID = "var_pane";
  private static final String VAR_TF_ID = "var_text";
  private static final String VAR_STARTING_TEXT = "Variables";
  private static final String COM_SP_ID = "com_pane";
  private static final String COM_TF_ID = "com_text";
  private static final String COM_STARTING_TEXT = "Commands";
  private static final String DIA_TITLE = "title";
  private static final String DIA_DESC = "desc";
  private static final String DIA_RESOURCES_PATH = "view.variableDialog.";

  private static final int VAR_WIDTH = 100;
  private static final int COM_WIDTH = 200;

  private ScrollPane variableScrollPane;
  private Text variableTextField;
  private VBox variableVBox;

  private ScrollPane commandScrollPane;
  private Text commandTextField;

  private Controller myController;

  private BorderPane myVarAndComSec;
  private String myLanguage;
  private Runner myRunner;
  private ResourceBundle myResources;

  /**
   * Constructor for the Variables and Commands Section
   *
   * @param c        The controller to get the variables and commands from the model
   * @param language The language to run the commands in
   * @param runner   The runner to run code by clicking on elements in this section
   */
  public VariablesAndCommandsSection(Controller c, String language, Runner runner) {
    myController = c;
    myLanguage = language;
    myRunner = runner;
    myResources = ResourceBundle.getBundle(MainIDEView.IDE_RESOURCES_ROOT + language);
    setVariableSide();
    setCommandSide();

    myVarAndComSec = new BorderPane();
    myVarAndComSec.setLeft(variableScrollPane);
    myVarAndComSec.setRight(commandScrollPane);
  }

  private void setVariableSide() {

    variableTextField = new Text(myResources.getString(VAR_STARTING_TEXT));
    variableTextField.setId(VAR_TF_ID);
    variableVBox = new VBox();
    BorderPane variableBorderPane = new BorderPane();
    variableBorderPane.setTop(variableTextField);
    variableBorderPane.setCenter(variableVBox);
    variableScrollPane = new ScrollPane();
    variableScrollPane.setId(VAR_SP_ID);
    variableScrollPane.setContent(variableBorderPane);
    variableScrollPane.setPrefViewportWidth(VAR_WIDTH);
  }

  private void setCommandSide() {
    commandTextField = new Text(myResources.getString(COM_STARTING_TEXT));
    commandTextField.setId(COM_TF_ID);

    commandScrollPane = new ScrollPane();
    commandScrollPane.setId(COM_SP_ID);
    commandScrollPane.setContent(commandTextField);
    commandScrollPane.setPrefViewportWidth(COM_WIDTH);
  }

  private void makeVariableButton(String variableName, String value) {
    Button b = new Button(formatVariableString(variableName, value));
    b.setMinWidth(VAR_WIDTH);
    b.setOnAction((e) -> openAndSaveNewVariableDialog(variableName, value));
    variableVBox.getChildren().add(b);
  }

  private void openAndSaveNewVariableDialog(String variableName, String value) {
    ResourceBundle dialogResources = ResourceBundle.getBundle(DIA_RESOURCES_PATH + myLanguage);
    TextInputDialog dialog = new TextInputDialog(value);
    dialog.setTitle(dialogResources.getString(DIA_TITLE));
    dialog.setHeaderText(dialogResources.getString(DIA_TITLE));
    dialog.setContentText(dialogResources.getString(DIA_DESC));
    Optional<String> result = dialog.showAndWait();
    String content = dialog.getEditor().getText();

    double toSet = 0;
    try {
      toSet = Double.parseDouble(content);
    } catch (Exception ex) {
      Errors.showError(ex.getMessage());
    }
    if (result.isPresent()) {
      myRunner.runQuietly(makeVariableSetCommand(variableName, String.valueOf(toSet)));
    }
  }

  /**
   * Updates the display to display the current variables and user-defined section
   */
  public void update() {
    updateVariables();
    updateUserCommands();
  }

  private void updateVariables() {
    variableVBox.getChildren().clear();
    Map<String, String> varList = myController.getMapData("variables");
    for (String variableName : varList.keySet()) {
      makeVariableButton(variableName, varList.get(variableName));
    }
  }

  private String formatVariableString(String name, String value) {
    String s = name;
    s = s.substring(1);
    s += DELIMITER;
    s += value;
    s += NEW_LINE;
    return s;
  }

  private void updateUserCommands() {
    Map<String, String> commandList = myController.getMapData("userCommands");
    String toDisplay = myResources.getString(COM_STARTING_TEXT) + NEW_LINE;
    for (String commandName : commandList.keySet()) {
      String commandValue = commandList.get(commandName);
      toDisplay += commandValue;
      toDisplay += NEW_LINE + NEW_LINE;
    }
    commandTextField.setText(toDisplay);
  }

  /**
   * Allows for the writing to a file of correctly formatted syntax
   *
   * @return String to Write to a file
   */
  public String getVariableAndCommandText() {
    String toReturn = "";
    for (String varName : myController.getMapData("variables").keySet()) {
      toReturn += makeVariableSetCommand(varName,
          myController.getMapData("variables").get(varName));
    }

    toReturn += commandTextField.getText().substring(COM_STARTING_TEXT.length());
    return toReturn;
  }

  private String makeVariableSetCommand(String varName, String value) {
    ResourceBundle parserResources = ResourceBundle.getBundle(
        Parser.RESOURCES_PACKAGE + myLanguage);
    String makeVariableCommand = parserResources.getString("MakeVariable");
    return (makeVariableCommand.contains("|") ? makeVariableCommand.split("\\|")[0] :
        makeVariableCommand) + NEW_LINE + varName + NEW_LINE + value
        + NEW_LINE;
  }


  @Override
  public Region getSection() {
    return myVarAndComSec;
  }
}
