package slogo.model.command.turtle;

import java.util.List;
import slogo.model.command.Value;
import slogo.model.command.display.SetPenColor;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.PenState;
import slogo.model.turtle.Turtle;

public class SetPenSize extends TurtleCommand {

  public static final int NUM_ARGS = 1;
  private Value myPenSize;

  public SetPenSize(List<Value> args) throws MissingArgumentException {
    super(args, NUM_ARGS);
    myPenSize = args.get(0);
  }

  @Override
  public Value execute(Turtle turtle) throws MissingArgumentException {
    PenState current = turtle.getStatus().penState();
    PenState newState = new PenState(current.penDown(), current.color(), myPenSize.getVal());
    turtle.setPen(newState);
    setReturnValue(myPenSize.getVal());
    return returnValue();
  }
}
