package slogo.model.command.math;

import java.util.List;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Class that represents a Tangent command.
 *
 * @author Jake Heller
 */
public class Tangent extends MathOperation {

  private static final int NUM_ARGS = 1;
  private final Value arg1;

  /**
   * Creates a Tangent command
   *
   * @param args the arguments that the command takes
   * @throws MissingArgumentException if the list of arguments does not contain enough arguments
   */
  public Tangent(List<Value> args) throws MissingArgumentException {
    super(args, NUM_ARGS);
    arg1 = args.get(0);
  }

  /**
   * Calculates the Tangent
   *
   * @param turtle the Turtle that is attached to this command
   * @return tan(arg1)
   */
  @Override
  public Value execute(Turtle turtle) {
    setReturnValue(Math.tan(Math.toRadians(arg1.getVal())));
    return returnValue();
  }
}
