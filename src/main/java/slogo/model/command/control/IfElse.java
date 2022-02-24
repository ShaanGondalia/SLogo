package slogo.model.command.control;

import java.util.Deque;
import java.util.List;
import slogo.model.command.Command;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

public class IfElse extends ControlCommand {

  private static int NUM_ARGS = 1;

  private Value myExpr;
  private Deque<Command> myTrueBody;
  private Deque<Command> myFalseBody;

  public IfElse(Turtle turtle, List<Value> args, Deque<Command> trueBody, Deque<Command> falseBody)
      throws MissingArgumentException {
    super(turtle, args, NUM_ARGS);
    verifyBody(trueBody);
    verifyBody(falseBody);

    myExpr = args.get(0);
    myTrueBody = trueBody;
    myFalseBody = falseBody;
  }

  @Override
  public Value execute() throws MissingArgumentException {
    Deque<Command> body;
    if (myExpr.equals(new Value(0))) {
      body = myFalseBody;
    }
    else {
      body = myTrueBody;
    }
    for (Command c: body) {
      c.execute();
    }
    setReturnValue(body.peekLast().returnValue().getVal());
    return returnValue();
  }
}
