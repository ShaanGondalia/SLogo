package slogo.view.windows.buttons;

import slogo.view.util.ButtonUtil;

/**
 * Button that runs the text in the current textbox - created reflectively
 *
 * @author Andy S. He
 * @see slogo.view.windows.buttons.IDEButton
 * @see IDEButtonFactory
 * @see slogo.view.windows.sections.ButtonSection
 */
public class Run implements IDEButton {

  /**
   * see above
   *
   * @param info contains the information that each of the buttons need to function
   */
  @Override
  public void doAction(ButtonUtil info) {
    info.runner().runAndSave(info.textSec().getRawCommandText());
    info.textSec().clear();
  }
}
