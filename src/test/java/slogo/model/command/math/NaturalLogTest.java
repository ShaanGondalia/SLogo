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
 * Tests for NaturalLog Command
 *
 * @author Shaan Gondalia
 */
public class NaturalLogTest {

  private static final double ARG_1 = Math.E;
  private static final double ARG_2 = 10;

  private Turtle myTurtle;

  @BeforeEach
  void setUp() {
    myTurtle = new Turtle();
  }

  @Test
  void testNotEnoughArgs() {
    List<Value> args = new ArrayList<>();
    assertThrows(MissingArgumentException.class, () -> new NaturalLog(args));
  }

  @Test
  void testTooManyArgs() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(ARG_1));
    args.add(new Value(ARG_2));
    NaturalLog p = new NaturalLog(args);
    assertEquals(1, p.execute(myTurtle).getVal());
    assertEquals(1, p.returnValue().getVal());
  }

  @Test
  void testCorrectArgsImperfect() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(ARG_2));
    NaturalLog p = new NaturalLog(args);
    assertEquals(Math.log(ARG_2), p.execute(myTurtle).getVal());
    assertEquals(Math.log(ARG_2), p.returnValue().getVal());
  }

  @Test
  void testCorrectArgsNegative() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(-ARG_1));
    NaturalLog p = new NaturalLog(args);
    assertEquals(NaN, p.execute(myTurtle).getVal());
    assertEquals(NaN, p.returnValue().getVal());
  }

}
