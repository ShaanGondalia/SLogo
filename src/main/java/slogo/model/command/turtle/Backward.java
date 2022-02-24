package slogo.model.command.turtle;

import java.util.List;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Class that represents a backward (bk) command. Depends on TurtleCommand and Turtle.
 *
 * @author Shaan Gondalia
 */
public class Backward extends TurtleCommand {

  private final Value pixels;
  private static final int NUM_ARGS = 1;

  /**
   * Creates a backward command
   *
   * @param args   the arguments for the command (single argument for BK)
   * @param turtle the Turtle that will be moved when the command is executed
   * @throws MissingArgumentException if the list of arguments does not contain enough arguments
   */
  public Backward(Turtle turtle, List<Value> args) throws MissingArgumentException {
    super(turtle, args, NUM_ARGS);
    pixels = args.get(0);
  }

  /**
   * Moves the attached turtle backward the given number of pixels
   *
   * @return the number of pixels the turtle will move
   */
  @Override
  public Value execute() {
    getTurtle().move(-pixels.getVal());
    setReturnValue(pixels.getVal());
    return returnValue();
  }

  /**
   * Returns the number of pixels the turtle will move
   *
   * @return the number of pixels the turtle will move
   */
  @Override
  public Value returnValue() {
    return pixels;
  }
}
