package slogo.model.command.math;

import java.util.List;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Class that represents a RandomRange command.
 *
 * @author Jake Heller
 */
public class RandomRange extends MathOperation {

  private final Value arg1;
  private final Value arg2;
  private static final int NUM_ARGS = 2;

  /**
   * Creates a RandomRange command
   *
   * @param turtle the Turtle that is attached to this command
   * @param args   the arguments that the command takes
   * @throws MissingArgumentException if the list of arguments does not contain enough arguments
   */
  public RandomRange(List<Value> args) throws MissingArgumentException {
    super(args, NUM_ARGS);
    arg1 = args.get(0);
    arg2 = args.get(1);
  }

  /**
   * Returns a random value between arg1 and arg2 inclusive
   *
   * @return random Value in range [arg1, arg2]
   * @param turtle
   */
  @Override
  public Value execute(Turtle turtle) {
    double min = arg1.getVal();
    double max = arg2. getVal();

    double rand = (max - min) * Math.random() + min;

    setReturnValue(rand);
    return returnValue();
  }
}
