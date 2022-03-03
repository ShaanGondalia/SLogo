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
 * Tests for Minus Command
 *
 * @author Shaan Gondalia
 */
public class MinusTest {

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
    assertThrows(MissingArgumentException.class, () -> new Minus(args));
  }

  @Test
  void testTooManyArgs() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(ARG_1));
    args.add(new Value(ARG_2));
    Minus p = new Minus(args);
    assertEquals(-ARG_1, p.execute(myTurtle).getVal());
    assertEquals(-ARG_1, p.returnValue().getVal());
  }

  @Test
  void testCorrectArgsNegative() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(-ARG_1));
    Minus p = new Minus(args);
    assertEquals(ARG_1, p.execute(myTurtle).getVal());
    assertEquals(ARG_1, p.returnValue().getVal());
  }

}
