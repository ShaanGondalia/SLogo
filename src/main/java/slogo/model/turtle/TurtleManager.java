package slogo.model.turtle;

import java.util.List;
import java.util.Map;
import slogo.model.command.Value;

/**
 * Class that manages turtles
 * Used to determine active turtles and set myID
 * @author Jake Heller
 */
public class TurtleManager {

  // maybe list or map from IDs to turtles
  // private List<Turtles> myTurtles;

  // possibly need to reverse myTurtles map?
  private Map<Turtle, Integer> myTurtles;
  private Map<Integer, Boolean> active;
  private final Value myID;

  public TurtleManager(Value id) {
    myID = id;
  }

  /**
   *
   * @return list of active turtles
   */
  public List<Turtle> getActiveTurtles() {
    return null;
  }

  public void setActive(Turtle t) throws Exception {
    Integer id = myTurtles.get(t);
    if (id == null) {
      throw new Exception("Message");
    }
    else {
      myID.setVal(id);
    }
  }
}
