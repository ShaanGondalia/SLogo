package slogo.view.windows.buttons;

import slogo.Errors;
import slogo.view.util.ButtonUtil;

public class Run implements IDEButton {

  public void doAction(ButtonUtil info){
    try {
      info.c().runText(info.textSec().getRawCommandText());
    } catch (Exception e) {
      Errors.showError(e.getMessage());
      return;
    }

    info.histSec().setNewHistory(info.textSec().getRawCommandText());
    info.textSec().clear();
  }
}
