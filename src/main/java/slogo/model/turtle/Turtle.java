package slogo.model.turtle;

import slogo.model.color.ColorRecord;

/**
 * Class that encapsulates the turtle in the back-end. Contains internal API methods for updating
 * and getting the status of the turtle. Depends on TurtleStatus.
 *
 * @author Jake Heller and Shaan Gondalia
 */
public class Turtle extends Observable<TurtleStatus> {

  private static final double ROTATION_ADJUST = 90;
  private static final double FULL_ROTATION = 360;

  public static final String POSE_CHANGED = "Pose";
  public static final String VISIBILITY_CHANGED = "Visibility";
  public static final String PEN_CHANGED = "Pen";
  public static final String TRAILS_CLEARED = "Clear";

  private double myX;
  private double myY;
  private final double myID;
  private double myThickness;
  private double myBearing;
  private boolean myPenDown;
  private boolean myVisibility;
  private boolean myActive;
  private TurtleStatus myLastState;
  private int myPenR;
  private int myPenG;
  private int myPenB;

  /**
   * Create a new turtle that starts at the center of the screen. Initializes instance variables.
   */
  public Turtle() {
    setDefaultParameters();
    myID = 0;
    myLastState = makeStatus();
  }

  /**
   * Create a new turtle that starts at the center of the screen with the given ID.
   *
   * @param id the id of the turtle
   */
  public Turtle(double id) {
    setDefaultParameters();
    myID = id;
    myLastState = makeStatus();
  }

  private void setDefaultParameters() {
    myX = 0;
    myY = 0;
    myBearing = 0;
    myPenDown = false;
    myVisibility = true;
    myPenR = 0;
    myPenG = 0;
    myPenB = 0;
    myActive = false;
    myThickness = 0;
  }

  /**
   * Moves the turtle a given distance in pixels (negative for backward), based on current bearing.
   *
   * @param distance the distance in pixels to move
   */
  public void move(double distance) {
    double theta = -1 * (myBearing - ROTATION_ADJUST);
    double radians = Math.toRadians(theta);
    myX += distance * Math.cos(radians);
    myY += distance * Math.sin(radians);
    change(POSE_CHANGED);
  }

  /**
   * Rotates the turtle Clockwise by a given number of degrees (negative for CCW).
   *
   * @param degrees the number of degrees to rotate
   */
  public void rotate(double degrees) {
    // needs to make sure difference from last bearing has same sign as input
    myBearing = myBearing + degrees;
    change(POSE_CHANGED);
    // "silently" change bearing and myLastState
    myBearing = myBearing % FULL_ROTATION;
    if (myBearing < 0) {
      myBearing += FULL_ROTATION;
    }
    myLastState = makeStatus();
  }

  /**
   * Returns the turtle to its starting position
   */
  public void goHome() {
    myX = 0;
    myY = 0;
    myBearing = 0;
    change(POSE_CHANGED);
  }

  /**
   * Clears the turtle's trails
   */
  public void clearTrails() {
    String property = TRAILS_CLEARED;
    change(property);
  }

  /**
   * Gets the id of the turtle
   *
   * @return the id of the turtle
   */
  public double getMyID() {
    return myID;
  }

  /**
   * Sets the status of the turtle's pen.
   *
   * @param penState Sets pen color, thickness, and if down
   */
  public void setPen(PenState penState) {
    myPenDown = penState.penDown();
    myThickness = penState.thickness();
    ColorRecord color = penState.color();
    myPenR = color.r();
    myPenG = color.g();
    myPenB = color.b();

    change(PEN_CHANGED);
  }

  public void setPenColor(ColorRecord color) {
    myPenR = color.r();
    myPenG = color.g();
    myPenB = color.b();

    String property = PEN_CHANGED;
    change(property);
  }

  /**
   * sets turtle to inactive
   */
  public void makeInactive() {
    myActive = false;

    change(VISIBILITY_CHANGED);
  }

  /**
   * sets turtle to active
   */
  public void makeActive() {
    myActive = true;

    change(VISIBILITY_CHANGED);
  }

  /**
   * Sets the visibility of the turtle.
   *
   * @param visibility If true, the turtle is visible. If false, the turtle is invisible.
   */
  public void setVisibility(boolean visibility) {
    myVisibility = visibility;
    String property = VISIBILITY_CHANGED;
    change(property);
  }

  public Pose getPose() {
    return currentPose();
  }

  public void setPose(Pose pose) {
    myX = pose.x();
    myY = pose.y();
    myBearing = pose.bearing();
    String property = POSE_CHANGED;
    change(property);
  }

  public TurtleStatus getStatus() {
    return makeStatus();
  }

  private TurtleStatus makeStatus() {
    Pose pose = currentPose();
    TurtleStatus status = new TurtleStatus(myID, pose, makePenState(), myActive, myVisibility);
    return status;
  }

  private PenState makePenState() {
    ColorRecord color = new ColorRecord(myPenR, myPenG, myPenB);
    PenState penState = new PenState(myPenDown, color, myThickness);
    return penState;
  }

  private Pose currentPose() {
    return new Pose(myX, myY, myBearing);
  }

  private void change(String property) {
    TurtleStatus newState = makeStatus();
    notifyListeners(property, myLastState, newState);
    myLastState = newState;
  }

}
