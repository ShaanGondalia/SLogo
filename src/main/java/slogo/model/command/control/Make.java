package slogo.model.command.control;

import java.util.List;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Class that represents a make (set) command. Depends on ControlCommand and Turtle.
 *
 * @author Shaan Gondalia
 */
public class Make extends ControlCommand {

  private static final int NUM_ARGS = 2;
  private final Value variable;
  private final Value expr;

  /**
   * Creates a make command
   *
   * @param args the arguments for the command (two arguments for Make)
   * @throws MissingArgumentException if the list of arguments does not contain enough arguments
   */
  public Make(List<Value> args) throws MissingArgumentException {
    super(args, NUM_ARGS);
    variable = args.get(0);
    expr = args.get(1);
  }

  /**
   * Sets a variable to the given value
   *
   * @param turtle the Turtle that will be moved when the command is executed
   * @return the value of the expression
   */
  @Override
  public Value execute(Turtle turtle) {
    variable.setVal(expr.getVal());
    setReturnValue(expr.getVal());
    return returnValue();
  }
}
