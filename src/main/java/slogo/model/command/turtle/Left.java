package slogo.model.command.turtle;

import java.util.List;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Class that represents a left (lt) command. Depends on TurtleCommand and Turtle.
 *
 * @author Shaan Gondalia
 */
public class Left extends TurtleCommand {

  private final double degrees;
  private static final int NUM_ARGS = 1;

  /**
   * Creates a left command. Takes a single argument
   *
   * @param args   the arguments for the command (single argument for Left)
   * @param turtle the Turtle that will be rotated
   * @throws MissingArgumentException if the list of arguments does not contain enough arguments
   */
  public Left(Turtle turtle, List<Double> args) throws MissingArgumentException {
    super(turtle, args, NUM_ARGS);
    degrees = args.get(0);
  }

  /**
   * Rotates the turtle Left (CCW) by the given number of degrees
   *
   * @return the number of degrees the turtle will rotate
   */
  @Override
  public Double execute() {
    getTurtle().rotate(-degrees);
    return degrees;
  }

  /**
   * Returns the number of degrees the turtle will rotate
   *
   * @return the number of degrees the turtle will rotate
   */
  @Override
  public Double returnValue() {
    return degrees;
  }
}
