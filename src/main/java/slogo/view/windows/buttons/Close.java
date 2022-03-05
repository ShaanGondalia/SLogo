package slogo.view.windows.buttons;

import slogo.Errors;
import slogo.view.util.ButtonUtil;

/**
 * Button that closes all the windows - created reflectively
 *
 * @author Andy S. He
 * @see slogo.view.windows.buttons.IDEButton
 * @see IDEButtonFactory
 * @see slogo.view.windows.sections.ButtonSection
 */
public class Close implements IDEButton {

  /**
   * see above
   *
   * @param info contains the information that each of the buttons need to function
   */
  @Override
  public void doAction(ButtonUtil info) {
    Errors.justClose();
  }
}
