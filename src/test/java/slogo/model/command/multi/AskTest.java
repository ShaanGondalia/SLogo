package slogo.model.command.multi;

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
import slogo.model.command.control.If;
import slogo.model.command.turtle.Forward;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Pose;
import slogo.model.turtle.Turtle;
import slogo.model.turtle.TurtleManager;

/**
 * Tests for Ask Command
 *
 * @author Shaan Gondalia
 */
public class AskTest {

  private static final double ARG_1 = 50;
  private static final double ARG_2 = 100;

  private TurtleManager myTurtleManager;
  private Turtle myTurtle;
  private List<Value> args;
  private List<Deque<Command>> lists;
  private Deque<Command> body;


  @BeforeEach
  void setUp() throws MissingArgumentException {
    myTurtleManager = new TurtleManager();
    args = new ArrayList<>();
    lists = new ArrayList<>();
    myTurtle = new Turtle();
    body = new LinkedList<>();
    List<Value> args = new ArrayList<>();
    Value variable = new Value(ARG_1);
    args.add(variable);
    Command forward = new Forward(args);
    body.add(forward);
    lists.add(body);
  }

  @Test
  void testNoArgs() {
    assertThrows(MissingArgumentException.class, () -> new Ask(args, lists, myTurtleManager));
  }

  @Test
  void testNoBody() {
    assertThrows(MissingArgumentException.class, () -> new Ask(args, new ArrayList<>(), myTurtleManager));
  }

  @Test
  void testSetOneActiveTurtle() throws MissingArgumentException {
    args.add(new Value(ARG_1));
    Command ask = new Ask(args, lists, myTurtleManager);
    ask.execute(myTurtle);
    assertEquals(ARG_1, ask.execute(myTurtle).getVal());
  }

  @Test
  void testSetTwoActiveTurtles() throws MissingArgumentException {
    args.add(new Value(ARG_1));
    args.add(new Value(ARG_2));
    Command ask = new Ask(args, lists, myTurtleManager);
    assertEquals(ARG_1, ask.execute(myTurtle).getVal());
  }


}
