package slogo.model.command.turtle;

import java.util.List;
import slogo.Main;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Class that represents a set towards command. Depends on TurtleCommand and Turtle.
 *
 * @author Shaan Gondalia
 */
public class SetTowards extends TurtleCommand {

  private final Value x;
  private final Value y;
  private double degrees;
  private Value degreesAbs;
  private static final int NUM_ARGS = 2;

  /**
   * Creates a set towards command
   *
   * @param args   the arguments for the command (2 arguments for SetTowards)
   * @param turtle the Turtle that will be moved when the command is executed
   * @throws MissingArgumentException if the list of arguments does not contain enough arguments
   */
  public SetTowards(Turtle turtle, List<Value> args) throws MissingArgumentException {
    super(turtle, args, NUM_ARGS);
    degrees = 0.0;
    x = args.get(0);
    y = args.get(1);
    degreesAbs = new Value();
  }

  /**
   * Turns the turtle to face the given point.
   *
   * @return the number of degrees the turtle rotates
   */
  @Override
  public Value execute() {
    degrees = getDegreesToTurn();
    degreesAbs.setVal(Math.abs(degrees));
    getTurtle().rotate(degrees);

    setReturnValue(degreesAbs.getVal());
    return returnValue();
  }

  // Gets the number of degrees the turtle has to turn
  // See: https://stackoverflow.com/questions/23692077/rotate-object-to-face-point
  private double getDegreesToTurn() {
    double deltaX = x.getVal() - getTurtle().getPose().x();
    double deltaY = y.getVal() - getTurtle().getPose().y();

    double newBearing;

    // division by 0
    if (Math.abs(deltaY) < Main.TOLERANCE) {
      newBearing = (deltaX > 0 ? 90 : 270); // either 90 or 270
    } else {
      double radsFromVertical = Math.atan((deltaX) / (deltaY));
      double degreesFromVertical = Math.toDegrees(radsFromVertical);
      newBearing = getNewBearing(degreesFromVertical, deltaX, deltaY);
    }

    double degreesToTurn = newBearing - getTurtle().getPose().bearing();

    return degreesToTurn;
  }

  // Gets the new bearing of the turtle given the degrees from vertical and delta x and y
  private double getNewBearing(double degreesFromVertical, double deltaX, double deltaY) {
    if (deltaY >= 0) { // quadrant 1 and 2
      return degreesFromVertical;
    } else { // quadrant 3 and 4
      return 180 + degreesFromVertical;
    }
  }
}
