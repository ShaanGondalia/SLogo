package slogo.model.command.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Tests for Pi Command
 *
 * @author Shaan Gondalia
 */
public class PiTest {

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
    Pi p = new Pi(myTurtle, args);
    assertEquals(Math.PI, p.returnValue().getVal());
    assertEquals(Math.PI, p.execute().getVal());
  }

  @Test
  void testCorrectArgs() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    Pi p = new Pi(myTurtle, args);
    assertEquals(Math.PI, p.returnValue().getVal());
    assertEquals(Math.PI, p.execute().getVal());
  }

}
