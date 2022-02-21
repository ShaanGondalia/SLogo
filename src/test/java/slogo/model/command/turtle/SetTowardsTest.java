package slogo.model.command.turtle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Pose;
import slogo.model.turtle.Turtle;

/**
 * Tests for SetTowards Command
 *
 * @author Shaan Gondalia
 */
public class SetTowardsTest {

  private static final double X_POS = 1;
  private static final double Y_POS = 1;
  private static final double X_NEG = -1;
  private static final double Y_NEG = -1;
  private static final double EPSILON = 0.0001d;

  private Turtle myTurtle;

  @BeforeEach
  void setUp() {
    myTurtle = new Turtle();
  }

  @Test
  void testNotEnoughArgs() {
    List<Double> args = new ArrayList<>();
    assertThrows(MissingArgumentException.class, () -> new SetTowards(myTurtle, args));
  }

  @Test
  void testRotateFirstQuadrant() throws MissingArgumentException {
    List<Double> args = new ArrayList<>();
    args.add(X_POS);
    args.add(Y_POS);
    SetTowards c = new SetTowards(myTurtle, args);
    assertEquals(45, c.execute(), EPSILON);
  }

  @Test
  void testRotateThirdQuadrant() throws MissingArgumentException {
    List<Double> args = new ArrayList<>();
    args.add(X_NEG);
    args.add(Y_NEG);
    SetTowards c = new SetTowards(myTurtle, args);
    assertEquals(135, c.execute(), EPSILON);
  }

  @Test
  void testRotateFirstQuadrantAlreadyRotated() throws MissingArgumentException {
    List<Double> args = new ArrayList<>();
    args.add(X_POS);
    args.add(Y_POS);
    Pose pose = new Pose(0, 0, 60);
    myTurtle.setPose(pose);
    SetTowards c = new SetTowards(myTurtle, args);
    assertEquals(15, c.execute(), EPSILON);
  }

  @Test
  void testRotateThirdQuadrantAlreadyRotated() throws MissingArgumentException {
    List<Double> args = new ArrayList<>();
    args.add(X_NEG);
    args.add(Y_NEG);
    Pose pose = new Pose(0, 0, 60);
    myTurtle.setPose(pose);
    SetTowards c = new SetTowards(myTurtle, args);
    assertEquals(165, c.execute(), EPSILON);
  }

}