package slogo.model.command.turtle;

import java.util.List;
import slogo.model.turtle.Turtle;
import slogo.model.exception.MissingArgumentException;

/**
 * Class that represents a set towards command. Depends on TurtleCommand and Turtle.
 *
 * @author Shaan Gondalia
 */
public class SetTowards extends TurtleCommand {

  private final double x;
  private final double y;
  private Double degrees;
  private static final int NUM_ARGS = 2;
  /**
   * Creates a set towards command
   * @param args the arguments for the command (2 arguments for SetTowards)
   * @param turtle the Turtle that will be moved when the command is executed
   * @throws MissingArgumentException if the list of arguments does not contain enough arguments
   */
  public SetTowards(Turtle turtle, List<Double> args) throws MissingArgumentException {
    super(turtle, args, NUM_ARGS);
    degrees = 0.0;
    x = args.get(0);
    y = args.get(1);
  }

  /**
   * Turns the turtle to face the given point.
   *
   * @return the number of degrees the turtle turns
   */
  @Override
  public Double execute() {
    degrees = getDegreesToTurn();
    getTurtle().rotate(degrees);
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

  // Gets the number of degrees the turtle has to turn
  // See: https://stackoverflow.com/questions/23692077/rotate-object-to-face-point
  private Double getDegreesToTurn(){
    double deltaX = x - getTurtle().getPose().x();
    double deltaY = y - getTurtle().getPose().y();
    double rads = Math.atan((deltaX) / (deltaY));

    return Math.toDegrees(rads) - getTurtle().getPose().bearing();
  }
}
