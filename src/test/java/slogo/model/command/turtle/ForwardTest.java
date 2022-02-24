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
import slogo.model.turtle.Pose;
import slogo.model.turtle.Turtle;

/**
 * Tests for Forward Command
 *
 * @author Shaan Gondalia
 */
public class ForwardTest {

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
    assertThrows(MissingArgumentException.class, () -> new Forward(myTurtle, args));
  }

  @Test
  void testTooManyArgs() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(ARG_1));
    args.add(new Value(ARG_2));
    Forward c = new Forward(myTurtle, args);
    Value returnVal = c.returnValue();
    assertEquals(ARG_1, c.execute().getVal());
    assertEquals(ARG_1, returnVal.getVal());
  }

  @Test
  void testCorrectArgs() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(ARG_1));
    Forward c = new Forward(myTurtle, args);

    double xBefore = myTurtle.getPose().x();
    double yBefore = myTurtle.getPose().y();
    assertEquals(ARG_1, c.execute().getVal(), Main.TOLERANCE);
    assertEquals(xBefore, myTurtle.getPose().x(), Main.TOLERANCE);
    assertEquals(ARG_1, c.returnValue().getVal(), Main.TOLERANCE);
    assertEquals(yBefore + ARG_1, myTurtle.getPose().y(), Main.TOLERANCE);
  }

  @Test
  void testForward2() throws MissingArgumentException {
    List<Value> rotateArgs = new ArrayList<>();
    rotateArgs.add(new Value(45.0));
    Command rotate = new Right(myTurtle, rotateArgs);
    rotate.execute();

    List<Value> forwardArgs = new ArrayList<>();

    double fdAmount = 10;
    forwardArgs.add(new Value(fdAmount));
    Command forward = new Forward(myTurtle, forwardArgs);
    forward.execute();
    Pose pose = myTurtle.getPose();
    double answer = Math.sqrt(Math.pow(fdAmount, 2) / 2.0);
    // String msg = String.format("Expected %f and got %f", answer, pose.x());
    assertEquals(answer, pose.x(), Main.TOLERANCE);
  }

}
