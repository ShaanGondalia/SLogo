package slogo.model.command.math;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Tests for Pi Command
 *
 * @author Shaan Gondalia
 */
public class PiTest {

  private static final double ARG_1 = 50;

  private Turtle myTurtle;

  @BeforeEach
  void setUp(){
    myTurtle = new Turtle();
  }

  @Test
  void testTooManyArgs() throws MissingArgumentException {
    List<Double> args = new ArrayList<>();
    args.add(ARG_1);
    Pi p = new Pi(myTurtle, args);
    assertEquals(p.returnValue(), Math.PI);
    assertEquals(p.execute(), Math.PI);
  }

  @Test
  void testCorrectArgs() throws MissingArgumentException {
    List<Double> args = new ArrayList<>();
    Pi p = new Pi(myTurtle, args);
    assertEquals(p.returnValue(), Math.PI);
    assertEquals(p.execute(), Math.PI);
  }

}
