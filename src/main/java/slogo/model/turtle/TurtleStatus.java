package slogo.model.turtle;

/**
 * Record for every part of the turtle's state which should be reported to
 * listeners. This object is what is passed in the Turtle's change event messages
 *
 * @author Jake Heller
 */
public record TurtleStatus(double id, Pose pose, PenState penState, boolean isActive, boolean visibility) {
}