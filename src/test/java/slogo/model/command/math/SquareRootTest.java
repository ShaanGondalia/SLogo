package slogo.model.command.math;

import static java.lang.Double.NaN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Tests for SquareRoot Command
 *
 * @author Shaan Gondalia
 */
public class SquareRootTest {

  private static final double ARG_1 = 9;
  private static final double ARG_2 = 2;

  private Turtle myTurtle;

  @BeforeEach
  void setUp() {
    myTurtle = new Turtle();
  }

  @Test
  void testNotEnoughArgs() {
    List<Value> args = new ArrayList<>();
    assertThrows(MissingArgumentException.class, () -> new SquareRoot(args));
  }

  @Test
  void testTooManyArgs() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(ARG_1));
    args.add(new Value(ARG_2));
    SquareRoot p = new SquareRoot(args);
    assertEquals(3, p.execute(myTurtle).getVal());
    assertEquals(3, p.returnValue().getVal());
  }

  @Test
  void testCorrectArgsImperfectSquare() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(ARG_2));
    SquareRoot p = new SquareRoot(args);
    assertEquals(Math.sqrt(ARG_2), p.execute(myTurtle).getVal());
    assertEquals(Math.sqrt(ARG_2), p.returnValue().getVal());
  }

  @Test
  void testCorrectArgsNegative() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(-ARG_1));
    SquareRoot p = new SquareRoot(args);
    assertEquals(NaN, p.execute(myTurtle).getVal());
    assertEquals(NaN, p.returnValue().getVal());
  }

}
