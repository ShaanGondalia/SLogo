package slogo.model.command.math;

import java.util.List;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Class that represents a random (rand) command. Depends on MathOperation and Turtle.
 *
 * @author Shaan Gondalia
 */
public class Random extends MathOperation {

  private final Value random;
  private static final int NUM_ARGS = 1;

  /**
   * Creates a random command
   *
   * @param turtle the Turtle that is attached to this command
   * @param args   the arguments that the command takes
   * @throws MissingArgumentException if the list of arguments does not contain enough arguments
   */
  public Random(Turtle turtle, List<Value> args) throws MissingArgumentException {
    super(turtle, args, NUM_ARGS);
    random = new Value(Math.random() * args.get(0).getVal());
  }

  /**
   * Returns a random number strictly less than arg1
   *
   * @return a random number strictly less than arg1
   */
  @Override
  public Value returnValue() {
    return random;
  }
}
