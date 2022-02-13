package slogo;

/**
 * This is the external API for the model. It provides information about the status of a turtle in
 * the model, such as its position and bearing. It also has some of the internal API methods that
 * the commands need to use to update the status of the turtle in the back end.
 *
 * The methods that are external/internal are stated below.
 */
public interface TurtleModel{

  /**
   * External - returns a Pose object which encapsulates the turtle's position and orientation (bearing)
   * @return a pose corresponding to the turtle's current state.
   */
  public Pose getPose();

  /**
   * External - returns whether the turtle is visible. Needed so that the front end can know whether the turtle should be drawn or not.
   * @return whether the turtle is visible
   */
  public boolean isVisible();


  /**
   * External - returns whether the status of the turtle's pen. Needed so that the front end can know whether the turtle leaves a trail.
   * @return whether the turtle leaves a trail
   */
  public boolean isPenDown();

  /**
   * Internal - required for the internal model API (to allow a command to update the state of the turtle.
   * @param newPose the new Pose of the turtle
   */
  public void setPose(Pose newPose);

  /**
   * Internal - allows commands to set the visibility of the turtle in the back end
   * @param visible whether the turtle should be visible or not
   */
  public void setVisible(boolean visible);


  /**
   * Internal - allows commands to set the pen status of the turtle in the back end
   * @param penDown whether the turtle's pen is down or not
   */
  public boolean setPen(boolean penDown);

  /**
   * Internal - resets the turtle's position to its starting position.
   */
  public void reset();


}