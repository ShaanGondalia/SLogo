package slogo.model.command.control;

import java.util.Deque;
import java.util.List;
import slogo.model.command.Command;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Creates IfElse command, which returns the value of the last command run
 *
 * @author Jake Heller
 */
public class IfElse extends ControlCommand {

  private static final int NUM_ARGS = 1;
  private static final int NUM_LISTS = 2;

  private Value myExpr;
  private Deque<Command> myTrueBody;
  private Deque<Command> myFalseBody;

  public IfElse(List<Value> args, List<Deque<Command>> lists)
      throws MissingArgumentException {
    super(args, NUM_ARGS);
    verifyCommandLists(lists, NUM_LISTS);

    myExpr = args.get(0);
    myTrueBody = lists.get(0);
    myFalseBody = lists.get(1);
  }

  @Override
  public Value execute(Turtle turtle) throws MissingArgumentException {
    Deque<Command> body;
    if (myExpr.equals(new Value(0))) {
      body = myFalseBody;
    } else {
      body = myTrueBody;
    }
    for (Command c : body) {
      c.execute(turtle);
    }
    setReturnValue(body.peekLast().returnValue().getVal());
    return returnValue();
  }
}
