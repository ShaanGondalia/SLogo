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

public class VariablesSection implements IDESection {

  private static final String VAR_SP_ID = "var_pane";
  private static final String VAR_TF_ID = "var_text";
  private static final String VAR_STARTING_TEXT = "Variables";
  private static final int VAR_WIDTH = 100;

  private static final String DIA_TITLE = "title";
  private static final String DIA_DESC = "desc";
  private static final String DIA_RESOURCES_PATH = "view.variableDialog.";

  private Controller myController;
  private String myLanguage;
  private Runner myRunner;
  private ResourceBundle myResources;

  private ScrollPane variableScrollPane;
  private Text variableTextField;
  private VBox variableVBox;

  public VariablesSection(Controller c, String language, Runner runner) {
    myController = c;
    myLanguage = language;
    myRunner = runner;
    myResources = ResourceBundle.getBundle(MainIDEView.IDE_RESOURCES_ROOT + language);
    setVariableSide();
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

  protected String getVarText() {
    String toReturn = "";
    Map<String, String> mapData = myController.getMapData(Controller.VARIABLE_GETTER);
    for (String varName : mapData.keySet()) {
      toReturn += makeVariableSetCommand(varName,
          mapData.get(varName));
    }
    return toReturn;
  }

  protected void updateVariables() {
    variableVBox.getChildren().clear();
    Map<String, String> varList = myController.getMapData(Controller.VARIABLE_GETTER);
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
