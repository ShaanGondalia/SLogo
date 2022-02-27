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
import slogo.model.command.turtle.Backward;
import slogo.model.command.turtle.Forward;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Tests for DoTimes Command
 *
 * @author Shaan Gondalia
 */
public class DoTimesTest{

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
    args.add(new Value());
    args.add(new Value(REPETITIONS));

    Command doTimesCommand = new DoTimes(myTurtle, args, bodies);
    doTimesCommand.execute();
    assertEquals(ARG_1, doTimesCommand.returnValue().getVal(), Main.TOLERANCE);
    assertEquals(ARG_1 * REPETITIONS, myTurtle.getPose().y(), Main.TOLERANCE);
  }

  @Test
  void testVariable() throws MissingArgumentException {
    Value variable = new Value();
    makeBody(variable);

    List<Value> args = new ArrayList<>();
    args.add(variable);
    args.add(new Value(REPETITIONS));

    Command doTimesCommand = new DoTimes(myTurtle, args, bodies);
    doTimesCommand.execute();
    assertEquals(REPETITIONS, doTimesCommand.returnValue().getVal(), Main.TOLERANCE);
    assertEquals(REPETITIONS * (REPETITIONS + 1) / 2, myTurtle.getPose().y(), Main.TOLERANCE);
  }

  @Test
  void testNotEnoughLists(){
    List<Value> args = new ArrayList<>();
    args.add(new Value());
    args.add(new Value(REPETITIONS));
    assertThrows(MissingArgumentException.class, () -> new DoTimes(myTurtle, args, bodies));
  }

  @Test
  void testNotEnoughArgs(){
    List<Value> args = new ArrayList<>();
    args.add(new Value());
    assertThrows(MissingArgumentException.class, () -> new DoTimes(myTurtle, args, bodies));
  }

  private void makeBody(Value v) throws MissingArgumentException {
    List<Value> forArgs = new ArrayList<>();
    forArgs.add(v);
    Command forward = new Forward(myTurtle, forArgs);
    Deque<Command> body = new LinkedList<>();
    body.add(forward);
    bodies.add(body);
  }

}
