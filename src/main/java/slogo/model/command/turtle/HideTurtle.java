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
   * @param turtle the Turtle that will be rotated
   * @throws MissingArgumentException if the list of arguments does not contain enough arguments
   */
  public HideTurtle(Turtle turtle, List<Value> args) throws MissingArgumentException {
    super(turtle, args, NUM_ARGS);
    setReturnValue(0.0);
  }

  /**
   * Hides the attached turtle
   *
   * @return 0
   */
  @Override
  public Value execute() {
    getTurtle().setVisibility(false);
    return returnValue();
  }
}
