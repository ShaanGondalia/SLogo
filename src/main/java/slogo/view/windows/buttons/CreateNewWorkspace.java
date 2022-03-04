package slogo.view.windows.buttons;

import javafx.stage.Stage;
import slogo.Errors;
import slogo.Main;
import slogo.view.util.ButtonUtil;

public class CreateNewWorkspace implements IDEButton {

  @Override
  public void doAction(ButtonUtil info) {
    Stage stage = new Stage();
    Main main = new Main();
    try {
      main.start(stage);
    } catch (Exception e) {
      Errors.showAndClose("internal error");
    }
  }
}
