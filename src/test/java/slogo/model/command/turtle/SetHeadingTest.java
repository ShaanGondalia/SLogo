package slogo.model.command.turtle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.Main;
import slogo.model.command.Command;
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
    List<Double> args = new ArrayList<>();
    assertThrows(MissingArgumentException.class, () -> new SetHeading(myTurtle, args));
  }

  @Test
  void testTooManyArgs() throws MissingArgumentException {
    List<Double> args = new ArrayList<>();
    args.add(ARG_1);
    args.add(ARG_2);
    Command c = new SetHeading(myTurtle, args);
    assertEquals(ARG_1, c.execute());
    assertEquals(ARG_1, c.returnValue());
  }

  @Test
  void testHeadingSimple() throws MissingArgumentException {
    List<Double> args = new ArrayList<>();
    double arg = -15.0;
    args.add(arg);
    Command c = new SetHeading(myTurtle, args);
    assertEquals(arg, c.execute());
    assertEquals(arg, c.returnValue());
  }


}
