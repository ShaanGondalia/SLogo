package slogo.model.command.turtle;

import java.util.ArrayList;
import java.util.List;
import slogo.model.command.Command;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Pose;
import slogo.model.turtle.Turtle;

/**
 * Class that represents GOTO/SETXY command, which moves turtle to given position
 *
 * @author Jake Heller
 */
public class SetPosition extends TurtleCommand {

  private static final int NUM_ARGS = 2;

  private final Value myDistance;
  private final Value myX;
  private final Value myY;

  public SetPosition(List<Value> args) throws MissingArgumentException {
    super(args, NUM_ARGS);
    myX = args.get(0);
    myY = args.get(1);
    myDistance = new Value();
  }

  /**
   * @return distance travelled
   * @throws MissingArgumentException if less than 2 arguments given
   * @param turtle
   */
  @Override
  public Value execute(Turtle turtle) throws MissingArgumentException {
    Pose initialPose = turtle.getPose();
    double initialBearing = initialPose.bearing();

    setTowards(myX.getVal(), myY.getVal()).execute(turtle);

    Pose nextPose = new Pose(myX.getVal(), myY.getVal(), turtle.getPose().bearing());
    turtle.setPose(nextPose);

    setBearing(initialBearing).execute(turtle);

    myDistance.setVal(distance(initialPose.x(), initialPose.y(), myX.getVal(), myY.getVal()));
    setReturnValue(myDistance.getVal());
    return returnValue();
  }

  private Command setTowards(double x, double y) throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(x));
    args.add(new Value(y));

    return new SetTowards(args);
  }

  private Command setBearing(double bearing) throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(bearing));

    return new SetHeading(args);
  }

  private double distance(double x1, double y1, double x2, double y2) {
    return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
  }

}
