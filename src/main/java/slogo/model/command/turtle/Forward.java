package slogo.model.command.turtle;

import java.util.List;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Class that represents a forward (fd) command. Depends on TurtleCommand and Turtle.
 *
 * @author Shaan Gondalia
 */
public class  Forward extends TurtleCommand {

  private final Value pixels;
  private static final int NUM_ARGS = 1;

  /**
   * Creates a forward command
   *
   * @param args   the arguments for the command (single argument for FD)
   * @param turtle the Turtle that will be moved when the command is executed
   * @throws MissingArgumentException if the list of arguments does not contain enough arguments
   */
  public Forward(Turtle turtle, List<Value> args) throws MissingArgumentException {
    super(turtle, args, NUM_ARGS);
    pixels = args.get(0);
  }

  /**
   * Moves the attached turtle forward given the arguments
   *
   * @return the number of pixels the turtle will move
   */
  @Override
  public Value execute() {
    getTurtle().move(pixels.getVal());
    setReturnValue(pixels.getVal());
    return returnValue();
  }
}
