package slogo.model.command.control;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.Main;
import slogo.model.command.Command;
import slogo.model.command.Value;
import slogo.model.command.turtle.Forward;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Tests for Repeat Command
 *
 * @author Shaan Gondalia
 */
public class RepeatTest{

  private static final double ARG_1 = 50;
  private static final double REPETITIONS = 10;

  Turtle myTurtle;
  List<Deque<Command>> bodies;

  @BeforeEach
  void setUp() throws MissingArgumentException {
    myTurtle = new Turtle();
    bodies = new ArrayList<>();
  }

  @Test
  void testConstant() throws MissingArgumentException {
    Value constant = new Value(ARG_1);
    makeBody(constant);

    List<Value> args = new ArrayList<>();
    args.add(new Value(REPETITIONS));

    Command repeatCommand = new Repeat(args, bodies);
    repeatCommand.execute(myTurtle);
    assertEquals(ARG_1, repeatCommand.returnValue().getVal(), Main.TOLERANCE);
    assertEquals(ARG_1 * REPETITIONS, myTurtle.getPose().y(), Main.TOLERANCE);
  }

  @Test
  void testNotEnoughLists(){
    List<Value> args = new ArrayList<>();
    args.add(new Value(REPETITIONS));
    assertThrows(MissingArgumentException.class, () -> new Repeat(args, bodies));
  }

  @Test
  void testNotEnoughArgs(){
    List<Value> args = new ArrayList<>();
    assertThrows(MissingArgumentException.class, () -> new Repeat(args, bodies));
  }

  private void makeBody(Value v) throws MissingArgumentException {
    List<Value> forArgs = new ArrayList<>();
    forArgs.add(v);
    Command forward = new Forward(forArgs);
    Deque<Command> body = new LinkedList<>();
    body.add(forward);
    bodies.add(body);
  }

}
