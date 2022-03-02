package slogo.view.util;

import javafx.animation.Animation;

/**
 * Turtle Animation Bundle that holds both a JavaFX animation and the visibility property
 * When visibility is true, the turtle will be viewed as a turtle image
 * When visibility is false, the turtle will be set to an invisible image
 *
 * @author Zack Schrage
 */
public class TurtleAnimation {

    private Animation animation;
    private boolean visibility;

    public TurtleAnimation(Animation animation, boolean visibility) {
        this.animation = animation;
        this.visibility = visibility;
    }

    /**
     * Getter method for a Turtle Animations JavaFX Animation object
     * @return animation object
     */
    public Animation getAnimation() {
        return animation;
    }

    /**
     * Getter method for an animations visibility
     * If a turtle animation is meant to be invisible it will hide the turtle while animating
     * @return turtle's visibility
     */
    public boolean getVisibility() {
        return visibility;
    }

}
