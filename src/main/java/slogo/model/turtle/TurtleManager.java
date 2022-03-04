package slogo.model.turtle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import slogo.model.command.Command;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;

/**
 * Class that manages turtles Used to determine active turtles and set myID
 *
 * @author Jake Heller and Shaan Gondalia
 */
public class TurtleManager extends Observable<Turtle> {

  // maybe list or map from IDs to turtles

  private final Map<Double, Turtle> myTurtles;
  private final Map<Double, Boolean> followingTurtleMap;
  private Turtle activeTurtle;

  public TurtleManager(int startingTurtles) {
    myTurtles = new HashMap<>();
    followingTurtleMap = new HashMap<>();
    addStartingTurtles(startingTurtles);
  }

  public TurtleManager() {
    this(1);
  }

  private void addStartingTurtles(int num) {
    for (int i = 0; i < num; i++) {
      Turtle t = new Turtle(i);
      t.move(i * 20);
      addTurtle(t);
      myTurtles.put(1.0 * i, t);
      followingTurtleMap.put(1.0 * i, true);
    }
    activeTurtle = myTurtles.get(0.0);
  }

  private void addTurtle(Turtle turtle) {
    notifyListeners("Add Turtle", null, turtle);
  }

  /**
   * Gets the list of turtles that are currently following commands
   *
   * @return list of turtles following commands
   */
  public List<Turtle> getFollowingTurtles() {
    List<Turtle> followingTurtles = new ArrayList<>();
    for (Double key : followingTurtleMap.keySet()) {
      if (followingTurtleMap.get(key)) {
        followingTurtles.add(myTurtles.get(key));
      }
    }
    return followingTurtles;
  }

  /**
   * Returns List of all turtles in turtle manager
   * @return list of all turtles
   */
  public Collection<Turtle> getTurtles() {
    return myTurtles.values().stream().toList();
  }

  /**
   * Returns the total number of turtles that have been created
   *
   * @return the total number of turtles that have been created
   */
  public double numTurtles() {
    return myTurtles.size();
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
   * Sets the turtles with the given ids to follow commands. Creates them if they do not already
   * exist
   *
   * @param ids the ids of the turtles that will follow commands.
   * @return
   */
  public List<Value> swapFollowingIDs(List<Value> ids) {
    List<Value> oldFollowers = new ArrayList<>();
    for (Double key : followingTurtleMap.keySet()) {
      if (followingTurtleMap.get(key)) {
        oldFollowers.add(new Value(key));
      }
    }
    clearFollowers();
    for (Value id : ids) {
      if (!myTurtles.containsKey(id.getVal())) {
        Turtle turtle = new Turtle(id.getVal());
        addTurtle(turtle);
        myTurtles.put(id.getVal(), turtle);
      }
      followingTurtleMap.put(id.getVal(), true);
    }
    activateTurtle(getFollowingTurtles().get(0));
    return oldFollowers;
  }

  // Disables all turtles
  private void clearFollowers() {
    followingTurtleMap.replaceAll((k, v) -> false);
  }

  // Activates a turtle
  private void activateTurtle(Turtle turtle) {
    activeTurtle = turtle;
  }


  /**
   * Executes a queue of commands on the following turtles. Assumes the following turtle does not
   * change during the run
   *
   * @param innerQueue
   */
  public void executeCommandQueue(Deque<Command> innerQueue) throws MissingArgumentException {
    for (Turtle t : getFollowingTurtles()) {
      activateTurtle(t);
      executeCommandsOnTurtle(innerQueue, t);
    }
  }

  /**
   * Executes a body of commands per turtle if they meet the condition
   *
   * @param condition the condition to check for each turtle
   * @param myBody    the body of commands that qualifying turtles will run
   */
  public void executeConditionally(Deque<Command> condition, Deque<Command> myBody)
      throws MissingArgumentException {
    for (Turtle t : myTurtles.values()) {
      for (Command command : condition) {
        command.execute(t);
      }
      if (condition.peekLast().returnValue().getVal() != 0) { // TODO: Add tolerance here
        executeCommandsOnTurtle(myBody, t);
      }
    }
  }

  // Executes Queue of commands on a turtle
  private void executeCommandsOnTurtle(Deque<Command> commands, Turtle turtle)
      throws MissingArgumentException {
    for (Command command : commands) {
      //System.out.println(command);
      command.execute(turtle);
      //System.out.println(t.getPose());
    }
  }


}
