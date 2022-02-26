package slogo.view.util;

import javafx.animation.Animation;

/**
 * Turtle Animation Bundle that holds both a JavaFX animation and two properties: penDown and visibility
 * When penDown is true, the turtle will animate and leave a trail
 * When visibility is true, the turtle will be viewed as a turtle image
 *
 * @author Zack Schrage
 */
public class TurtleAnimation {

    private Animation animation;
    private boolean penDown;
    private boolean visibility;

    public TurtleAnimation(Animation animation, boolean penDown, boolean visibility) {
        this.animation = animation;
        this.penDown = penDown;
        this.visibility = visibility;
    }

    public Animation getAnimation() {
        return animation;
    }

    public boolean getPenDown() {
        return penDown;
    }

    public boolean getVisibility() {
        return visibility;
    }

}
