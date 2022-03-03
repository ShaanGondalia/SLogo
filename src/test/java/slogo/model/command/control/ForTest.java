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

public class ForTest {

  Turtle myTurtle;

  @BeforeEach
  void setUp() {
    myTurtle = new Turtle();
  }

  @Test
  void singleCommand() throws MissingArgumentException {
    Deque<Command> body = new LinkedList<>();
    List<Value> args = new ArrayList<>();

    Value variable = new Value();

    args.add(variable);
    Command forward = new Forward(args);
    body.add(forward);
    List<Deque<Command>> lists = new ArrayList<>();
    lists.add(body);

    List<Value> forArgs = new ArrayList<>();
    forArgs.add(variable);
    forArgs.add(new Value(1));
    forArgs.add(new Value(10));
    forArgs.add(new Value(1));

    Command forCommand = new For(forArgs, lists);
    forCommand.execute(myTurtle);
    assertEquals(9.0, forCommand.returnValue().getVal(), Main.TOLERANCE);
    assertEquals(45.0, myTurtle.getPose().y(), Main.TOLERANCE);
  }
}
