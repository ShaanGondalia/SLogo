package slogo;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import slogo.model.Pose;
import slogo.model.TurtleStatus;

public class TestListener implements PropertyChangeListener {

  String lastChange;

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    lastChange = evt.getPropertyName();
    Pose pOld = ((TurtleStatus) evt.getOldValue()).pose();
    Pose pNew = ((TurtleStatus) evt.getNewValue()).pose();
    System.out.println(evt.getNewValue());
    System.out.println(pOld);
    System.out.println(pNew);
  }

  public String getChange () {
    return lastChange;
  }

}
