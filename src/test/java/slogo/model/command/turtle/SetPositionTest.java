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
    List<Value> args = new ArrayList<>();
    args.add(new Value(1));
    assertThrows(MissingArgumentException.class, () -> new SetPosition(args));
  }

  @Test
  void testSetPositionSimple() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(3));
    args.add(new Value(4));
    Command setPos = new SetPosition(args);
    double distance = 5.0;
    assertEquals(distance, setPos.execute(myTurtle).getVal(), Main.TOLERANCE);
  }

  // this has caused turtle to disappear in GUI
  @Test
  void testSamePosTwice() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(3));
    args.add(new Value(4));
    Command setPos = new SetPosition(args);
    double distance = 5.0;
    assertEquals(distance, setPos.execute(myTurtle).getVal(), Main.TOLERANCE);
    assertEquals(4, myTurtle.getPose().y(), Main.TOLERANCE);

    setPos.execute(myTurtle);
    assertEquals(0.0, setPos.returnValue().getVal(), Main.TOLERANCE);
    assertEquals(4, myTurtle.getPose().y(), Main.TOLERANCE);
  }
}
