package slogo.model.command.turtle;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Tests for Left Command
 *
 * @author Shaan Gondalia
 */
public class LeftTest {

  private static final double ARG_1 = 50;
  private static final double ARG_2 = 100;

  private Turtle myTurtle;

  @BeforeEach
  void setUp(){
    myTurtle = new Turtle();
  }

  @Test
  void testNotEnoughArgs(){
    List<Double> args = new ArrayList<>();
    assertThrows(MissingArgumentException.class, () -> new Left(myTurtle, args));
  }

  @Test
  void testTooManyArgs() throws MissingArgumentException {
    List<Double> args = new ArrayList<>();
    args.add(ARG_1);
    args.add(ARG_2);
    Left c = new Left(myTurtle, args);
    assertEquals(c.returnValue(), ARG_1);
    assertEquals(c.execute(), ARG_1);
  }

  @Test
  void testCorrectArgs() throws MissingArgumentException {
    List<Double> args = new ArrayList<>();
    args.add(ARG_1);
    Left c = new Left(myTurtle, args);
    assertEquals(c.returnValue(), ARG_1);
    double bearingBefore = myTurtle.getPose().bearing();
    assertEquals(c.execute(), ARG_1);
    assertEquals(myTurtle.getPose().bearing(), bearingBefore - ARG_1);
  }

}
