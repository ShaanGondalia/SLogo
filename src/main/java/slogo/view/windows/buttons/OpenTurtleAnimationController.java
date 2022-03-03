package slogo.view.windows.buttons;

import slogo.view.util.ButtonUtil;
import slogo.view.windows.Display;
import slogo.view.windows.TurtleAnimationController;

/**
 * Button that opens a new window containing a control panel for the turtle's animation properties
 *
 * @author Zack Schrage
 */
public class OpenTurtleAnimationController extends Display implements IDEButton {

  @Override
  public void doAction(ButtonUtil info) {
    new TurtleAnimationController(info);
  }

}
