package slogo.model.command.control;

import java.util.Deque;
import java.util.List;
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

  private static final int NUM_ARGS = 1;
  private static final int NUM_LISTS = 1;

  private Deque<Command> myBody;
  private Value repetitions;

  /**
   * @param args
   * @throws MissingArgumentException
   */
  public Repeat(List<Value> args, List<Deque<Command>> lists)
      throws MissingArgumentException {
    super(args, NUM_ARGS);
    verifyCommandLists(lists, NUM_LISTS);
    //TODO: Figure out how to do :repcount incrementing

    myBody = lists.get(0);
    repetitions = args.get(0);
  }

  /**
   * @return the output of the last executed command in the loop
   * @throws MissingArgumentException
   * @param turtle
   */
  @Override
  public Value execute(Turtle turtle) throws MissingArgumentException {
    for (int i = 0; i < repetitions.getVal(); i++) {
      for (Command c : myBody) {
        c.execute(turtle);
      }
    }
    setReturnValue(myBody.peekLast().returnValue().getVal());
    return returnValue();
  }
}
