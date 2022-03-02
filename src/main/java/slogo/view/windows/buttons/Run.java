package slogo.view.windows.buttons;

import slogo.view.util.ButtonUtil;

public class Run implements IDEButton {
  @Override
  public void doAction(ButtonUtil info){
    runString(info, info.textSec().getRawCommandText());
  }

  public void runString(ButtonUtil info, String s){
    info.c().runText(s);
    info.userDefinedSection().updateList();
    info.histSec().setNewHistory(s);
    info.textSec().clear();
  }

}
