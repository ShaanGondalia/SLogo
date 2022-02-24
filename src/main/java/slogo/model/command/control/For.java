package slogo.model.command.control;

import java.util.Deque;
import java.util.List;
import slogo.model.command.Command;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

public class For extends ControlCommand {

  private static int NUM_ARGS = 4;

  private Deque<Command> myBody;
  private Value myVariable;

  public For(Turtle turtle, List<Value> args, Deque<Command> body) throws MissingArgumentException {
    super(turtle, args, NUM_ARGS);

  }

  @Override
  public Value execute() throws MissingArgumentException {
    return null;
  }

  @Override
  public Value returnValue() {
    return myVariable;
  }
}
