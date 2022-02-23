package slogo.model.command.turtle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.command.Command;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Tests for SetHeading
 *
 * @author Jake Heller
 */
public class SetHeadingTest {

  private static final double ARG_1 = 50;
  private static final double ARG_2 = 100;

  private Turtle myTurtle;

  @BeforeEach
  void setUp() {
    myTurtle = new Turtle();
  }

  @Test
  void testNotEnoughArgs() {
    List<Value> args = new ArrayList<>();
    assertThrows(MissingArgumentException.class, () -> new SetHeading(myTurtle, args));
  }

  @Test
  void testTooManyArgs() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(ARG_1));
    args.add(new Value(ARG_2));
    Command c = new SetHeading(myTurtle, args);
    assertEquals(ARG_1, c.execute().getVal());
    assertEquals(ARG_1, c.returnValue().getVal());
  }

  @Test
  void testHeadingSimple() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    double arg = -15.0;
    args.add(new Value(arg));
    Command c = new SetHeading(myTurtle, args);
    assertEquals(arg, c.execute().getVal());
    assertEquals(arg, c.returnValue().getVal());
  }


}
