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
 * Tests for IfElse Command
 *
 * @author Shaan Gondalia
 */
public class IfElseTest{

  private static final double ARG_1 = 50;

  Turtle myTurtle;
  List<Deque<Command>> lists;

  @BeforeEach
  void setUp() throws MissingArgumentException {
    myTurtle = new Turtle();
    lists = new ArrayList<>();
    Deque<Command> body1 = new LinkedList<>();
    Deque<Command> body2 = new LinkedList<>();
    List<Value> args = new ArrayList<>();
    Value variable = new Value(ARG_1);
    args.add(variable);
    Command forward = new Forward(myTurtle, args);
    Command backward = new Backward(myTurtle, args);
    body1.add(forward);
    body2.add(backward);
    lists.add(body1);
    lists.add(body2);
  }

  @Test
  void testFalse() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(0));

    Command ifElseCommand = new IfElse(myTurtle, args, lists);
    ifElseCommand.execute();
    assertEquals(ARG_1, ifElseCommand.returnValue().getVal(), Main.TOLERANCE);
    assertEquals(-ARG_1, myTurtle.getPose().y(), Main.TOLERANCE);
  }

  @Test
  void testTrue() throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    args.add(new Value(1));

    Command ifElseCommand = new IfElse(myTurtle, args, lists);
    ifElseCommand.execute();
    assertEquals(ARG_1, ifElseCommand.returnValue().getVal(), Main.TOLERANCE);
    assertEquals(ARG_1, myTurtle.getPose().y(), Main.TOLERANCE);
  }

  @Test
  void testNotEnoughLists(){
    List<Value> args = new ArrayList<>();
    args.add(new Value(1));
    lists.remove(1);
    assertThrows(MissingArgumentException.class, () -> new IfElse(myTurtle, args, lists));
  }

  @Test
  void testNotEnoughArgs(){
    List<Value> args = new ArrayList<>();
    assertThrows(MissingArgumentException.class, () -> new IfElse(myTurtle, args, lists));
  }

}
