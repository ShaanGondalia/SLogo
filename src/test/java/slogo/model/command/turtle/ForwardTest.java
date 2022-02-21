package slogo.model.command.turtle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Tests for Forward Command
 *
 * @author Shaan Gondalia
 */
public class ForwardTest {

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
    assertThrows(MissingArgumentException.class, () -> new Forward(myTurtle, args));
  }

  @Test
  void testTooManyArgs() throws MissingArgumentException {
    List<Double> args = new ArrayList<>();
    args.add(ARG_1);
    args.add(ARG_2);
    Forward c = new Forward(myTurtle, args);
    assertEquals(ARG_1, c.returnValue());
    assertEquals(ARG_1, c.execute());
  }

  @Test
  void testCorrectArgs() throws MissingArgumentException {
    List<Double> args = new ArrayList<>();
    args.add(ARG_1);
    Forward c = new Forward(myTurtle, args);
    assertEquals(c.returnValue(), ARG_1);
    double xBefore = myTurtle.getPose().x();
    double yBefore = myTurtle.getPose().y();
    assertEquals(c.execute(), ARG_1);
    assertEquals(xBefore, myTurtle.getPose().x());
    assertEquals(yBefore + ARG_1, myTurtle.getPose().y());
  }

}