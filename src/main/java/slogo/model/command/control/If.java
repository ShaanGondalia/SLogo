package slogo.model.command.control;

import java.util.Deque;
import java.util.List;
import slogo.model.command.Command;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

public class If extends ControlCommand {

  private static final int NUM_ARGS = 1;
  private static final int NUM_LISTS = 1;

  private Value myExpr;
  private Deque<Command> myBody;

  public If(List<Value> args, List<Deque<Command>> lists)
      throws MissingArgumentException {
    super(args, NUM_ARGS);
    verifyCommandLists(lists, NUM_LISTS);

    myExpr = args.get(0);
    myBody = lists.get(0);
  }

  @Override
  public Value execute(Turtle turtle) throws MissingArgumentException {
    Deque<Command> body;
    if (myExpr.equals(new Value(0))) {
      setReturnValue(0);
    } else {
      for (Command c : myBody) {
        c.execute(turtle);
      }
      setReturnValue(myBody.peekLast().returnValue().getVal());
    }
    return returnValue();
  }
}