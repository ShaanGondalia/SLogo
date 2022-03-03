package slogo.model.command.math;

import java.util.List;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Class that represents a Sine command.
 *
 * @author Jake Heller
 */
public class Sine extends MathOperation {

  private final Value arg1;
  private static final int NUM_ARGS = 1;

  /**
   * Creates a Sine command
   *
   * @param turtle the Turtle that is attached to this command
   * @param args   the arguments that the command takes
   * @throws MissingArgumentException if the list of arguments does not contain enough arguments
   */
  public Sine(List<Value> args) throws MissingArgumentException {
    super(args, NUM_ARGS);
    arg1 = args.get(0);
  }

  /**
   * Calculates the Sine
   *
   * @return sin(arg1)
   * @param turtle
   */
  @Override
  public Value execute(Turtle turtle) {
    setReturnValue(Math.sin(arg1.getVal()));
    return returnValue();
  }
}
