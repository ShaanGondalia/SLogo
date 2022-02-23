package slogo.model.command.turtle;

import java.util.List;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Class that represents a ClearScreen (cs) command. Depends on TurtleCommand and Turtle.
 *
 * @author Shaan Gondalia
 */
public class ClearScreen extends TurtleCommand {

  private static final int NUM_ARGS = 0;

  private Value distance;

  /**
   * Creates a clear screen command. Takes no arguments
   *
   * @param args   the arguments for the command (no arguments for ClearScreen)
   * @param turtle the Turtle that will be rotated
   * @throws MissingArgumentException if the list of arguments does not contain enough arguments
   */
  public ClearScreen(Turtle turtle, List<Value> args) throws MissingArgumentException {
    super(turtle, args, NUM_ARGS);
    distance = new Value();
  }

  /**
   * Shows the attached turtle
   *
   * @return 1
   */
  @Override
  public Value execute() {
    double x = getTurtle().getPose().x();
    double y = getTurtle().getPose().y();
    distance.setVal(Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)));
    getTurtle().goHome();
    getTurtle().clear();
    return returnValue();
  }

  /**
   * Returns 1
   *
   * @return 1
   */
  @Override
  public Value returnValue() {
    return distance;
  }
}
