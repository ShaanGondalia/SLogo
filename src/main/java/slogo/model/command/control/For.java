package slogo.model.command.control;

import java.util.Deque;
import java.util.List;
import slogo.Main;
import slogo.model.command.Command;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Implements For Command
 *
 * @author Jake Heller
 */
public class For extends ControlCommand {

  private static int NUM_ARGS = 4;

  private Deque<Command> myBody;
  private Value myVariable;
  private double myStart;
  private double myEnd;
  private double myIncrement;

  /**
   * @param turtle
   * @param args
   * @param body
   * @throws MissingArgumentException
   */
  public For(Turtle turtle, List<Value> args, Deque<Command> body) throws MissingArgumentException {
    super(turtle, args, NUM_ARGS);
    verifyBody(body);

    myBody = body;
    myVariable = args.get(0);
    myStart = args.get(1).getVal();
    myEnd = args.get(2).getVal();
    myIncrement = args.get(3).getVal();
  }

  /**
   *
   * @return the output of the last executed command in the loop
   * @throws MissingArgumentException
   */
  @Override
  public Value execute() throws MissingArgumentException {
    myVariable.setVal(myStart);
    while (myVariable.getVal() < myEnd && (Math.abs(myVariable.getVal() - myEnd) > Main.TOLERANCE)) {
      for (Command c: myBody) {
        c.execute();
      }
      myVariable.setVal(myVariable.getVal() + myIncrement);
    }
    setReturnValue(myBody.peekLast().returnValue().getVal());
    return returnValue();
  }
}
