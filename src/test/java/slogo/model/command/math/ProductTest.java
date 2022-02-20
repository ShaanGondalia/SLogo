package slogo.model.command.math;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Tests for Product Command
 *
 * @author Shaan Gondalia
 */
public class ProductTest {

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
    assertThrows(MissingArgumentException.class, () -> new Product(myTurtle, args));
  }

  @Test
  void testTooManyArgs() throws MissingArgumentException {
    List<Double> args = new ArrayList<>();
    args.add(ARG_1);
    args.add(ARG_2);
    args.add(ARG_3);
    Product s = new Product(myTurtle, args);
    assertEquals(s.returnValue(), ARG_1 * ARG_2);
    assertEquals(s.execute(), ARG_1 * ARG_2);
  }

  @Test
  void testCorrectArgs() throws MissingArgumentException {
    List<Double> args = new ArrayList<>();
    args.add(ARG_1);
    args.add(ARG_2);
    Product s = new Product(myTurtle, args);
    assertEquals(s.returnValue(), ARG_1 * ARG_2);
    assertEquals(s.execute(), ARG_1 * ARG_2);
  }

}
