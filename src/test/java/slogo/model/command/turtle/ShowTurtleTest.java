package slogo.model.command.turtle;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Tests for ShowTurtle Command
 *
 * @author Shaan Gondalia
 */
public class ShowTurtleTest {

  private static final double ARG_1 = 50;

  private Turtle myTurtle;

  @BeforeEach
  void setUp() {
    myTurtle = new Turtle();
  }

  @Test
  void testTooManyArgs() throws MissingArgumentException {
    List<Double> args = new ArrayList<>();
    args.add(ARG_1);
    ShowTurtle c = new ShowTurtle(myTurtle, args);
    assertEquals(1.0, c.returnValue());
    assertEquals(1.0, c.execute());
  }

  @Test
  void testCorrectArgs() throws MissingArgumentException {
    List<Double> args = new ArrayList<>();
    ShowTurtle c = new ShowTurtle(myTurtle, args);
    assertEquals(1.0, c.returnValue());
    assertEquals(1.0, c.execute());
  }

}
