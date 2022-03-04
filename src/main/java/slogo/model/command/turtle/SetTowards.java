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

  private static final int NUM_ARGS = 2;
  private static final double ROTATION_X_POS = 90;
  private static final double ROTATION_X_NEG = 270;
  private static final double QUADRANT_III_IV_ADJUST = 180;
  private final Value x;
  private final Value y;
  private double degrees;
  private final Value degreesAbs;

  /**
   * Creates a set towards command
   *
   * @param args the arguments for the command (2 arguments for SetTowards)
   * @throws MissingArgumentException if the list of arguments does not contain enough arguments
   */
  public SetTowards(List<Value> args) throws MissingArgumentException {
    super(args, NUM_ARGS);
    degrees = 0.0;
    x = args.get(0);
    y = args.get(1);
    degreesAbs = new Value();
  }

  /**
   * Turns the turtle to face the given point.
   *
   * @param turtle the Turtle that will be moved when the command is executed
   * @return the number of degrees the turtle rotates
   */
  @Override
  public Value execute(Turtle turtle) {
    degrees = getDegreesToTurn(turtle);
    degreesAbs.setVal(Math.abs(degrees));
    turtle.rotate(degrees);

    setReturnValue(degreesAbs.getVal());
    return returnValue();
  }

  // Gets the number of degrees the turtle has to turn
  // See: https://stackoverflow.com/questions/23692077/rotate-object-to-face-point
  private double getDegreesToTurn(Turtle turtle) {
    double deltaX = x.getVal() - turtle.getPose().x();
    double deltaY = y.getVal() - turtle.getPose().y();

    double newBearing;

    // division by 0
    if (Math.abs(deltaY) < Main.TOLERANCE) {
      newBearing = (deltaX > 0 ? ROTATION_X_POS : ROTATION_X_NEG); // either 90 or 270
    } else {
      double radsFromVertical = Math.atan((deltaX) / (deltaY));
      double degreesFromVertical = Math.toDegrees(radsFromVertical);
      newBearing = getNewBearing(degreesFromVertical, deltaX, deltaY);
    }

    return newBearing - turtle.getPose().bearing();
  }

  // Gets the new bearing of the turtle given the degrees from vertical and delta x and y
  private double getNewBearing(double degreesFromVertical, double deltaX, double deltaY) {
    if (deltaY >= 0) { // quadrant 1 and 2
      return degreesFromVertical;
    } else { // quadrant 3 and 4
      return QUADRANT_III_IV_ADJUST + degreesFromVertical;
    }
  }
}
