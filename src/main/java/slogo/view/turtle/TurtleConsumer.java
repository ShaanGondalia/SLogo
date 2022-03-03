package slogo.view.turtle;

import java.beans.PropertyChangeEvent;

public interface TurtleConsumer {
    void accept(TurtleView turtleView, PropertyChangeEvent evt);
}
