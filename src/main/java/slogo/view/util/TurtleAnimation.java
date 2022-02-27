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

    public Animation getAnimation() {
        return animation;
    }

    public boolean getVisibility() {
        return visibility;
    }

}
