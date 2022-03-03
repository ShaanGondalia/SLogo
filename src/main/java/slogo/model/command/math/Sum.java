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
  private static final int NUM_ARGS = 2;

  /**
   * Creates a sum command
   *
   * @param turtle the Turtle that is attached to this command
   * @param args   the arguments that the command takes
   * @throws MissingArgumentException if the list of arguments does not contain enough arguments
   */
  public Sum(List<Value> args) throws MissingArgumentException {
    super(args, NUM_ARGS);
    arg1 = args.get(0);
    arg2 = args.get(1);
  }

  /**
   * Calculates the sum of the two arguments
   *
   * @return arg1 + arg2
   * @param turtle
   */
  @Override
  public Value execute(Turtle turtle) {
    setReturnValue(arg1.getVal() + arg2.getVal());
    return returnValue();
  }
}
