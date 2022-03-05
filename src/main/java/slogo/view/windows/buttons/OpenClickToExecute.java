package slogo.view.windows.buttons;

import javafx.stage.Stage;
import slogo.view.util.ButtonUtil;
import slogo.view.util.ClickToExecuteGUI;

/**
 * Button that opens the Click-to-Execute GUI - created reflectively
 *
 * @author Andy S. He
 * @see slogo.view.windows.buttons.IDEButton
 * @see IDEButtonFactory
 * @see slogo.view.windows.sections.ButtonSection
 */
public class OpenClickToExecute implements IDEButton {

  /**
   * see above
   *
   * @param info contains the information that each of the buttons need to function
   */
  @Override
  public void doAction(ButtonUtil info) {
    ClickToExecuteGUI gui = new ClickToExecuteGUI(info, new Stage());
  }
}
