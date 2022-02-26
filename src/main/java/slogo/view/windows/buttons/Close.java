package slogo.view.windows.buttons;

import slogo.Errors;
import slogo.view.util.ButtonUtil;

public class Close implements IDEButton{

  @Override
  public void doAction(ButtonUtil info) {
    Errors.justClose();
  }
}
