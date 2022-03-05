package slogo.view.windows.buttons;

import javafx.stage.Stage;
import slogo.Errors;
import slogo.Main;
import slogo.view.util.ButtonUtil;

/**
 * Button that creates a new workspace - created reflectively
 *
 * @author Andy S. He
 * @see slogo.view.windows.buttons.IDEButton
 * @see IDEButtonFactory
 * @see slogo.view.windows.sections.ButtonSection
 */
public class CreateNewWorkspace implements IDEButton {

  /**
   * see above
   *
   * @param info contains the information that each of the buttons need to function
   */
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
