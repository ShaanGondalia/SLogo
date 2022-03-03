package slogo.model.command.bool;

import java.util.List;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Class that represents Not command
 *
 * @author Jake Heller
 */
public class Not extends BooleanOperation {

  private final Value arg1;
  private static final int NUM_ARGS = 1;

  public Not(List<Value> args) throws MissingArgumentException {
    super(args, NUM_ARGS);
    arg1 = args.get(0);
  }

  /**
   *
   * @return 1 if both expr1 and expr2 are non-zero
   * @throws MissingArgumentException
   * @param turtle
   */
  @Override
  public Value execute(Turtle turtle) {
    double val = (arg1.equals(new Value(0))) ? 1.0 : 0.0;
    setReturnValue(val);
    return returnValue();
  }
}
