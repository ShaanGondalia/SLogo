package slogo.model.command.math;

import java.util.List;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Class that represents a Difference command.
 *
 * @author Jake Heller
 */
public class Difference extends MathOperation {

  private final Value arg1;
  private final Value arg2;
  private static final int NUM_ARGS = 2;

  /**
   * Creates a Difference command
   *
   * @param turtle the Turtle that is attached to this command
   * @param args   the arguments that the command takes
   * @throws MissingArgumentException if the list of arguments does not contain enough arguments
   */
  public Difference(Turtle turtle, List<Value> args) throws MissingArgumentException {
    super(turtle, args, NUM_ARGS);
    arg1 = args.get(0);
    arg2 = args.get(1);
  }

  /**
   * Calculates the difference
   *
   * @return arg1 - arg2
   */
  @Override
  public Value execute() {
    setReturnValue(arg1.getVal() - arg2.getVal());
    return returnValue();
  }
}
