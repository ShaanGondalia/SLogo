package slogo.model.command.math;

import java.util.List;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Class that represents a NaturalLog command.
 *
 * @author Jake Heller
 */
public class NaturalLog extends MathOperation {

  private final Value arg1;
  private static final int NUM_ARGS = 1;

  /**
   * Creates a NaturalLog command
   *
   * @param turtle the Turtle that is attached to this command
   * @param args   the arguments that the command takes
   * @throws MissingArgumentException if the list of arguments does not contain enough arguments
   */
  public NaturalLog(Turtle turtle, List<Value> args) throws MissingArgumentException {
    super(turtle, args, NUM_ARGS);
    arg1 = args.get(0);
  }

  /**
   * Calculates the NaturalLog
   *
   * @return ln(arg1)
   */
  @Override
  public Value execute() {
    setReturnValue(Math.log(arg1.getVal()));
    return returnValue();
  }
}