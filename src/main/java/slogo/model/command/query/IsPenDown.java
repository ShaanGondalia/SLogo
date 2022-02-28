package slogo.model.command.query;

import java.util.List;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Class that represents a IsPenDown query. Depends on TurtleQuery and Turtle.
 *
 * @author Jake Heller
 */
public class IsPenDown extends TurtleQuery {

  private static final int NUM_ARGS = 0;

  /**
   * Creates a IsPenDown command attached to the given Turtle.
   *
   * @param turtle the Turtle that is attached to this command
   * @param args   the arguments that the command takes
   * @throws MissingArgumentException if the list of arguments does not contain enough arguments
   */
  public IsPenDown(Turtle turtle, List<Value> args) throws MissingArgumentException {
    super(turtle, args, NUM_ARGS);
  }

  /**
   * Returns the IsPenDown of the queried turtle
   *
   * @return 1 if pen is down, 0 otherwise
   */
  @Override
  public Value execute() throws MissingArgumentException {
    double val = getTurtle().getStatus().penDown() ? 1.0 : 0.0;
    setReturnValue(val);
    return returnValue();
  }
}
