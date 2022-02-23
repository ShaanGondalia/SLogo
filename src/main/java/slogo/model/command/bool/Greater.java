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

  private final Value greater;
  private static final int NUM_ARGS = 2;

  /**
   * Creates a greater command
   *
   * @param turtle the Turtle that is attached to this command
   * @param args   the arguments that the command takes
   * @throws MissingArgumentException if the list of arguments does not contain enough arguments
   */
  public Greater(Turtle turtle, List<Value> args) throws MissingArgumentException {
    super(turtle, args, NUM_ARGS);
    greater = new Value(args.get(0).getVal() > args.get(1).getVal() ? 1 : 0);
  }

  /**
   * Gets the return value of the greater operation.
   *
   * @return 1 if expr1 > expr2, 0 otherwise
   */
  @Override
  public Value returnValue() {
    return greater;
  }
}
