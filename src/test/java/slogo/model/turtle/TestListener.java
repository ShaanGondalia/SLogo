package slogo.model.turtle;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import slogo.model.turtle.Pose;
import slogo.model.turtle.TurtleStatus;

public class TestListener implements PropertyChangeListener {

  PropertyChangeEvent lastChange = null;

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    lastChange = evt;
    Pose pOld = ((TurtleStatus) evt.getOldValue()).pose();
    Pose pNew = ((TurtleStatus) evt.getNewValue()).pose();
    System.out.println(evt.getNewValue());
  }

  public PropertyChangeEvent getChange () {
    return lastChange;
  }

}
