package slogo.view.turtle;

import java.beans.PropertyChangeEvent;

/**
 * Interface for a functional so that property change event's properties can be mapped to function that acts upon a change
 *
 * @author Jake Heller
 * @author Zack Schrage
 */
public interface TurtleConsumer {
    void accept(TurtleView turtleView, PropertyChangeEvent evt);
}
