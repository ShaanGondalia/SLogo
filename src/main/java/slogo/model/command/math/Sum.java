package slogo.model.command.math;

import java.util.List;
import slogo.model.Turtle;
import slogo.model.exception.MissingArgumentException;

/**
 * Class that represents a sum (+) command. Depends on MathOperation.
 *
 * @author Shaan Gondalia
 */
public class Sum extends MathOperation {

  private final Double sum;
  private static final int NUM_ARGS = 2;

  /**
   * Creates a sum command
   * @param args the arguments for the command (single argument for sum)
   */
  public Sum(Turtle turtle, List<Double> args) throws MissingArgumentException {
    super(turtle, args, NUM_ARGS);
    sum = args.get(0) + args.get(1);
  }

  @Override
  public Double returnValue() {
    return sum;
  }
}
