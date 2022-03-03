package slogo.model.command.bool;

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
 * Tests for NotEqual Command
 *
 * @author Shaan Gondalia
 */
public class NotEqualTest {

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
    assertThrows(MissingArgumentException.class, () -> new NotEqual(args));
  }

  @Test
  void testTooManyArgs() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(ARG_1));
    args.add(new Value(ARG_2));
    args.add(new Value(ARG_3));
    NotEqual s = new NotEqual(args);
    assertEquals(1.0, s.execute(myTurtle).getVal());
    assertEquals(1.0, s.returnValue().getVal());
  }

  @Test
  void testCorrectArgsGreater() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(ARG_2));
    args.add(new Value(ARG_1));
    NotEqual s = new NotEqual(args);
    assertEquals(1.0, s.execute(myTurtle).getVal());
    assertEquals(1.0, s.returnValue().getVal());
  }

  @Test
  void testCorrectArgsLess() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(ARG_1));
    args.add(new Value(ARG_2));
    NotEqual s = new NotEqual(args);
    assertEquals(1.0, s.execute(myTurtle).getVal());
    assertEquals(1.0, s.returnValue().getVal());
  }

  @Test
  void testCorrectArgsEqual() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(ARG_1));
    args.add(new Value(ARG_1));
    NotEqual s = new NotEqual(args);
    assertEquals(0.0, s.execute(myTurtle).getVal());
    assertEquals(0.0, s.returnValue().getVal());
  }

}
