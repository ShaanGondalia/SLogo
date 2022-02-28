package slogo.view.windows.buttons;

import slogo.Errors;
import slogo.view.util.ButtonUtil;

public class Run implements IDEButton {
  @Override
  public void doAction(ButtonUtil info){
    runString(info, info.textSec().getRawCommandText());
  }

  public void runString(ButtonUtil info, String s){

    try {
      info.c().runText(s);
    } catch (Exception e) {
      Errors.showError(e.getMessage());
      e.printStackTrace();
      return;
    }
    info.userDefinedSection().updateList();
    info.histSec().setNewHistory(s);
    info.textSec().clear();
  }
}
