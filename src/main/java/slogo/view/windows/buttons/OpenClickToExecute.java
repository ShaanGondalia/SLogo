package slogo.view.windows.buttons;

import javafx.stage.Stage;
import slogo.view.util.ButtonUtil;
import slogo.view.util.ClickToExecuteGUI;

public class OpenClickToExecute implements IDEButton{

  @Override
  public void doAction(ButtonUtil info) {
    ClickToExecuteGUI gui = new ClickToExecuteGUI(info, new Stage());
  }
}
