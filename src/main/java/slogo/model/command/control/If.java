package slogo.model.command.control;

import java.util.Deque;
import java.util.List;
import slogo.model.command.Command;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

public class If extends ControlCommand {

  private static int NUM_ARGS = 1;

  private Value myExpr;
  private Deque<Command> myBody;

  public If(Turtle turtle, List<Value> args, Deque<Command> body)
      throws MissingArgumentException {
    super(turtle, args, NUM_ARGS);
    verifyBody(body);

    myExpr = args.get(0);
    myBody = body;
  }

  @Override
  public Value execute() throws MissingArgumentException {
    Deque<Command> body;
    if (myExpr.equals(new Value(0))) {
      setReturnValue(0);
    }
    else {
      for (Command c: myBody) {
        c.execute();
      }
      setReturnValue(myBody.peekLast().returnValue().getVal());
    }
    return returnValue();
  }
}