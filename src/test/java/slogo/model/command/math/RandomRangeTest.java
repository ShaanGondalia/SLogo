package slogo.model.command.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Tests for RandomRange Command
 *
 * @author Shaan Gondalia
 */
public class RandomRangeTest {

  private static final double ARG_1 = 50;
  private static final double ARG_2 = 100;
  private static final double ARG_3 = 75;

  private Turtle myTurtle;

  @BeforeEach
  void setUp() {
    myTurtle = new Turtle();
  }

  @Test
  void testNotEnoughArgs() {
    List<Value> args = new ArrayList<>();
    args.add(new Value(ARG_1));
    assertThrows(MissingArgumentException.class, () -> new RandomRange(args));
  }

  @Test
  void testTooManyArgs() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(ARG_1));
    args.add(new Value(ARG_2));
    args.add(new Value(ARG_3));
    RandomRange r = new RandomRange(args);
    r.execute(myTurtle);
    assertTrue(r.returnValue().getVal() < ARG_2 && r.returnValue().getVal() >= ARG_1);
  }

  @Test
  void testCorrectArgsSame() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(ARG_1));
    args.add(new Value(ARG_1));
    RandomRange r = new RandomRange(args);
    assertEquals(ARG_1, r.execute(myTurtle).getVal());
    assertEquals(ARG_1, r.returnValue().getVal());
  }

}
