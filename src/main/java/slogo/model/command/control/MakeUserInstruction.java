package slogo.model.command.control;

import java.util.Deque;
import java.util.List;
import slogo.model.command.Command;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

public class MakeUserInstruction extends ControlCommand {

  private static final int NUM_LISTS = 1;

  private final List<Value> myFormalParameters;
  private final List<Deque<Command>> bodies;


  public MakeUserInstruction(List<Value> variables, List<Deque<Command>> lists)
      throws MissingArgumentException {
    super(variables, variables.size());
    verifyCommandLists(lists, NUM_LISTS);

    myFormalParameters = variables;
    bodies = lists;
  }

  public Command getUserCommand(List<Value> inputs) throws MissingArgumentException {
    UserCommand userCommand = new UserCommand(myFormalParameters, bodies);
    userCommand.setActualParameters(inputs);
    return userCommand;
  }

  @Override
  public Value execute(Turtle turtle) throws MissingArgumentException {
    setReturnValue(1);
    return returnValue();
  }
}
