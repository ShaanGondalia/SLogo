package slogo.model.command.bool;

import java.util.List;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Class that represents And command
 *
 * @author Jake Heller
 */
public class Or extends BooleanOperation {

  private static final int NUM_ARGS = 2;
  private final Value arg1;
  private final Value arg2;

  public Or(List<Value> args) throws MissingArgumentException {
    super(args, NUM_ARGS);
    arg1 = args.get(0);
    arg2 = args.get(1);
  }

  /**
   * @param turtle
   * @return 1 if expr1 or expr2 are non-zero
   * @throws MissingArgumentException
   */
  @Override
  public Value execute(Turtle turtle) {
    double val = (!arg1.equals(new Value()) || !arg2.equals(new Value())) ? 1.0 : 0.0;
    setReturnValue(val);
    return returnValue();
  }
}
