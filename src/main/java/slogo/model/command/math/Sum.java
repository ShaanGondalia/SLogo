package slogo.model.command.math;

import java.util.List;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Class that represents a sum (+) command. Depends on MathOperation and Turtle.
 *
 * @author Shaan Gondalia
 */
public class Sum extends MathOperation {

  private final Value arg1;
  private final Value arg2;
  private Value sum;
  private static final int NUM_ARGS = 2;

  /**
   * Creates a sum command
   *
   * @param turtle the Turtle that is attached to this command
   * @param args   the arguments that the command takes
   * @throws MissingArgumentException if the list of arguments does not contain enough arguments
   */
  public Sum(Turtle turtle, List<Value> args) throws MissingArgumentException {
    super(turtle, args, NUM_ARGS);
    sum = new Value();
    arg1 = args.get(0);
    arg2 = args.get(1);
  }

  /**
   * Calculates the sum of the two arguments
   *
   * @return the sum of the two arguments
   */
  @Override
  public Value execute() {
    sum.setVal(arg1.getVal() + arg2.getVal());
    return returnValue();
  }

  /**
   * Gets the returnValue of the operation. Implemented by subclasses.
   *
   * @return arg1 + arg2
   */
  @Override
  public Value returnValue() {
    return sum;
  }
}
