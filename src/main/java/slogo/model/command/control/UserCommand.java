package slogo.model.command.control;

import java.util.Deque;
import java.util.List;
import slogo.model.command.Command;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

public class UserCommand extends ControlCommand {

  private static final int NUM_LISTS = 1;

  private final List<Value> myFormalParameters;
  private List<Value> myActualParameters;
  private final Deque<Command> myMethodBody;

  public UserCommand(List<Value> variables, List<Deque<Command>> lists)
      throws MissingArgumentException {
    super(variables, variables.size());
    verifyCommandLists(lists, NUM_LISTS);

    myFormalParameters = variables;
    myMethodBody = lists.get(0);
  }

  public void setActualParameters(List<Value> inputs) throws MissingArgumentException {
    myActualParameters = inputs;
    if (myActualParameters.size() != myFormalParameters.size()) {
      throw new MissingArgumentException();
    }
  }

  private void copyToActual() {
    for (int i = 0; i < myFormalParameters.size(); i++) {
      Value formal = myFormalParameters.get(i);
      Value actual = myActualParameters.get(i);
      actual.setVal(formal.getVal());
    }
  }

  private void copyActualToFormal() {
    for (int i = 0; i < myFormalParameters.size(); i++) {
      Value formal = myFormalParameters.get(i);
      Value actual = myActualParameters.get(i);
      formal.setVal(actual.getVal());
    }
  }

  @Override
  public Value execute(Turtle turtle) throws MissingArgumentException {
    copyActualToFormal();
    if (myActualParameters == null) {
      throw new MissingArgumentException();
    }
    for (Command c : myMethodBody) {
      c.execute(turtle);
    }
    copyToActual();
    setReturnValue(myMethodBody.peekLast().returnValue().getVal());
    return returnValue();
  }


}
