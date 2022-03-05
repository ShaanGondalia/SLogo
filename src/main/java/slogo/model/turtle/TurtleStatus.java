package slogo.model.turtle;

/**
 * @author Jake Heller
 */
public record TurtleStatus(Pose pose, PenState penState, boolean isActive, boolean visibility) {

}