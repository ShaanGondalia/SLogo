package slogo.view.turtle;

import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * This class outlines the JavaFX subcomponents of a turtle tooltip
 * A turtle tooltip displays the information about a turtle when a turtle is hovered over
 *
 * @author Zack Schrage
 */
public class TurtleTooltip {

    private Rectangle background;
    private Text text;

    /**
     * A turtle tooltip displays the information about a turtle when a turtle is hovered over
     * A turtle tooltip contains a background JavaFX rectangle and a JavaFX text segment containing a turtle status description
     * @param x coordinate
     * @param y coordinate
     */
    public TurtleTooltip(int x, int y) {
        background = new Rectangle(x, y, 80, 60);
        text = new Text();
        text.setX(x);
        text.setY(y);
    }

    /**
     * Updates the position of the tooltip elements
     * @param x position of the tooltip
     * @param y position of the tooltip
     */
    public void updatePosition(int x, int y) {
        background.setX(x);
        background.setY(y);
        text.setX(x + 5);
        text.setY(y + 15);
    }

    /**
     * Sets the text description for a turtle
     * @param s description
     */
    public void setText(String s) {
        text.setText(s);
    }

    /**
     * Gets background rectangle for the tooltip
     * @return background rectangle
     */
    public Rectangle getBackground() {
        return background;
    }

    /**
     * Gets text area of the tooltip
     * @return text area
     */
    public Text getText() {
        return text;
    }
}
