package slogo.view.turtle;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * Outlines a Trail Graphical Component
 * Trails are a 2D Line segment with a particular given color
 *
 * @author Zack Schrage
 */
public class Trail {

    private Line line;
    private Color color;
    private double thickness;

    /**
     * Outlines a Trail Graphical Component
     * Trails are a 2D Line segment with a particular given color
     * @param line JavaFX line segment representing a trail
     * @param color JavaFX color of the trail
     * @param thickness numerical width in pixels of the trail
     */
    public Trail(Line line, Color color, double thickness) {
        this.line = line;
        this.color = color;
        this.thickness = thickness;
    }

    /**
     * Getter method for a trails line segment
     * @return trail line segment
     */
    public Line getLine() {
        return line;
    }

    /**
     * Getter method for a trails color
     * @return trail color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Getter method for a trails thickness
     * @return trail thickness
     */
    public double getThickness() {
        return thickness;
    }

}
