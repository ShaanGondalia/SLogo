package slogo.view.windows.buttons;

import slogo.view.util.ButtonUtil;
import slogo.view.util.FileWriter;

/**
 * Button that saves the command history to a file - created reflectively
 *
 * @author Andy S. He
 * @see slogo.view.windows.buttons.IDEButton
 * @see IDEButtonFactory
 * @see slogo.view.windows.sections.ButtonSection
 */
public class CommandHistoryOut implements IDEButton {

  @Override
  public void doAction(ButtonUtil info) {
    FileWriter fw = new FileWriter(info.histSec().getHistoryText());
  }

}
