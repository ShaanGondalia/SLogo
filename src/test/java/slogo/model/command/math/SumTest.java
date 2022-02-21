package slogo.model.command.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Tests for Sum Command
 *
 * @author Shaan Gondalia
 */
public class SumTest {

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
    List<Double> args = new ArrayList<>();
    args.add(ARG_1);
    assertThrows(MissingArgumentException.class, () -> new Sum(myTurtle, args));
  }

  @Test
  void testTooManyArgs() throws MissingArgumentException {
    List<Double> args = new ArrayList<>();
    args.add(ARG_1);
    args.add(ARG_2);
    args.add(ARG_3);
    Sum s = new Sum(myTurtle, args);
    assertEquals(ARG_1 + ARG_2, s.returnValue());
    assertEquals(ARG_1 + ARG_2, s.execute());
  }

  @Test
  void testCorrectArgs() throws MissingArgumentException {
    List<Double> args = new ArrayList<>();
    args.add(ARG_1);
    args.add(ARG_2);
    Sum s = new Sum(myTurtle, args);
    assertEquals(ARG_1 + ARG_2, s.returnValue());
    assertEquals(ARG_1 + ARG_2, s.execute());
  }

}
