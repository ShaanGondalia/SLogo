package slogo.model.command.turtle;

import java.util.List;
import slogo.model.turtle.Turtle;
import slogo.model.exception.MissingArgumentException;

/**
 * Class that represents a right (rt) command. Depends on TurtleCommand and Turtle.
 *
 * @author Shaan Gondalia
 */
public class Right extends TurtleCommand {

  private final double degrees;
  private static final int NUM_ARGS = 1;

  /**
   * Creates a right command. Takes a single argument
   * @param args the arguments for the command (single argument for Right)
   * @param turtle the Turtle that will be rotated
   * @throws MissingArgumentException if the list of arguments does not contain enough arguments
   */
  public Right(Turtle turtle, List<Double> args) throws MissingArgumentException {
    super(turtle, args, NUM_ARGS);
    degrees = args.get(0);
  }

  /**
   * Rotates the turtle Right (CW) by the given number of degrees
   *
   * @return the number of degrees the turtle will rotate
   */
  @Override
  public Double execute() {
    getTurtle().rotate(degrees);
    return degrees;
  }

  /**
   * Returns the number of degrees the turtle will rotate
   * @return the number of degrees the turtle will rotate
   */
  @Override
  public Double returnValue() {
    return degrees;
  }
}
