package slogo.model.command.control;

import java.util.Deque;
import java.util.List;
import java.util.Map;
import slogo.model.command.Command;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Implements Repeat Command. Depends on ControlCommand
 *
 * @author Shaan Gondalia
 */
public class Repeat extends ControlCommand {

  private static final String REP_COUNT_NAME = ":repcount";
  private static final int NUM_ARGS = 1;
  private static final int NUM_LISTS = 1;

  private final Deque<Command> myBody;
  private final Value repetitions;
  private final Value repCount;

  /**
   * @param args
   * @throws MissingArgumentException
   */
  public Repeat(List<Value> args, List<Deque<Command>> lists, Map<String, Value> implicitVariables)
      throws MissingArgumentException {
    super(args, NUM_ARGS);
    verifyCommandLists(lists, NUM_LISTS);
    if (!implicitVariables.containsKey(REP_COUNT_NAME)) {
      throw new MissingArgumentException();
    }
    repCount = implicitVariables.get(REP_COUNT_NAME);
    myBody = lists.get(0);
    repetitions = args.get(0);
  }

  /**
   * @param turtle
   * @return the output of the last executed command in the loop
   * @throws MissingArgumentException
   */
  @Override
  public Value execute(Turtle turtle) throws MissingArgumentException {
    for (int i = 0; i < repetitions.getVal(); i++) {
      repCount.setVal(i + 1);
      for (Command c : myBody) {
        c.execute(turtle);
      }
    }
    setReturnValue(myBody.peekLast().returnValue().getVal());
    return returnValue();
  }
}
