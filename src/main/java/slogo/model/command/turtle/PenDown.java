package slogo.model.command.turtle;

import java.util.List;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Class that represents a pendown (pd) command. Depends on TurtleCommand and Turtle.
 *
 * @author Shaan Gondalia
 */
public class PenDown extends TurtleCommand {

  private static final int NUM_ARGS = 0;

  /**
   * Creates a pen down command. Takes no arguments
   *
   * @param args   the arguments for the command (no arguments for PenDown)
   * @throws MissingArgumentException if the list of arguments does not contain enough arguments
   */
  public PenDown(List<Value> args) throws MissingArgumentException {
    super(args, NUM_ARGS);
    setReturnValue(1.0);
  }

  /**
   * Puts the attached turtle's pen down
   *
   * @return 1
   * @param turtle the Turtle that will be rotated
   */
  @Override
  public Value execute(Turtle turtle) {
    turtle.setPen(true);
    return returnValue();
  }
}
