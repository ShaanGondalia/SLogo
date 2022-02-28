package slogo.model.command.query;

import java.util.List;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Class that represents a YCoordinate query. Depends on TurtleQuery and Turtle.
 *
 * @author Jake Heller
 */
public class YCoordinate extends TurtleQuery {

  private static final int NUM_ARGS = 0;

  /**
   * Creates an YCoordinate command attached to the given Turtle.
   *
   * @param turtle the Turtle that is attached to this command
   * @param args   the arguments that the command takes
   * @throws MissingArgumentException if the list of arguments does not contain enough arguments
   */
  public YCoordinate(Turtle turtle, List<Value> args) throws MissingArgumentException {
    super(turtle, args, NUM_ARGS);
  }

  /**
   * Returns the y coordinate of the queried
   *
   * @return the y coordinate of the queried turtle
   */
  @Override
  public Value execute() throws MissingArgumentException {
    setReturnValue(getTurtle().getPose().y());
    return returnValue();
  }
}
