package slogo.model.command.turtle;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Pose;
import slogo.model.turtle.Turtle;

/**
 * Tests for ClearScreen Command
 *
 * @author Shaan Gondalia
 */
public class ClearScreenTest {

  private static final double ARG_1 = 50;
  private static final double MOVE_X = 3;
  private static final double MOVE_Y = 4;
  private static final double BEARING = 0;

  private Turtle myTurtle;

  @BeforeEach
  void setUp() {
    myTurtle = new Turtle();
  }

  @Test
  void testTooManyArgs() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(ARG_1));
    ClearScreen c = new ClearScreen(args);
    assertEquals(0.0, c.execute(myTurtle).getVal());
  }

  @Test
  void testCorrectArgs() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    ClearScreen c = new ClearScreen(args);
    assertEquals(0.0, c.execute(myTurtle).getVal());
  }

  @Test
  void testAfterMovement() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    Pose p = new Pose(MOVE_X, MOVE_Y, BEARING);
    myTurtle.setPose(p);
    ClearScreen c = new ClearScreen(args);
    assertEquals(5, c.execute(myTurtle).getVal());
  }

}
