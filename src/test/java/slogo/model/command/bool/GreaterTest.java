package slogo.model.command.bool;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Tests for Greater Command
 *
 * @author Shaan Gondalia
 */
public class GreaterTest {

  private static final double ARG_1 = 50;
  private static final double ARG_2 = 75;
  private static final double ARG_3 = 100;

  private Turtle myTurtle;

  @BeforeEach
  void setUp(){
    myTurtle = new Turtle();
  }

  @Test
  void testNotEnoughArgs(){
    List<Double> args = new ArrayList<>();
    args.add(ARG_1);
    assertThrows(MissingArgumentException.class, () -> new Greater(myTurtle, args));
  }

  @Test
  void testTooManyArgs() throws MissingArgumentException {
    List<Double> args = new ArrayList<>();
    args.add(ARG_1);
    args.add(ARG_2);
    args.add(ARG_3);
    Greater s = new Greater(myTurtle, args);
    assertEquals(0.0, s.returnValue());
  }

  @Test
  void testCorrectArgsLess() throws MissingArgumentException {
    List<Double> args = new ArrayList<>();
    args.add(ARG_1);
    args.add(ARG_2);
    Greater s = new Greater(myTurtle, args);
    assertEquals(0.0, s.returnValue());
  }

  @Test
  void testCorrectArgsGreater() throws MissingArgumentException {
    List<Double> args = new ArrayList<>();
    args.add(ARG_2);
    args.add(ARG_1);
    Greater s = new Greater(myTurtle, args);
    assertEquals(1.0, s.returnValue());
  }

  @Test
  void testCorrectArgsEqual() throws MissingArgumentException {
    List<Double> args = new ArrayList<>();
    args.add(ARG_1);
    args.add(ARG_1);
    Greater s = new Greater(myTurtle, args);
    assertEquals(0.0, s.returnValue());
  }

}
