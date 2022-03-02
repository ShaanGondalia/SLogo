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

    public Trail(Line line, Color color) {
        this.line = line;
        this.color = color;
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

}
