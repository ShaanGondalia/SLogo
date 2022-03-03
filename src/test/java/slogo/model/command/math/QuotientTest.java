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
 * Tests for Quotient Command
 *
 * @author Shaan Gondalia
 */
public class QuotientTest {

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
    assertThrows(MissingArgumentException.class, () -> new Quotient(args));
  }

  @Test
  void testTooManyArgs() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(ARG_1));
    args.add(new Value(ARG_2));
    args.add(new Value(ARG_3));
    Quotient s = new Quotient(args);
    assertEquals(ARG_1 / ARG_2, s.execute(myTurtle).getVal());
    assertEquals(ARG_1 / ARG_2, s.returnValue().getVal());
  }

  @Test
  void testCorrectArgs() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(ARG_1));
    args.add(new Value(ARG_2));
    Quotient s = new Quotient(args);
    assertEquals(ARG_1 / ARG_2, s.execute(myTurtle).getVal());
    assertEquals(ARG_1 / ARG_2, s.returnValue().getVal());
  }

  @Test
  void testCorrectNegativeDivisor() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(ARG_1));
    args.add(new Value(-ARG_2));
    Quotient s = new Quotient(args);
    assertEquals(ARG_1 / -ARG_2, s.execute(myTurtle).getVal());
    assertEquals(ARG_1 / -ARG_2, s.returnValue().getVal());
  }

  @Test
  void testCorrectNegativeDividend() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(-ARG_1));
    args.add(new Value(ARG_2));
    Quotient s = new Quotient(args);
    assertEquals(-ARG_1 / ARG_2, s.execute(myTurtle).getVal());
    assertEquals(-ARG_1 / ARG_2, s.returnValue().getVal());
  }

  @Test
  void testCorrectDoubleNegative() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(-ARG_1));
    args.add(new Value(-ARG_2));
    Quotient s = new Quotient(args);
    assertEquals(ARG_1 / ARG_2, s.execute(myTurtle).getVal());
    assertEquals(ARG_1 / ARG_2, s.returnValue().getVal());
  }

}
