package slogo.model.command.bool;

import java.util.List;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Class that represents a greater command. Depends on BooleanOperation and Turtle.
 *
 * @author Shaan Gondalia
 */
public class Greater extends BooleanOperation {

  private static final int NUM_ARGS = 2;
  private final Value arg1;
  private final Value arg2;

  /**
   * Creates a greater command
   *
   * @param args the arguments that the command takes
   * @throws MissingArgumentException if the list of arguments does not contain enough arguments
   */
  public Greater(List<Value> args) throws MissingArgumentException {
    super(args, NUM_ARGS);
    arg1 = args.get(0);
    arg2 = args.get(1);
  }

  /**
   * Gets the return value of the greater operation.
   *
   * @param turtle
   * @return 1 if expr1 > expr2, 0 otherwise
   */
  @Override
  public Value execute(Turtle turtle) {
    setReturnValue(arg1.compareTo(arg2) == 1 ? 1.0 : 0.0);
    return returnValue();
  }
}
