package slogo.model.command.turtle;

import java.util.List;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Class that represents a hideturtle (ht) command. Depends on TurtleCommand and Turtle.
 *
 * @author Shaan Gondalia
 */
public class HideTurtle extends TurtleCommand {

  private static final int NUM_ARGS = 0;

  /**
   * Creates a hide turtle command. Takes no arguments
   *
   * @param args   the arguments for the command (no arguments for HideTurtle)
   * @throws MissingArgumentException if the list of arguments does not contain enough arguments
   */
  public HideTurtle(List<Value> args) throws MissingArgumentException {
    super(args, NUM_ARGS);
    setReturnValue(0.0);
  }

  /**
   * Hides the attached turtle
   *
   * @return 0
   * @param turtle the Turtle that will be rotated
   */
  @Override
  public Value execute(Turtle turtle) {
    turtle.setVisibility(false);
    return returnValue();
  }
}
