package slogo.model.command.math;

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
 * Tests for Difference Command
 *
 * @author Shaan Gondalia
 */
public class DifferenceTest {

  private static final double ARG_1 = 50;
  private static final double ARG_2 = 75;
  private static final double ARG_3 = 100;

  private Turtle myTurtle;

  @BeforeEach
  void setUp() {
    myTurtle = new Turtle();
  }

  @Test
  void testNotEnoughArgs() {
    List<Value> args = new ArrayList<>();
    args.add(new Value(ARG_1));
    assertThrows(MissingArgumentException.class, () -> new Difference(args));
  }

  @Test
  void testTooManyArgs() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(ARG_1));
    args.add(new Value(ARG_2));
    args.add(new Value(ARG_3));
    Difference s = new Difference(args);
    s.execute(myTurtle);
    assertEquals(ARG_1 - ARG_2, s.returnValue().getVal());
    assertEquals(ARG_1 - ARG_2, s.execute(myTurtle).getVal());
  }

  @Test
  void testCorrectArgs() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(ARG_1));
    args.add(new Value(ARG_2));
    Difference s = new Difference(args);
    s.execute(myTurtle);
    assertEquals(ARG_1 - ARG_2, s.returnValue().getVal());
    assertEquals(ARG_1 - ARG_2, s.execute(myTurtle).getVal());
  }

  @Test
  void testCorrectArgsNegative() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(-ARG_1));
    args.add(new Value(ARG_2));
    Difference s = new Difference(args);
    s.execute(myTurtle);
    assertEquals(-ARG_1 - ARG_2, s.returnValue().getVal());
    assertEquals(-ARG_1 - ARG_2, s.execute(myTurtle).getVal());
  }

  @Test
  void testCorrectArgsDoubleNegative() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(ARG_1));
    args.add(new Value(-ARG_2));
    Difference s = new Difference(args);
    s.execute(myTurtle);
    assertEquals(ARG_1 + ARG_2, s.returnValue().getVal());
    assertEquals(ARG_1 + ARG_2, s.execute(myTurtle).getVal());
  }

}
