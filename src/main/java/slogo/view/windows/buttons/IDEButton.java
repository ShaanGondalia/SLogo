package slogo.view.windows.buttons;

import slogo.view.util.ButtonUtil;

/**
 * The common interface that each of the classes that represent a button will implement
 *
 * @author Andy S. He
 */
public interface IDEButton {

  /**
   * Does that action that the button should do
   *
   * @param info contains the information that each of the buttons need to function
   */
  void doAction(ButtonUtil info);
}
