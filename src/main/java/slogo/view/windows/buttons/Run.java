package slogo.view.windows.buttons;

import slogo.view.util.ButtonUtil;

public class Run implements IDEButton {
  @Override
  public void doAction(ButtonUtil info){
    info.runner().runAndSave(info.textSec().getRawCommandText());
    info.textSec().clear();
  }
}
