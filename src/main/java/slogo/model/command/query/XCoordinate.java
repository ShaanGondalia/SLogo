package slogo.model.command.query;

import java.util.List;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Class that represents a XCoordinate query. Depends on TurtleQuery and Turtle.
 *
 * @author Shaan Gondalia
 */
public class XCoordinate extends TurtleQuery {

  private static final int NUM_ARGS = 0;

  /**
   * Creates an XCoordinate command attached to the given Turtle.
   *
   * @param turtle the Turtle that is attached to this command
   * @param args   the arguments that the command takes
   * @throws MissingArgumentException if the list of arguments does not contain enough arguments
   */
  public XCoordinate(List<Value> args) throws MissingArgumentException {
    super(args, NUM_ARGS);
  }

  /**
   * Returns the x coordinate of the queried
   *
   * @return the x coordinate of the queried turtle
   * @param turtle
   */
  @Override
  public Value execute(Turtle turtle) throws MissingArgumentException {
    setReturnValue(turtle.getPose().x());
    return returnValue();
  }
}
