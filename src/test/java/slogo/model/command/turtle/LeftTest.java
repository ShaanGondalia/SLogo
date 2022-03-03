package slogo.model.command.turtle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.command.Value;
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
  void setUp() {
    myTurtle = new Turtle();
  }

  @Test
  void testNotEnoughArgs() {
    List<Value> args = new ArrayList<>();
    assertThrows(MissingArgumentException.class, () -> new Left(args));
  }

  @Test
  void testTooManyArgs() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(ARG_1));
    args.add(new Value(ARG_2));
    Left c = new Left(args);
    assertEquals(ARG_1, c.execute(myTurtle).getVal());
    assertEquals(ARG_1, c.returnValue().getVal());
  }

  @Test
  void testCorrectArgs() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(ARG_1));
    Left c = new Left(args);
    double bearingBefore = myTurtle.getPose().bearing();
    assertEquals(ARG_1, c.execute(myTurtle).getVal());
    assertEquals(ARG_1, c.returnValue().getVal());
    assertEquals(bearingBefore - ARG_1 + 360, myTurtle.getPose().bearing());
  }

}
