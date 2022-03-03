package slogo.model.command.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.Main;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Tests for ArcTangent Command
 *
 * @author Shaan Gondalia
 */
public class ArcTangentTest {

  private static final double ARG_1 = 0;
  private static final double ARG_2 = 1;

  private Turtle myTurtle;

  @BeforeEach
  void setUp() {
    myTurtle = new Turtle();
  }

  @Test
  void testNotEnoughArgs() {
    List<Value> args = new ArrayList<>();
    assertThrows(MissingArgumentException.class, () -> new ArcTangent(args));
  }

  @Test
  void testTooManyArgs() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(ARG_1));
    args.add(new Value(ARG_2));
    ArcTangent p = new ArcTangent(args);
    assertEquals(0, p.execute(myTurtle).getVal(), Main.TOLERANCE);
    assertEquals(0, p.returnValue().getVal(), Main.TOLERANCE);
  }

  @Test
  void testCorrectArgs45() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(ARG_2));
    ArcTangent p = new ArcTangent(args);
    assertEquals(45, p.execute(myTurtle).getVal(), Main.TOLERANCE);
    assertEquals(45, p.returnValue().getVal(), Main.TOLERANCE);
  }

  @Test
  void testCorrectArgsMinus45() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(-ARG_2));
    ArcTangent p = new ArcTangent(args);
    assertEquals(-45, p.execute(myTurtle).getVal(), Main.TOLERANCE);
    assertEquals(-45, p.returnValue().getVal(), Main.TOLERANCE);
  }

}
