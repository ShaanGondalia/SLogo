package slogo.model.turtle;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.color.ColorRecord;

/**
 * Tests for Turtle class
 *
 * @author Shaan Gondalia
 */
public class TurtleTest {

  private static final double FORWARD = 50;
  private static final double LEFT = 90;
  private static final double OVER_ROTATION = 450;
  private static final double EPSILON = 0.0001d;

  private Turtle myTurtle;

  @BeforeEach
  void setUp() {
    myTurtle = new Turtle();
  }

  @Test
  void testMoveForward() {
    myTurtle.move(FORWARD);
    assertEquals(FORWARD, myTurtle.getPose().y(), EPSILON);
    assertEquals(0, myTurtle.getPose().x(), EPSILON);
  }

  @Test
  void testRotate() {
    myTurtle.rotate(LEFT);
    assertEquals(LEFT, myTurtle.getPose().bearing(), EPSILON);
  }

  @Test
  void testOverRotate() {
    myTurtle.rotate(OVER_ROTATION);
    assertEquals(LEFT, myTurtle.getPose().bearing(), EPSILON);
  }

  @Test
  void testRotateMove() {
    myTurtle.rotate(LEFT);
    myTurtle.move(FORWARD);
    assertEquals(0, myTurtle.getPose().y(), EPSILON);
    assertEquals(FORWARD, myTurtle.getPose().x(), EPSILON);
  }

  @Test
  void testMoveSquare() {
    myTurtle.move(FORWARD);
    myTurtle.rotate(LEFT);
    myTurtle.move(FORWARD);
    myTurtle.rotate(LEFT);
    myTurtle.move(FORWARD);
    myTurtle.rotate(LEFT);
    myTurtle.move(FORWARD);

    assertEquals(0, myTurtle.getPose().y(), EPSILON);
    assertEquals(0, myTurtle.getPose().x(), EPSILON);
  }

  @Test
  void testColorListener() {
    TestListener listener = new TestListener();
    myTurtle.addListener(listener);
    myTurtle.move(20);
    TurtleStatus status = (TurtleStatus) listener.getChange().getNewValue();
    assertEquals(status.penState().color().toString(), "#000000");

    myTurtle.setPenColor(new ColorRecord(50, 0, 0));
    status = (TurtleStatus) listener.getChange().getNewValue();
    assertEquals(status.penState().color().toString(), "#320000");
  }


}
