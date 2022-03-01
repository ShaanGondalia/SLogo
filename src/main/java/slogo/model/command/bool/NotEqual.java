package slogo.model.command.bool;

import java.util.List;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Class that represents NotEqual command
 *
 * @author Jake Heller
 */
public class NotEqual extends BooleanOperation {

  private final Value arg1;
  private final Value arg2;
  private static final int NUM_ARGS = 2;

  public NotEqual(Turtle turtle, List<Value> args) throws MissingArgumentException {
    super(turtle, args, NUM_ARGS);
    arg1 = args.get(0);
    arg2 = args.get(1);
  }

  /**
   *
   * @return 1 if both expr1 and expr2 are non-zero
   * @throws MissingArgumentException
   */
  @Override
  public Value execute() {
    double val = (arg1.equals(arg2)) ? 0.0 : 1.0;
    setReturnValue(val);
    return returnValue();
  }
}