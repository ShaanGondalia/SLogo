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

  @Override
  public void doAction(ButtonUtil info) {
    Errors.justClose();
  }
}
