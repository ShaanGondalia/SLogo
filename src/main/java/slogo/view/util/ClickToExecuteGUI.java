package slogo.view.util;

import java.awt.Dimension;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import slogo.model.compiler.Parser;
import slogo.view.windows.Display;

/**
 * Class that allows user to run the turtle without entering commands - creates a new stage
 *
 * @author Andy S. He
 * @see slogo.view.windows.buttons.OpenClickToExecute
 */
public class ClickToExecuteGUI extends Display {

  private static final String TITLE = "GUI - SLOGO 3";
  private static final Dimension DIMENSION = new Dimension(100, 75);
  private static final String CSS_STYLE = "light";
  private static final String DELIMETER = " ";

  private static final String FORWARD_COMMAND = "Forward";
  private static final String BACKWARD_COMMAND = "Backward";
  private static final String LEFT_COMMAND = "Left";
  private static final String RIGHT_COMMAND = "Right";
  private static final int DELTA = 90;

  private final ButtonUtil myInfo;

  /**
   * Default Constructor
   *
   * @param info  information that was given from the button
   * @param stage - used to test the class with the JavaFX default stage
   */
  public ClickToExecuteGUI(ButtonUtil info, Stage stage) {
    BorderPane pane = new BorderPane();
    pane.setId("hello");
    stage = createStage(TITLE, DIMENSION, pane, CSS_STYLE);
    myInfo = info;
    pane.setTop(makeCommandButton(FORWARD_COMMAND, info.language()));
    pane.setBottom(makeCommandButton(BACKWARD_COMMAND, info.language()));
    pane.setLeft(makeCommandButton(LEFT_COMMAND, info.language()));
    pane.setRight(makeCommandButton(RIGHT_COMMAND, info.language()));
    stage.show();
  }

  //used to create a button that runs a specific command
  private Node makeCommandButton(String command, String language) {
    ResourceBundle resources = ResourceBundle.getBundle(Parser.RESOURCES_PACKAGE + language);
    Button b = new Button(getFormattedCommand(command, resources));
    b.setOnAction(
        (e) -> myInfo.runner().runQuietly(getFormattedCommand(command, resources) + DELTA));
    return b;
  }

  //used to get a formatted command that can be run by the compiler
  private String getFormattedCommand(String command, ResourceBundle resourceBundle) {
    String raw = resourceBundle.getString(command);
    return (raw.contains("|") ? command.split("\\|")[0] : command) + DELIMETER;
  }

}
