package slogo.model.command.turtle;

import java.util.List;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Class that represents a ShowTurtle (st) command. Depends on TurtleCommand and Turtle.
 *
 * @author Shaan Gondalia
 */
public class ShowTurtle extends TurtleCommand {

  private static final int NUM_ARGS = 0;

  /**
   * Creates a show turtle command. Takes no arguments
   *
   * @param args   the arguments for the command (no arguments for ShowTurtle)
   * @param turtle the Turtle that will be rotated
   * @throws MissingArgumentException if the list of arguments does not contain enough arguments
   */
  public ShowTurtle(Turtle turtle, List<Value> args) throws MissingArgumentException {
    super(turtle, args, NUM_ARGS);
    setReturnValue(1.0);
  }

  /**
   * Shows the attached turtle
   *
   * @return 1
   */
  @Override
  public Value execute() {
    getTurtle().setVisibility(true);
    return returnValue();
  }
}
