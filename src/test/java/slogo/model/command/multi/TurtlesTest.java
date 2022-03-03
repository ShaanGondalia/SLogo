package slogo.model.command.multi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.Main;
import slogo.model.command.Command;
import slogo.model.command.Value;
import slogo.model.command.control.If;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Pose;
import slogo.model.turtle.Turtle;
import slogo.model.turtle.TurtleManager;

/**
 * Tests for Turtles Command
 *
 * @author Shaan Gondalia
 */
public class TurtlesTest {

  private static final double ARG_1 = 50;

  private TurtleManager myTurtleManager;
  private Turtle myTurtle;
  private List<Value> args;


  @BeforeEach
  void setUp() {
    myTurtleManager = new TurtleManager();
    args = new ArrayList<>();
    myTurtle = new Turtle();
  }

  @Test
  void testNoArgs() throws MissingArgumentException {
    Command id = new Turtles(args, myTurtleManager);
    assertEquals(1, id.execute(myTurtle).getVal());
  }

  @Test
  void testTooManyArgs() throws MissingArgumentException {
    args.add(new Value(ARG_1));
    Command id = new Turtles(args, myTurtleManager);
    assertEquals(1, id.execute(myTurtle).getVal());
  }

  @Test
  void testSetOneActiveTurtle() throws MissingArgumentException {
    List<Value> newIds = new ArrayList<>();
    newIds.add(new Value(ARG_1));
    List<Value> oldIds = myTurtleManager.swapFollowingIDs(newIds);
    Command id = new Turtles(args, myTurtleManager);
    assertEquals(2, id.execute(myTurtle).getVal());
    myTurtleManager.swapFollowingIDs(oldIds);
    assertEquals(2, id.execute(myTurtle).getVal());
  }

  @Test
  void testSetLotsOfActiveTurtle() throws MissingArgumentException {
    List<Value> newIds = new ArrayList<>();
    newIds.add(new Value(ARG_1));
    newIds.add(new Value(2));
    newIds.add(new Value(3));
    newIds.add(new Value(4));
    newIds.add(new Value(6));
    List<Value> oldIds = myTurtleManager.swapFollowingIDs(newIds);
    Command id = new Turtles(args, myTurtleManager);
    assertEquals(6, id.execute(myTurtle).getVal());
    myTurtleManager.swapFollowingIDs(oldIds);
    assertEquals(6, id.execute(myTurtle).getVal());
  }

}
