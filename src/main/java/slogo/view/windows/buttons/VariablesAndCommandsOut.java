package slogo.view.windows.buttons;

import java.io.FileNotFoundException;
import slogo.view.util.ButtonUtil;
import slogo.view.util.FileWriter;

public class VariablesAndCommandsOut implements IDEButton{

  @Override
  public void doAction(ButtonUtil info){
    FileWriter fw = new FileWriter(info.userDefinedSection().getVariableAndCommandText());
  }
}
