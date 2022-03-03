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

  /**
   * Creates a clear screen command. Takes no arguments
   *
   * @param args the arguments for the command (no arguments for ClearScreen)
   * @throws MissingArgumentException if the list of arguments does not contain enough arguments
   */
  public ClearScreen(List<Value> args) throws MissingArgumentException {
    super(args, NUM_ARGS);
  }

  /**
   * Shows the attached turtle
   *
   * @param turtle the Turtle that will be rotated
   * @return distance turtle moved
   */
  @Override
  public Value execute(Turtle turtle) {
    double x = turtle.getPose().x();
    double y = turtle.getPose().y();
    turtle.goHome();
    turtle.clear();

    setReturnValue(Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)));
    return returnValue();
  }
}
