package slogo.model.command.turtle;

import java.util.List;
import slogo.model.Turtle;

/**
 * Class that represents a left (lt) command. Depends on TurtleCommand and Turtle.
 *
 * @author Shaan Gondalia
 */
public class Left extends TurtleCommand {

  double degrees;
  /**
   * Creates a left command. Takes a single argument
   * @param args the arguments for the command (single argument for Left)
   * @param turtle the Turtle that will be rotated
   */
  public Left(List<Double> args, Turtle turtle){
    super(turtle);
    degrees = args.get(0);
  }

  /**
   * Rotates the turtle Left (CCW) by the given number of degrees
   *
   * @return the number of degrees the turtle will rotate
   */
  @Override
  public Double execute() {
    getTurtle().rotate(-degrees);
    return degrees;
  }

  /**
   * Returns the number of degrees the turtle will rotate
   * @return the number of degrees the turtle will rotate
   */
  @Override
  public Double returnValue() {
    return degrees;
  }
}
