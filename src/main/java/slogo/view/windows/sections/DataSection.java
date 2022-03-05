package slogo.view.windows.sections;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import slogo.controller.Controller;
import slogo.view.util.Runner;

/**
 * Section that displays to the user the user-defined commands and variables
 *
 * @author Andy S. He
 */
public class DataSection implements IDESection {

  protected static final String DELIMITER = ": ";
  protected static final String NEW_LINE = "\n";


  private final BorderPane myDataSec;
  private final VariablesSection myVarSec;
  private final CommandsSection myComSec;
  private TurtlesAndColorsSection myTAndCSec;

  /**
   * Constructor for the Variables and Commands Section
   *
   * @param c        The controller to get the variables and commands from the model
   * @param language The language to run the commands in
   * @param runner   The runner to run code by clicking on elements in this section
   */
  public DataSection(Controller c, String language, Runner runner) {
    myDataSec = new BorderPane();
    myVarSec = new VariablesSection(c, language, runner);
    myComSec = new CommandsSection(c, language);
    myTAndCSec = new TurtlesAndColorsSection(c, language);
    myDataSec.setLeft(myVarSec.getSection());
    myDataSec.setCenter(myComSec.getSection());
    myDataSec.setRight(myTAndCSec.getSection());
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

  /**
   * get the region that can be set to a specific location on the MainIDEView
   *
   * @return a BorderPane
   */
  @Override
  public Region getSection() {
    return myDataSec;
  }
}
