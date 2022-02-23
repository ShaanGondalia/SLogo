package slogo.model.command.turtle;

import java.util.ArrayList;
import java.util.List;
import slogo.model.command.Command;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Pose;
import slogo.model.turtle.Turtle;

/**
 * Class that represents GOTO/SETXY command,
 * which moves turtle to given position
 *
 * @author Jake Heller
 */
public class SetPosition extends TurtleCommand{

  private static final int NUM_ARGS = 2;

  private Value myDistance;
  private double myX;
  private double myY;

  public SetPosition(Turtle turtle, List<Value> args) throws MissingArgumentException {
    super(turtle, args, NUM_ARGS);
    myX = args.get(0).getVal();
    myY = args.get(1).getVal();
    myDistance = new Value();
  }

  /**
   *
   * @return distance travelled
   * @throws MissingArgumentException if less than 2 arguments given
   */
  @Override
  public Value execute() throws MissingArgumentException {
    Pose initialPose = getTurtle().getPose();
    double initialBearing = initialPose.bearing();

    setTowards(myX, myY).execute();

    Pose nextPose = new Pose(myX, myY, getTurtle().getPose().bearing());
    getTurtle().setPose(nextPose);

    setBearing(initialBearing).execute();

    myDistance.setVal(distance(initialPose.x(), initialPose.y(), myX, myY));
    return returnValue();
  }

  /**
   *
   * @return distance travelled
   */
  @Override
  public Value returnValue() {
    return myDistance;
  }

  public Command setTowards(double x, double y) throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(x));
    args.add(new Value(y));

    return new SetTowards(getTurtle(), args);
  }

  public Command setBearing(double bearing) throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(bearing));

    return new SetHeading(getTurtle(), args);
  }

  public double distance(double x1, double y1, double x2, double y2) {
    double distance = Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));
    return distance;
  }

}
