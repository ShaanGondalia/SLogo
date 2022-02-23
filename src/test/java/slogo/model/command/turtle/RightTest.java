package slogo.model.command.turtle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.Main;
import slogo.model.command.Command;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Tests for Right Command
 *
 * @author Shaan Gondalia
 */
public class RightTest {

  private static final double ARG_1 = 50;
  private static final double ARG_2 = 100;

  private Turtle myTurtle;

  @BeforeEach
  void setUp() {
    myTurtle = new Turtle();
  }

  @Test
  void testNotEnoughArgs() {
    List<Value> args = new ArrayList<>();
    assertThrows(MissingArgumentException.class, () -> new Right(myTurtle, args));
  }

  @Test
  void testTooManyArgs() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(ARG_1));
    args.add(new Value(ARG_2));
    Right c = new Right(myTurtle, args);
    assertEquals(ARG_1, c.returnValue().getVal());
    assertEquals(ARG_1, c.execute().getVal());
  }

  @Test
  void testCorrectArgs() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(ARG_1));
    Right c = new Right(myTurtle, args);
    assertEquals(ARG_1, c.returnValue().getVal());
    double bearingBefore = myTurtle.getPose().bearing();
    double xBefore = myTurtle.getPose().x();
    double yBefore = myTurtle.getPose().y();
    assertEquals(ARG_1, c.execute().getVal());
    assertEquals(bearingBefore + ARG_1, myTurtle.getPose().bearing());
    assertEquals(xBefore, myTurtle.getPose().x(), Main.TOLERANCE);
    assertEquals(yBefore, myTurtle.getPose().y(), Main.TOLERANCE);
  }

  @Test
  void testRight1() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(45));
    Command right = new Right(myTurtle, args);
    double answer = 45;
    assertEquals(45, right.execute().getVal(), Main.TOLERANCE);
    assertEquals(answer, myTurtle.getPose().bearing(), Main.TOLERANCE);
  }

}
