package slogo.model.command.bool;

import java.util.List;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Class that represents Less command
 *
 * @author Jake Heller
 */
public class Less extends BooleanOperation {

  private final Value arg1;
  private final Value arg2;
  private static final int NUM_ARGS = 2;

  public Less(Turtle turtle, List<Value> args) throws MissingArgumentException {
    super(turtle, args, NUM_ARGS);
    arg1 = args.get(0);
    arg2 = args.get(1);
  }

  /**
   *
   * @return 1 if expr1 < expr2, 0 otherwise
   * @throws MissingArgumentException
   */
  @Override
  public Value execute() {
    double val = arg1.compareTo(arg2) == -1 ? 1.0 : 0.0;
    setReturnValue(val);
    return returnValue();
  }
}