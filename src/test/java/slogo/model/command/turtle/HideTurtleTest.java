package slogo.model.command.turtle;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Tests for HideTurtle Command
 *
 * @author Shaan Gondalia
 */
public class HideTurtleTest {

  private static final double ARG_1 = 50;

  private Turtle myTurtle;

  @BeforeEach
  void setUp() {
    myTurtle = new Turtle();
  }

  @Test
  void testTooManyArgs() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(ARG_1));
    HideTurtle c = new HideTurtle(args);
    assertEquals(0.0, c.returnValue().getVal());
    assertEquals(0.0, c.execute(myTurtle).getVal());
  }

  @Test
  void testCorrectArgs() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    HideTurtle c = new HideTurtle(args);
    assertEquals(0.0, c.returnValue().getVal());
    assertEquals(0.0, c.execute(myTurtle).getVal());
  }

}
