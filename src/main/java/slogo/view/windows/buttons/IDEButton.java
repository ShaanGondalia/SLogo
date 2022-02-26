package slogo.view.windows.buttons;

import slogo.view.util.ButtonUtil;

public interface IDEButton {

  /**
   * Does that action that the button should do
   * @param info contains the information that each of the buttons need to function
   */
  void doAction(ButtonUtil info);
}
