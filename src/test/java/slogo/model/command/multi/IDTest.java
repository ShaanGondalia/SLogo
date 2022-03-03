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
 * Tests for ID Command
 *
 * @author Shaan Gondalia
 */
public class IDTest {

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
    Command id = new ID(args, myTurtleManager);
    assertEquals(0, id.execute(myTurtle).getVal());
  }

  @Test
  void testTooManyArgs() throws MissingArgumentException {
    args.add(new Value(ARG_1));
    Command id = new ID(args, myTurtleManager);
    assertEquals(0, id.execute(myTurtle).getVal());
  }

  @Test
  void testSetOneActiveTurtle() throws MissingArgumentException {
    List<Value> newIds = new ArrayList<>();
    newIds.add(new Value(ARG_1));
    List<Value> oldIds = myTurtleManager.swapFollowingIDs(newIds);
    Command id = new ID(args, myTurtleManager);
    assertEquals(ARG_1, id.execute(myTurtle).getVal());
    myTurtleManager.swapFollowingIDs(oldIds);
    assertEquals(0, id.execute(myTurtle).getVal());
  }

}
