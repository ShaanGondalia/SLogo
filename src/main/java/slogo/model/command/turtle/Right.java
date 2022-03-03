package slogo.model.command.turtle;

import java.util.List;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Class that represents a right (rt) command. Depends on TurtleCommand and Turtle.
 *
 * @author Shaan Gondalia
 */
public class Right extends TurtleCommand {

  private static final int NUM_ARGS = 1;
  private final Value degrees;

  /**
   * Creates a right command. Takes a single argument
   *
   * @param args the arguments for the command (single argument for Right)
   * @throws MissingArgumentException if the list of arguments does not contain enough arguments
   */
  public Right(List<Value> args) throws MissingArgumentException {
    super(args, NUM_ARGS);
    degrees = args.get(0);
  }

  /**
   * Rotates the turtle Right (CW) by the given number of degrees
   *
   * @param turtle the Turtle that will be rotated
   * @return the number of degrees the turtle will rotate
   */
  @Override
  public Value execute(Turtle turtle) {
    turtle.rotate(degrees.getVal());
    setReturnValue(degrees.getVal());
    return returnValue();
  }
}
