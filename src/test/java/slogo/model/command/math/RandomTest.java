package slogo.model.command.math;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
  void setUp(){
    myTurtle = new Turtle();
  }

  @Test
  void testNotEnoughArgs(){
    List<Double> args = new ArrayList<>();
    assertThrows(MissingArgumentException.class, () -> new Random(myTurtle, args));
  }

  @Test
  void testTooManyArgs() throws MissingArgumentException {
    List<Double> args = new ArrayList<>();
    args.add(ARG_1);
    args.add(ARG_2);
    Random r = new Random(myTurtle, args);
    assertTrue(r.returnValue() < ARG_1 && r.returnValue() >= 0);
  }

  @Test
  void testCorrectArgs() throws MissingArgumentException {
    List<Double> args = new ArrayList<>();
    args.add(ARG_1);
    Random r = new Random(myTurtle, args);
    assertTrue(r.returnValue() < ARG_1 && r.returnValue() >= 0);
  }

}
