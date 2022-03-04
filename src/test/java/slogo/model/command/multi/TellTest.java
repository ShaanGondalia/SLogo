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
 * Tests for Tell Command
 *
 * @author Shaan Gondalia
 */
public class TellTest {

  private static final double ARG_1 = 50;
  private static final double ARG_2 = 100;

  private TurtleManager myTurtleManager;
  private Turtle myTurtle;
  private List<Value> args;


  @BeforeEach
  void setUp() {
    myTurtleManager = new TurtleManager(1);
    args = new ArrayList<>();
    myTurtle = new Turtle();
  }

  @Test
  void testNoArgs() {
    assertThrows(MissingArgumentException.class, () -> new Tell(args, myTurtleManager));
  }

  @Test
  void testSetOneActiveTurtle() throws MissingArgumentException {
    args.add(new Value(ARG_1));
    Command tell = new Tell(args, myTurtleManager);
    tell.execute(myTurtle);
    assertEquals(ARG_1, tell.execute(myTurtle).getVal());
  }

  @Test
  void testSetTwoActiveTurtles() throws MissingArgumentException {
    args.add(new Value(ARG_1));
    args.add(new Value(ARG_2));
    Command tell = new Tell(args, myTurtleManager);
    assertEquals(ARG_2, tell.execute(myTurtle).getVal());
  }


}
