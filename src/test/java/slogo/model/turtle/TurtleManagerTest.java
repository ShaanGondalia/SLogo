package slogo.model.turtle;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.command.Command;
import slogo.model.command.Value;
import slogo.model.command.turtle.Forward;
import slogo.model.exception.MissingArgumentException;

/**
 * Tests for TurtleManger class
 *
 * @author Shaan Gondalia
 */
public class TurtleManagerTest {

  private static final double FORWARD = 50;
  private static final double LEFT = 90;
  private static final double OVER_ROTATION = 450;
  private static final double EPSILON = 0.0001d;

  private TurtleManager myTurtleManager;

  @BeforeEach
  void setUp() {
    myTurtleManager = new TurtleManager();
  }

  @Test
  void testFollowingTurtles(){
    List<Turtle> turtles = myTurtleManager.getFollowingTurtles();
    assertEquals(1, turtles.size());

    List<Value> followingTurtleIds = new ArrayList<>();
    followingTurtleIds.add(new Value(1));
    followingTurtleIds.add(new Value(3));
    followingTurtleIds.add(new Value(2));

    myTurtleManager.swapFollowingIDs(followingTurtleIds);
    turtles = myTurtleManager.getFollowingTurtles();
    assertEquals(3, turtles.size());
  }

  @Test
  void testFollowingTurtleTwice(){
    List<Turtle> turtles = myTurtleManager.getFollowingTurtles();
    assertEquals(1, turtles.size());

    List<Value> followingTurtleIds = new ArrayList<>();
    followingTurtleIds.add(new Value(0));
    followingTurtleIds.add(new Value(1));

    myTurtleManager.swapFollowingIDs(followingTurtleIds);
    turtles = myTurtleManager.getFollowingTurtles();
    assertEquals(2, turtles.size());
  }

  @Test
  void executeMultipleTurtles() throws MissingArgumentException {
    List<Turtle> turtles = myTurtleManager.getFollowingTurtles();
    assertEquals(1, turtles.size());

    List<Value> followingTurtleIds = new ArrayList<>();
    followingTurtleIds.add(new Value(0));
    followingTurtleIds.add(new Value(1));

    Deque<Command> commandDeque = new LinkedList<>();
    List<Value> args = new ArrayList<>();
    Value variable = new Value(FORWARD);
    args.add(variable);
    commandDeque.add(new Forward(args));

    myTurtleManager.swapFollowingIDs(followingTurtleIds);
    turtles = myTurtleManager.getFollowingTurtles();
    assertEquals(2, turtles.size());
    myTurtleManager.executeCommandQueue(commandDeque);

    assertEquals(50, myTurtleManager.getFollowingTurtles().get(0).getPose().y());
    assertEquals(50, myTurtleManager.getFollowingTurtles().get(1).getPose().y());

  }




}
