package slogo.model.command.control;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import slogo.model.command.Command;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * @author Jake Heller
 */
public class DoTimes extends ControlCommand {

  private static final int NUM_ARGS = 2;
  private static final int NUM_LISTS = 1;
  private Command myFor;

  public DoTimes(List<Value> args, List<Deque<Command>> lists)
      throws MissingArgumentException {
    super(args, NUM_ARGS);
    verifyCommandLists(lists, NUM_LISTS);

    Value start = new Value(1);
    Value end = new Value(
        args.get(1).getVal() + 0.5); // 0.5 to ensure that loop gets run correct number of times
    Value increment = new Value(1);

    List<Value> forArgs = new ArrayList<>();
    forArgs.add(args.get(0));
    forArgs.add(start);
    forArgs.add(end);
    forArgs.add(increment);

    myFor = new For(forArgs, lists);
  }

  /**
   * @return value of last command run
   * @throws MissingArgumentException
   * @param turtle
   */
  @Override
  public Value execute(Turtle turtle) throws MissingArgumentException {
    myFor.execute(turtle);
    setReturnValue(myFor.returnValue().getVal());
    return returnValue();
  }
}
