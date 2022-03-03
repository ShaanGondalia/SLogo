package slogo.model.command.query;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.Main;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Tests for YCor Command
 *
 * @author Shaan Gondalia
 */
public class YCorTest {

  private static final double ARG_1 = 50;

  private Turtle myTurtle;

  @BeforeEach
  void setUp() {
    myTurtle = new Turtle();
  }

  @Test
  void testTooManyArgs() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(ARG_1));
    YCoordinate c = new YCoordinate(args);
    assertEquals(myTurtle.getPose().y(), c.returnValue().getVal(), Main.TOLERANCE);
    assertEquals(myTurtle.getPose().y(), c.execute(myTurtle).getVal(), Main.TOLERANCE);
  }

  @Test
  void testCorrectArgs() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    YCoordinate c = new YCoordinate(args);
    assertEquals(myTurtle.getPose().y(), c.returnValue().getVal(), Main.TOLERANCE);
    assertEquals(myTurtle.getPose().y(), c.execute(myTurtle).getVal(), Main.TOLERANCE);
  }

}
