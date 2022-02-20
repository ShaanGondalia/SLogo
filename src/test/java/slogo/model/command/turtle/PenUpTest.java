package slogo.model.command.turtle;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Tests for PenUp Command
 *
 * @author Shaan Gondalia
 */
public class PenUpTest {

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
    PenUp c = new PenUp(myTurtle, args);
    assertEquals(c.returnValue(), 0.0);
    assertEquals(c.execute(), 0.0);
  }

  @Test
  void testCorrectArgs() throws MissingArgumentException {
    List<Double> args = new ArrayList<>();
    PenUp c = new PenUp(myTurtle, args);
    assertEquals(c.returnValue(), 0.0);
    assertEquals(c.execute(), 0.0);
  }

}
