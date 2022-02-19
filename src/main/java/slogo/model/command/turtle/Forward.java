package slogo.model.command.turtle;

import java.util.List;
import slogo.model.Turtle;
import slogo.model.exception.MissingArgumentException;

/**
 * Class that represents a forward (fd) command. Depends on TurtleCommand and Turtle.
 *
 * @author Shaan Gondalia
 */
public class Forward extends TurtleCommand {

  double pixels;
  private static final int NUM_ARGS = 1;
  /**
   * Creates a forward command
   * @param args the arguments for the command (single argument for FD)
   * @param turtle the Turtle that will be moved when the command is executed
   */
  public Forward(Turtle turtle, List<Double> args) throws MissingArgumentException {
    super(turtle, args, NUM_ARGS);
    pixels = args.get(0);
  }

  /**
   * Moves the attached turtle forward given the arguments
   * @return the number of pixels the turtle will move
   */
  @Override
  public Double execute() {
    getTurtle().move(pixels);
    return pixels;
  }

  /**
   * Returns the number of pixels the turtle will move
   * @return the number of pixels the turtle will move
   */
  @Override
  public Double returnValue() {
    return pixels;
  }
}
