package slogo.model.turtle;

import slogo.model.color.ColorRecord;

/**
 * Record class which stores if pen is up, the color, and thickness of a pen
 * @author Jake Heller
 */
public record PenState(boolean penDown, ColorRecord color, double thickness) {

}
