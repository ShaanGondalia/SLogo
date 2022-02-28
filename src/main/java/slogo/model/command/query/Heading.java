package slogo.model.command.query;

import java.util.List;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Class that represents a Heading query. Depends on TurtleQuery and Turtle.
 *
 * @author Jake Heller
 */
public class Heading extends TurtleQuery {

  private static final int NUM_ARGS = 0;

  /**
   * Creates a Heading command attached to the given Turtle.
   *
   * @param turtle the Turtle that is attached to this command
   * @param args   the arguments that the command takes
   * @throws MissingArgumentException if the list of arguments does not contain enough arguments
   */
  public Heading(Turtle turtle, List<Value> args) throws MissingArgumentException {
    super(turtle, args, NUM_ARGS);
  }

  /**
   * Returns the heading of the queried turtle
   *
   * @return the heading of the queried turtle
   */
  @Override
  public Value execute() throws MissingArgumentException {
    setReturnValue(getTurtle().getPose().bearing());
    return returnValue();
  }
}
