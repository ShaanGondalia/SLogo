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
   * @param args   the arguments that the command takes
   * @throws MissingArgumentException if the list of arguments does not contain enough arguments
   */
  public IsPenDown(List<Value> args) throws MissingArgumentException {
    super(args, NUM_ARGS);
  }

  /**
   * Returns the IsPenDown of the queried turtle
   *
   * @param turtle
   * @return 1 if pen is down, 0 otherwise
   */
  @Override
  public Value execute(Turtle turtle) throws MissingArgumentException {
    double val = turtle.getStatus().penState().penDown() ? 1.0 : 0.0;
    setReturnValue(val);
    return returnValue();
  }
}
