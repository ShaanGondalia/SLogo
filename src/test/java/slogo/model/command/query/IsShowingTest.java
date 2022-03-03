package slogo.model.command.query;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.Main;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Tests for IsShowing Command
 *
 * @author Shaan Gondalia
 */
public class IsShowingTest {

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
    IsShowing c = new IsShowing(args);
    assertEquals(1, c.execute(myTurtle).getVal(), Main.TOLERANCE);
    assertEquals(1, c.returnValue().getVal(), Main.TOLERANCE);
  }

  @Test
  void testCorrectArgs() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    IsShowing c = new IsShowing(args);
    assertEquals(1, c.execute(myTurtle).getVal(), Main.TOLERANCE);
    assertEquals(1, c.returnValue().getVal(), Main.TOLERANCE);
  }

}
