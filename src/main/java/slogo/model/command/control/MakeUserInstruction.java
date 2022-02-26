package slogo.model.command.control;

import java.util.Deque;
import java.util.List;
import slogo.model.command.Command;
import slogo.model.command.Value;
import slogo.model.command.turtle.Forward;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

public class MakeUserInstruction extends ControlCommand {

  private static final int NUM_LISTS = 1;

  private List<Value> myFormalParameters;
  private List<Value> myActualParameters;
  private Deque<Command> myMethodBody;

  public MakeUserInstruction(Turtle turtle, List<Value> variables, List<Deque<Command>> lists)
      throws MissingArgumentException {
    super(turtle, variables, variables.size());
    verifyCommandLists(lists, NUM_LISTS);

    myFormalParameters = variables;
    myMethodBody = lists.get(0);
  }

  public void setActualParameters(List<Value> inputs) throws MissingArgumentException {
    myActualParameters = inputs;
    if (myActualParameters.size() != myFormalParameters.size()) {
      throw new MissingArgumentException("Wrong number of inputs");
    }
    for (int i = 0; i < myFormalParameters.size(); i++) {
      Value formal = myFormalParameters.get(i);
      Value actual = myActualParameters.get(i);
      formal.setVal(actual.getVal());
    }
  }

  public void copyToActual() {
    for (int i = 0; i < myFormalParameters.size(); i++) {
      Value formal = myFormalParameters.get(i);
      Value actual = myActualParameters.get(i);
      actual.setVal(formal.getVal());
    }
  }

  @Override
  public Value execute() throws MissingArgumentException {
    if (myActualParameters == null) {
      throw new MissingArgumentException("No arguments specified");
    }
    for (Command c : myMethodBody) {
      c.execute();
    }
    copyToActual();
    myActualParameters = null;
    setReturnValue(myMethodBody.peekLast().returnValue().getVal());
    return returnValue();
  }
}
