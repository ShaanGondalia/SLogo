package slogo.model.command.math;

import java.util.List;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Class that represents a Cosine command.
 *
 * @author Jake Heller
 */
public class Cosine extends MathOperation {

  private final Value arg1;
  private static final int NUM_ARGS = 1;

  /**
   * Creates a Cosine command
   *
   * @param args   the arguments that the command takes
   * @throws MissingArgumentException if the list of arguments does not contain enough arguments
   */
  public Cosine(List<Value> args) throws MissingArgumentException {
    super(args, NUM_ARGS);
    arg1 = args.get(0);
  }

  /**
   * Calculates the cosine of the first argument
   *
   * @return cos(arg1)
   * @param turtle the Turtle that is attached to this command
   */
  @Override
  public Value execute(Turtle turtle) {
    setReturnValue(Math.cos(Math.toRadians(arg1.getVal())));
    return returnValue();
  }
}
