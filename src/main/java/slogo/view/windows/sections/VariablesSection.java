package slogo.view.windows.sections;

import static slogo.view.windows.sections.DataSection.DELIMITER;
import static slogo.view.windows.sections.DataSection.NEW_LINE;

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
 * Section that displays what variables are in play - and allows for them to be changed without
 * typing commands
 *
 * @author Andy S. He
 */
public class VariablesSection implements IDESection {

  private static final String VAR_SP_ID = "var_pane";
  private static final String VAR_TF_ID = "var_text";
  private static final String VAR_STARTING_TEXT = "Variables";
  private static final int VAR_WIDTH = 100;

  private static final String DIA_TITLE = "title";
  private static final String DIA_DESC = "desc";
  private static final String DIA_RESOURCES_PATH = "view.variableDialog.";

  private final Controller myController;
  private final String myLanguage;
  private final Runner myRunner;
  private final ResourceBundle myResources;

  private ScrollPane variableScrollPane;
  private VBox variableVBox;

  /**
   * Default constructor
   *
   * @param c        how to get the variables
   * @param language display language
   * @param runner   allows for class to run programs in the background
   */
  public VariablesSection(Controller c, String language, Runner runner) {
    myController = c;
    myLanguage = language;
    myRunner = runner;
    myResources = ResourceBundle.getBundle(MainIDEView.IDE_RESOURCES_ROOT + language);
    setVariableSide();
  }

  // sets the JavaFX Nodes to what they need to be
  private void setVariableSide() {
    Text variableTextField = new Text(myResources.getString(VAR_STARTING_TEXT));
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

  // used to make each button be able to change on-click
  private void makeVariableButton(String variableName, String value) {
    Button b = new Button(formatVariableString(variableName, value));
    b.setMinWidth(VAR_WIDTH);
    b.setOnAction((e) -> openAndSaveNewVariableDialog(variableName, value));
    variableVBox.getChildren().add(b);
  }

  // opens a new dialog to change the variables on-click
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
   * Used to write the variables to a file in a way that that text can be run again
   *
   * @return String that is able to be re run
   */
  protected String getVarText() {
    String toReturn = "";
    Map<String, String> mapData = myController.getMapData(Controller.VARIABLE_GETTER);
    for (String varName : mapData.keySet()) {
      toReturn += makeVariableSetCommand(varName,
          mapData.get(varName));
    }
    return toReturn;
  }

  /**
   * Used to update the VBox of buttons to display new variables if a program is run
   */
  protected void updateVariables() {
    variableVBox.getChildren().clear();
    Map<String, String> varList = myController.getMapData(Controller.VARIABLE_GETTER);
    for (String variableName : varList.keySet()) {
      makeVariableButton(variableName, varList.get(variableName));
    }
  }

  //formats the variable to be displayed to the user in a user-friendly way
  private String formatVariableString(String name, String value) {
    String s = name;
    s = s.substring(1);
    s += DELIMITER;
    s += value;
    s += NEW_LINE;
    return s;
  }

  // used to ensure that set can be run in any language and that when saved to a file, the command works.
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
    return variableScrollPane;
  }
}
