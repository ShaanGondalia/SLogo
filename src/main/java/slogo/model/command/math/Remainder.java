package slogo.model.command.math;

import java.util.List;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Class that represents a Remainder command.
 *
 * @author Jake Heller
 */
public class Remainder extends MathOperation {

  private static final int NUM_ARGS = 2;
  private final Value arg1;
  private final Value arg2;

  /**
   * Creates a Remainder command
   *
   * @param turtle the Turtle that is attached to this command
   * @param args   the arguments that the command takes
   * @throws MissingArgumentException if the list of arguments does not contain enough arguments
   */
  public Remainder(List<Value> args) throws MissingArgumentException {
    super(args, NUM_ARGS);
    arg1 = args.get(0);
    arg2 = args.get(1);
  }

  /**
   * Calculates the Remainder
   *
   * @param turtle
   * @return arg1 % arg2
   */
  @Override
  public Value execute(Turtle turtle) {
    setReturnValue(arg1.getVal() % arg2.getVal());
    return returnValue();
  }
}
