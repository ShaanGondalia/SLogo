package slogo.model.command.query;

import java.util.List;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Class that represents a IsShowing query. Depends on TurtleQuery and Turtle.
 *
 * @author Jake Heller
 */
public class IsShowing extends TurtleQuery {

  private static final int NUM_ARGS = 0;

  /**
   * Creates a IsShowing command attached to the given Turtle.
   *
   * @param turtle the Turtle that is attached to this command
   * @param args   the arguments that the command takes
   * @throws MissingArgumentException if the list of arguments does not contain enough arguments
   */
  public IsShowing(Turtle turtle, List<Value> args) throws MissingArgumentException {
    super(turtle, args, NUM_ARGS);
  }

  /**
   * Returns the IsShowing of the queried turtle
   *
   * @return 1 if turtle is showing, 0 otherwise
   */
  @Override
  public Value execute() throws MissingArgumentException {
    double val = getTurtle().getStatus().visibility() ? 1.0 : 0.0;
    setReturnValue(val);
    return returnValue();
  }
}
