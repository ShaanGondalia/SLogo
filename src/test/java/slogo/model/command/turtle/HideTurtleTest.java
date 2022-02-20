package slogo.model.command.turtle;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Tests for HideTurtle Command
 *
 * @author Shaan Gondalia
 */
public class HideTurtleTest {

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
    HideTurtle c = new HideTurtle(myTurtle, args);
    assertEquals(0.0, c.returnValue());
    assertEquals(0.0, c.execute());
  }

  @Test
  void testCorrectArgs() throws MissingArgumentException {
    List<Double> args = new ArrayList<>();
    HideTurtle c = new HideTurtle(myTurtle, args);
    assertEquals(0.0, c.returnValue());
    assertEquals(0.0, c.execute());
  }

}
