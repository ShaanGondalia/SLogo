package slogo.view.windows.buttons;

import java.io.FileNotFoundException;
import slogo.view.util.ButtonUtil;
import slogo.view.util.FileWriter;

/**
 * Button that saves only the variables and commands - created reflectively
 *
 * @author Andy S. He
 * @see slogo.view.windows.buttons.IDEButton
 * @see IDEButtonFactory
 * @see slogo.view.windows.sections.ButtonSection
 */
public class VariablesAndCommandsOut implements IDEButton {

  @Override
  public void doAction(ButtonUtil info) {
    FileWriter fw = new FileWriter(info.userDefinedSection().getVariableAndCommandText());
  }
}
