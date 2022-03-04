package slogo.view.windows.sections;

import static slogo.view.windows.sections.VariablesAndCommandsSection.NEW_LINE;

import java.util.Map;
import java.util.ResourceBundle;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import slogo.controller.Controller;
import slogo.view.util.Runner;
import slogo.view.windows.MainIDEView;



public class CommandsSection implements IDESection{

  private static final String COM_SP_ID = "com_pane";
  private static final String COM_TF_ID = "com_text";
  private static final String COM_STARTING_TEXT = "Commands";



  private static final int COM_WIDTH = 200;
  private String myLanguage;
  private Runner myRunner;
  private ResourceBundle myResources;
  private ScrollPane commandScrollPane;
  private Text commandTextField;

  private Controller myController;
  public CommandsSection(Controller c, String language, Runner runner){
    myController = c;
    myLanguage = language;
    myRunner = runner;
    myResources = ResourceBundle.getBundle(MainIDEView.IDE_RESOURCES_ROOT + language);
    setCommandSide();
  }

  private void setCommandSide() {
    commandTextField = new Text(myResources.getString(COM_STARTING_TEXT));
    commandTextField.setId(COM_TF_ID);

    commandScrollPane = new ScrollPane();
    commandScrollPane.setId(COM_SP_ID);
    commandScrollPane.setContent(commandTextField);
    commandScrollPane.setPrefViewportWidth(COM_WIDTH);
  }

  protected void updateUserCommands() {
    Map<String, String> commandList = myController.getMapData("userCommands");
    String toDisplay = myResources.getString(COM_STARTING_TEXT) + NEW_LINE;
    for (String commandName : commandList.keySet()) {
      String commandValue = commandList.get(commandName);
      toDisplay += commandValue;
      toDisplay += NEW_LINE + NEW_LINE;
    }
    commandTextField.setText(toDisplay);
  }

  protected String getComText(){
    return commandTextField.getText().substring(COM_STARTING_TEXT.length());
  }

  @Override
  public Region getSection() {
    return commandScrollPane;
  }
}
