package slogo.model.turtle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import slogo.model.command.Value;

/**
 * Class that manages turtles
 * Used to determine active turtles and set myID
 *
 * @author Jake Heller and Shaan Gondalia
 */
public class TurtleManager {

  // maybe list or map from IDs to turtles

  private final Map<Double, Turtle> myTurtles;
  private final Map<Double, Boolean> followingTurtleMap;
  private Turtle activeTurtle;

  public TurtleManager() {
    myTurtles = new HashMap<>();
    followingTurtleMap = new HashMap<>();
    activeTurtle = new Turtle();
    myTurtles.put((double) 0, activeTurtle);
    followingTurtleMap.put((double) 0, true);
  }

  /**
   * Gets the collection of turtles that are currently following commands
   *
   * @return collection of turtles following commands
   */
  public Collection<Turtle> getFollowingTurtles() {
    Collection<Turtle> followingTurtles = new ArrayList<>();
    for (Double key : followingTurtleMap.keySet()) {
      if (followingTurtleMap.get(key)) {
        followingTurtles.add(myTurtles.get(key));
      }
    }
    return followingTurtles;
  }

  /**
   *
   * @param turtle the turtle that is active
   */
  public void setActiveTurtle(Turtle turtle) {
    activeTurtle = turtle;
  }

  /**
   * Gets the ID of the active Turtle
   */
  public double getActiveTurtleID() {
    for (Double key : myTurtles.keySet()) {
      if (myTurtles.get(key) == activeTurtle) {
        return key;
      }
    }
    return -1;
  }

  /**
   * Sets the turtles with the given ids to follow commands. Creates them if they do not already exist
   *
   * @param ids the ids of the turtles that will follow commands.
   */
  public void setFollowingIDs(List<Value> ids) {
    for (Value id : ids) {
      if (!myTurtles.containsKey(id.getVal())) {
        Turtle turtle = new Turtle();
        myTurtles.put(id.getVal(), turtle);
      }
      followingTurtleMap.put(id.getVal(), true);
    }
  }
}
