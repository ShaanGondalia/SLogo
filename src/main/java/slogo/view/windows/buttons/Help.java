package slogo.view.windows.buttons;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.stage.Stage;
import slogo.view.util.ButtonUtil;
import slogo.view.windows.HelpView;

/**
 * Button that opens a help menu to display all the commands in english - created reflectively
 *
 * @author Andy S. He
 * @see slogo.view.windows.buttons.IDEButton
 * @see IDEButtonFactory
 * @see slogo.view.windows.sections.ButtonSection
 */
public class Help implements IDEButton {

  /**
   * see above
   *
   * @param info contains the information that each of the buttons need to function
   */
  @Override
  public void doAction(ButtonUtil info) {
    HelpView helpView = new HelpView();
  }

}
