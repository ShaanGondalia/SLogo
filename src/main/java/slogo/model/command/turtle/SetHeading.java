package slogo.model.command.turtle;

import java.util.List;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Pose;
import slogo.model.turtle.Turtle;

/**
 * Class that creates set heading command, which rotates a turtle to a set bearing
 *
 * @author Jake Heller
 */
public class SetHeading extends TurtleCommand {

  private static final int NUM_ARGS = 1;

  private final Value myDegreesTurned;
  private final Value myBearing;

  /**
   * @param args size 1 list containing bearing to set turtle to
   * @throws MissingArgumentException if args contains less than 1 item
   */
  public SetHeading(List<Value> args) throws MissingArgumentException {
    super(args, NUM_ARGS);
    myBearing = args.get(0);
    myDegreesTurned = new Value();
  }

  /**
   * @param turtle turtle to perform command on
   * @return the number of degrees turned to reach target bearing
   */
  @Override
  public Value execute(Turtle turtle) {
    Pose currPose = turtle.getPose();
    double currBearing = currPose.bearing();
    myDegreesTurned.setVal(myBearing.getVal() - currBearing);
    turtle.rotate(myDegreesTurned.getVal());

    setReturnValue(myDegreesTurned.getVal());
    return returnValue();
  }
}
