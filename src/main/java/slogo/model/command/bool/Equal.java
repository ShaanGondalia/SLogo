package slogo.model.command.bool;

import java.util.List;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Class that represents Equal command
 *
 * @author Jake Heller
 */
public class Equal extends BooleanOperation {

  private static final int NUM_ARGS = 2;
  private final Value arg1;
  private final Value arg2;

  public Equal(List<Value> args) throws MissingArgumentException {
    super(args, NUM_ARGS);
    arg1 = args.get(0);
    arg2 = args.get(1);
  }

  /**
   * @param turtle
   * @return 1 if expr1 == expr2
   * @throws MissingArgumentException
   */
  @Override
  public Value execute(Turtle turtle) {
    double val = (arg1.equals(arg2)) ? 1.0 : 0.0;
    setReturnValue(val);
    return returnValue();
  }
}
