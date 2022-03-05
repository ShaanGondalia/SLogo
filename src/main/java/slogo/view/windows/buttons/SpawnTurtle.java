package slogo.view.windows.buttons;

import java.util.ResourceBundle;
import slogo.model.compiler.Parser;
import slogo.view.turtle.TurtleView;
import slogo.view.util.ButtonUtil;

/**
 * Button that spawns a new turtle with a random ID - created reflectively
 *
 * @author Andy S. He
 * @see slogo.view.windows.buttons.IDEButton
 * @see IDEButtonFactory
 * @see slogo.view.windows.sections.ButtonSection
 */
public class SpawnTurtle implements IDEButton {

  private static final String COMMAND = "Tell";
  private static final int SCALING_FACTOR = 5000;
  private static final String DELIMITER = " ";
  private static final String SYNTAX_1 = "[";
  private static final String SYNTAX_2 = "]";

  @Override
  public void doAction(ButtonUtil info) {
    ResourceBundle resourceBundle = ResourceBundle.getBundle(
        Parser.RESOURCES_PACKAGE + info.language());
    int NEW_ID = (int) (Math.random() * SCALING_FACTOR);
    String command =
        resourceBundle.getString(COMMAND).contains("|") ? resourceBundle.getString(COMMAND)
            .split("\\|")[0] : resourceBundle.getString(COMMAND);

    info.runner()
        .runAndSave(command + DELIMITER + SYNTAX_1 + DELIMITER + NEW_ID + DELIMITER + SYNTAX_2);
  }

}
