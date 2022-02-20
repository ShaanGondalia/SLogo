package slogo.model.command.query;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Tests for XCor Command
 *
 * @author Shaan Gondalia
 */
public class XCorTest {

  private static final double ARG_1 = 50;

  private Turtle myTurtle;

  @BeforeEach
  void setUp(){
    myTurtle = new Turtle();
  }

  @Test
  void testTooManyArgs() throws MissingArgumentException {
    List<Double> args = new ArrayList<>();
    args.add(ARG_1);
    XCor c = new XCor(myTurtle, args);
    assertEquals(myTurtle.getPose().x(), c.returnValue());
    assertEquals(myTurtle.getPose().x(), c.execute());
  }

  @Test
  void testCorrectArgs() throws MissingArgumentException {
    List<Double> args = new ArrayList<>();
    XCor c = new XCor(myTurtle, args);
    assertEquals(myTurtle.getPose().x(), c.returnValue());
    assertEquals(myTurtle.getPose().x(), c.execute());
  }

}
