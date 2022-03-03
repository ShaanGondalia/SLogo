package slogo.view.windows.buttons;

import slogo.view.util.ButtonUtil;
import slogo.view.util.FileWriter;

public class CommandHistoryOut implements IDEButton {

  @Override
  public void doAction(ButtonUtil info) {
    FileWriter fw = new FileWriter(info.histSec().getHistoryText());
  }

}
