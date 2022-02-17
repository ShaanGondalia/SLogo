package slogo.Model;

public class Turtle extends Observable<TurtleStatus> {

  private double myX;
  private double myY;
  private double myBearing;
  private boolean myHasPen;
  private boolean myVisibility;
  private TurtleStatus myLastState;

  public Turtle() {
    myX = 0;
    myY = 0;
    myBearing = 0;
    myHasPen = false;
    myVisibility = true;
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
