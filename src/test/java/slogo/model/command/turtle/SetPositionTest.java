package slogo.model.command.turtle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.Main;
import slogo.model.command.Command;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Tests for SetPosition Command
 *
 * @author Jake Heller
 */
public class SetPositionTest {

  private Turtle myTurtle;

  @BeforeEach
  void setUp() {
    myTurtle = new Turtle();
  }

  @Test
  void testTooFewArgs() throws MissingArgumentException {
    List<Double> args = new ArrayList<>();
    args.add(1.0);
    assertThrows(MissingArgumentException.class, () -> new SetPosition(myTurtle, args));
  }

  @Test
  void testSetPositionSimple () throws MissingArgumentException {
    List<Double> args = new ArrayList<>();
    args.add(3.0);
    args.add(4.0);
    Command setPos = new SetPosition(myTurtle, args);
    double distance = 5.0;
    assertEquals(distance, setPos.execute(), Main.TOLERANCE);
  }

}
