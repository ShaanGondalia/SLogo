package slogo.model.turtle;

import slogo.model.color.ColorRecord;

public record PenState(boolean penDown, ColorRecord color, double thickness) {

}
