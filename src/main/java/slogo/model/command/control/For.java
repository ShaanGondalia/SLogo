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

  private static final int NUM_ARGS = 4;
  private static final int NUM_LISTS = 1;
  private static final int START_POS = 1;
  private static final int END_POS = 2;
  private static final int INCREMENT_POS = 3;

  private final Deque<Command> myBody;
  private final Value myVariable;
  private final double myStart;
  private final double myEnd;
  private final double myIncrement;

  /**
   * @param args
   * @throws MissingArgumentException
   */
  public For(List<Value> args, List<Deque<Command>> lists)
      throws MissingArgumentException {
    super(args, NUM_ARGS);
    verifyCommandLists(lists, NUM_LISTS);

    myBody = lists.get(0);
    myVariable = args.get(0);
    myStart = args.get(START_POS).getVal();
    myEnd = args.get(END_POS).getVal();
    myIncrement = args.get(INCREMENT_POS).getVal();
  }

  /**
   * @param turtle
   * @return the output of the last executed command in the loop
   * @throws MissingArgumentException
   */
  @Override
  public Value execute(Turtle turtle) throws MissingArgumentException {
    myVariable.setVal(myStart);

    while (myVariable.getVal() < myEnd && (Math.abs(myVariable.getVal() - myEnd)
        > Main.TOLERANCE)) {
      for (Command c : myBody) {
        c.execute(turtle);
      }
      myVariable.setVal(myVariable.getVal() + myIncrement);
    }
    setReturnValue(myBody.peekLast().returnValue().getVal());
    return returnValue();
  }
}
