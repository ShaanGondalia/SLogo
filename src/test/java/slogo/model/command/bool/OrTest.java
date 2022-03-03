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
 * Tests for Or Command
 *
 * @author Shaan Gondalia
 */
public class OrTest {

  private static final double ARG_1 = 1;
  private static final double ARG_2 = 0;
  private static final double ARG_3 = 0;

  private Turtle myTurtle;

  @BeforeEach
  void setUp() {
    myTurtle = new Turtle();
  }

  @Test
  void testNotEnoughArgs() {
    List<Value> args = new ArrayList<>();
    args.add(new Value(ARG_1));
    assertThrows(MissingArgumentException.class, () -> new Or(args));
  }

  @Test
  void testTooManyArgs() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(ARG_1));
    args.add(new Value(ARG_2));
    args.add(new Value(ARG_3));
    Or s = new Or(args);
    assertEquals(1.0, s.execute(myTurtle).getVal());
    assertEquals(1.0, s.returnValue().getVal());
  }

  @Test
  void testCorrectArgsArgBothTrue() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(ARG_1));
    args.add(new Value(ARG_1));
    Or s = new Or(args);
    assertEquals(1.0, s.execute(myTurtle).getVal());
    assertEquals(1.0, s.returnValue().getVal());
  }

  @Test
  void testCorrectArgsArg1True() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(ARG_1));
    args.add(new Value(ARG_2));
    Or s = new Or(args);
    assertEquals(1.0, s.execute(myTurtle).getVal());
    assertEquals(1.0, s.returnValue().getVal());
  }

  @Test
  void testCorrectArgsArg2True() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(ARG_2));
    args.add(new Value(ARG_1));
    Or s = new Or(args);
    assertEquals(1.0, s.execute(myTurtle).getVal());
    assertEquals(1.0, s.returnValue().getVal());
  }

  @Test
  void testCorrectArgsFalse() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(ARG_2));
    args.add(new Value(ARG_2));
    Or s = new Or(args);
    assertEquals(0.0, s.execute(myTurtle).getVal());
    assertEquals(0.0, s.returnValue().getVal());
  }

}
