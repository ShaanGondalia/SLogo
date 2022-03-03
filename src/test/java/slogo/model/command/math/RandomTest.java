package slogo.model.command.math;

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
 * Tests for Random Command
 *
 * @author Shaan Gondalia
 */
public class RandomTest {

  private static final double ARG_1 = 50;
  private static final double ARG_2 = 75;

  private Turtle myTurtle;

  @BeforeEach
  void setUp() {
    myTurtle = new Turtle();
  }

  @Test
  void testNotEnoughArgs() {
    List<Value> args = new ArrayList<>();
    assertThrows(MissingArgumentException.class, () -> new Random(args));
  }

  @Test
  void testTooManyArgs() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(ARG_1));
    args.add(new Value(ARG_2));
    Random r = new Random(args);
    r.execute(myTurtle);
    assertTrue(r.returnValue().getVal() < ARG_1 && r.returnValue().getVal() >= 0);
  }

  @Test
  void testCorrectArgs() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(ARG_1));
    Random r = new Random(args);
    r.execute(myTurtle);
    assertTrue(r.returnValue().getVal() < ARG_1 && r.returnValue().getVal() >= 0);
  }

}
