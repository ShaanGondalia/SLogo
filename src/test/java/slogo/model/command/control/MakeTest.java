package slogo.model.command.control;

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
 * Tests for Make Command
 *
 * @author Shaan Gondalia
 */
public class MakeTest {

  private static final double ARG_1 = 50;
  private static final double ARG_2 = 75;
  private static final double ARG_3 = 100;

  private Turtle myTurtle;

  @BeforeEach
  void setUp() {
    myTurtle = new Turtle();
  }

  @Test
  void testNotEnoughArgs() {
    List<Value> args = new ArrayList<>();
    args.add(new Value(ARG_1));
    assertThrows(MissingArgumentException.class, () -> new Make(args));
  }

  @Test
  void testTooManyArgs() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(ARG_1));
    args.add(new Value(ARG_2));
    args.add(new Value(ARG_3));
    Make m = new Make(args);
    assertEquals(ARG_2, m.execute(myTurtle).getVal());
    assertEquals(ARG_2, m.returnValue().getVal());
  }

  @Test
  void testCorrectArgsOverwrite() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    Value var = new Value(ARG_1);
    args.add(var);
    args.add(new Value(ARG_2));
    Make m = new Make(args);
    assertEquals(ARG_2, m.execute(myTurtle).getVal());
    assertEquals(ARG_2, var.getVal());
  }

}
