package slogo.model.command.math;

import java.util.List;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Class that represents a SquareRoot command.
 *
 * @author Jake Heller
 */
public class SquareRoot extends MathOperation {

  private final Value arg1;
  private static final int NUM_ARGS = 1;

  /**
   * Creates a SquareRoot command
   *
   * @param turtle the Turtle that is attached to this command
   * @param args   the arguments that the command takes
   * @throws MissingArgumentException if the list of arguments does not contain enough arguments
   */
  public SquareRoot(List<Value> args) throws MissingArgumentException {
    super(args, NUM_ARGS);
    arg1 = args.get(0);
  }

  /**
   * Calculates the Square Root
   *
   * @return sqrt(arg1)
   * @param turtle
   */
  @Override
  public Value execute(Turtle turtle) {
    setReturnValue(Math.sqrt(arg1.getVal()));
    return returnValue();
  }
}
