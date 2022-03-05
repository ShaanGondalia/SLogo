package slogo.view.windows.sections;

import static slogo.view.windows.sections.DataSection.NEW_LINE;

import java.util.Map;
import java.util.ResourceBundle;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import slogo.controller.Controller;
import slogo.view.windows.MainIDEView;

/**
 * Creates the section in the DataSection in the IDE that displays commands
 *
 * @author Andy S. He
 * @see DataSection
 */
public class CommandsSection implements IDESection {

  private static final String COM_SP_ID = "com_pane";
  private static final String COM_TF_ID = "com_text";
  private static final String COM_STARTING_TEXT = "Commands";

  private static final int COM_WIDTH = 200;
  private final ResourceBundle myResources;
  private ScrollPane commandScrollPane;
  private Text commandTextField;

  private final Controller myController;

  /**
   * Default constructor to create this basic section
   *
   * @param c        used to determine what user-defined commands exist
   * @param language used to determin display text language
   */
  public CommandsSection(Controller c, String language) {
    myController = c;
    myResources = ResourceBundle.getBundle(MainIDEView.IDE_RESOURCES_ROOT + language);
    setCommandSide();
  }

  //sets all the JavaFX elements to what they need to be
  private void setCommandSide() {
    commandTextField = new Text(myResources.getString(COM_STARTING_TEXT));
    commandTextField.setId(COM_TF_ID);

    commandScrollPane = new ScrollPane();
    commandScrollPane.setId(COM_SP_ID);
    commandScrollPane.setContent(commandTextField);
    commandScrollPane.setPrefViewportWidth(COM_WIDTH);
  }

  /**
   * Used in the Data Section to update what user commands exist and display it formatted
   */
  protected void updateUserCommands() {
    Map<String, String> commandList = myController.getMapData(Controller.USER_COMMAND_GETTER);
    String toDisplay = myResources.getString(COM_STARTING_TEXT) + NEW_LINE;
    for (String commandName : commandList.keySet()) {
      String commandValue = commandList.get(commandName);
      toDisplay += commandValue;
      toDisplay += NEW_LINE + NEW_LINE;
    }
    commandTextField.setText(toDisplay);
  }

  /**
   * Used in the file reader to properly format the commands to be written to the file
   *
   * @return the String that could be run again
   */
  protected String getComText() {
    return commandTextField.getText().substring(COM_STARTING_TEXT.length());
  }

  @Override
  public Region getSection() {
    return commandScrollPane;
  }
}
