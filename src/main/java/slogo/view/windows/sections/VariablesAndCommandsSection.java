package slogo.view.windows.sections;

import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import slogo.Errors;
import slogo.controller.Controller;
import slogo.model.compiler.Parser;
import slogo.view.util.Runner;
import slogo.view.windows.MainIDEView;

/**
 * Section that displays to the user the user-defined commands and variables
 *
 * @author Andy S. He
 */
public class VariablesAndCommandsSection implements IDESection {

  protected static final String DELIMITER = ": ";
  protected static final String NEW_LINE = "\n";







  private BorderPane myVarAndComSec;
  private VariablesSection myVarSec;
  private CommandsSection myComSec;

  /**
   * Constructor for the Variables and Commands Section
   *
   * @param c        The controller to get the variables and commands from the model
   * @param language The language to run the commands in
   * @param runner   The runner to run code by clicking on elements in this section
   */
  public VariablesAndCommandsSection(Controller c, String language, Runner runner) {
    myVarAndComSec = new BorderPane();
    myVarSec = new VariablesSection(c, language,runner);
    myComSec = new CommandsSection(c, language,runner);
    myVarAndComSec.setLeft(myVarSec.getSection());
    myVarAndComSec.setRight(myComSec.getSection());
  }


  /**
   * Updates the display to display the current variables and user-defined section
   */
  public void update() {
    myVarSec.updateVariables();
    myComSec.updateUserCommands();
  }



  /**
   * Allows for the writing to a file of correctly formatted syntax
   *
   * @return String to Write to a file
   */
  public String getVariableAndCommandText() {
    return myVarSec.getVarText() + myComSec.getComText();
  }




  @Override
  public Region getSection() {
    return myVarAndComSec;
  }
}
