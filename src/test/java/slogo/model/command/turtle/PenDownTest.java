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
 * Tests for PenDown Command
 *
 * @author Shaan Gondalia
 */
public class PenDownTest {

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
    PenDown c = new PenDown(myTurtle, args);
    assertEquals(1.0, c.returnValue().getVal());
    assertEquals(1.0, c.execute().getVal());
  }

  @Test
  void testCorrectArgs() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    PenDown c = new PenDown(myTurtle, args);
    assertEquals(1.0, c.returnValue().getVal());
    assertEquals(1.0, c.execute().getVal());
  }

}
