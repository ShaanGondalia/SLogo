package slogo.Model;

/**
 * Class that encapsulates the turtle in the back-end. Contains internal API methods for updating and getting the status of the turtle. Depends on TurtleStatus.
 *
 * @author Jake Heller and Shaan Gondalia
 */
public class Turtle extends Observable<TurtleStatus> {

  private double myX;
  private double myY;
  private double myBearing;
  private boolean myHasPen;
  private boolean myVisibility;
  private TurtleStatus myLastState;

  /**
   * Create a new turtle that starts at the center of the screen. Initializes instance variables.
   */
  public Turtle() {
    myX = 0;
    myY = 0;
    myBearing = 0;
    myHasPen = false;
    myVisibility = true;
    myLastState = makeStatus();
  }

  /**
   * Moves the turtle a given distance in pixels (negative for backward), based on current bearing.
   *
   * @param distance the distance in pixels to move
   */
  public void move(double distance){
    //TODO: Math to get the updated x and y locations
    myX += distance;
  }

  /**
   * Rotates the turtle Clockwise by a given number of degrees (negative for CCW).
   *
   * @param degrees the number of degrees to rotate
   */
  public void rotate(double degrees){
    myBearing = (myBearing + degrees) % 360;
  }

  public void setPose(Pose pose) {
    myX = pose.x();
    myY = pose.y();
    myBearing = pose.bearing();
    String property = "Pose";
    change(property);
  }

  public void setPen(boolean hasPen) {
    myHasPen = hasPen;
    String property = "Pen";
    change(property);
  }

  public void setVisibility(boolean visibility) {
    myVisibility = visibility;
    String property = "Visibility";
    change(property);
  }

  public Pose getPose() {
    return currentPose();
  }

  private TurtleStatus makeStatus() {
    Pose pose = currentPose();
    TurtleStatus status = new TurtleStatus(pose, myHasPen, myVisibility);
    return status;
  }

  private Pose currentPose() {
    return new Pose(myX, myY, myBearing);
  }

  private void change(String property) {
    TurtleStatus newState = makeStatus();
    notifyListeners(property, myLastState, newState);
    myLastState = newState;
  }

  /*
  These methods are unnecessary if you always package pose info
  into the pose

  public void setX(double x) {
    myX = x;
    String property = "Pose";
    change(property);
  }

  public void setY(double y) {
    myY = y;
    String property = "Pose";
    change(property);
  }

  public void setAngle(double angle) {
    myBearing = angle;
    String property = "Pose";
    change(property);
  }
   */
}
