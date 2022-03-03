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
   * @param args the arguments for the command (no arguments for ShowTurtle)
   * @throws MissingArgumentException if the list of arguments does not contain enough arguments
   */
  public ShowTurtle(List<Value> args) throws MissingArgumentException {
    super(args, NUM_ARGS);
    setReturnValue(1.0);
  }

  /**
   * Shows the attached turtle
   *
   * @param turtle the turtle to show
   * @return 1
   */
  @Override
  public Value execute(Turtle turtle) {
    turtle.setVisibility(true);
    return returnValue();
  }
}
