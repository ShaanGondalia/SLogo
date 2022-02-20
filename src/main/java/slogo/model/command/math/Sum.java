package slogo.model.command.math;

import java.util.List;
import slogo.model.turtle.Turtle;
import slogo.model.exception.MissingArgumentException;

/**
 * Class that represents a sum (+) command. Depends on MathOperation and Turtle.
 *
 * @author Shaan Gondalia
 */
public class Sum extends MathOperation {

  private final Double sum;
  private static final int NUM_ARGS = 2;

  /**
   * Creates a sum command
   *
   * @param turtle the Turtle that is attached to this command
   * @param args the arguments that the command takes
   * @throws MissingArgumentException if the list of arguments does not contain enough arguments
   */
  public Sum(Turtle turtle, List<Double> args) throws MissingArgumentException {
    super(turtle, args, NUM_ARGS);
    sum = args.get(0) + args.get(1);
  }

  /**
   * Gets the returnValue of the operation. Implemented by subclasses.
   *
   * @return arg1 + arg2
   */
  @Override
  public Double returnValue() {
    return sum;
  }
}
