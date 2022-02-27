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
 * Tests for If Command
 *
 * @author Shaan Gondalia
 */
public class IfTest{

  private static final double ARG_1 = 50;

  Turtle myTurtle;
  List<Deque<Command>> lists;
  Deque<Command> body;

  @BeforeEach
  void setUp() throws MissingArgumentException {
    myTurtle = new Turtle();
    lists = new ArrayList<>();
    body = new LinkedList<>();
    List<Value> args = new ArrayList<>();
    Value variable = new Value(ARG_1);
    args.add(variable);
    Command forward = new Forward(myTurtle, args);
    body.add(forward);
    lists.add(body);
  }

  @Test
  void testFalse() throws MissingArgumentException {
    List<Value> ifArgs = new ArrayList<>();
    ifArgs.add(new Value(0));

    Command ifCommand = new If(myTurtle, ifArgs, lists);
    ifCommand.execute();
    assertEquals(0, ifCommand.returnValue().getVal(), Main.TOLERANCE);
    assertEquals(0, myTurtle.getPose().y(), Main.TOLERANCE);
  }

  @Test
  void testTrue() throws MissingArgumentException {
    List<Value> ifArgs = new ArrayList<>();
    ifArgs.add(new Value(1));

    Command ifCommand = new If(myTurtle, ifArgs, lists);
    ifCommand.execute();
    assertEquals(ARG_1, ifCommand.returnValue().getVal(), Main.TOLERANCE);
    assertEquals(ARG_1, myTurtle.getPose().y(), Main.TOLERANCE);
  }

}
