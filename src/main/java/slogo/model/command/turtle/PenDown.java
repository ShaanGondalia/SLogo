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
   * @param turtle the Turtle that will be rotated
   * @throws MissingArgumentException if the list of arguments does not contain enough arguments
   */
  public PenDown(Turtle turtle, List<Value> args) throws MissingArgumentException {
    super(turtle, args, NUM_ARGS);
    setReturnValue(1.0);
  }

  /**
   * Puts the attached turtle's pen down
   *
   * @return 1
   */
  @Override
  public Value execute() {
    getTurtle().setPen(true);
    return returnValue();
  }
}
