package slogo.model.command.control;

import java.util.Deque;
import java.util.List;
import slogo.model.command.AbstractCommand;
import slogo.model.command.Command;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;

/**
 * Defines abstract class for any control command with an arbitrary number of args.
 *
 * @author Shaan Gondalia and Jake Heller
 */
public abstract class ControlCommand extends AbstractCommand {

  /**
   * @param args    the arguments that the command takes
   * @param numArgs the number of arguments that this command takes
   * @throws MissingArgumentException if the list of arguments does not contain enough arguments
   */
  public ControlCommand(List<Value> args, int numArgs)
      throws MissingArgumentException {
    super();
    verifyArgs(args, numArgs);
  }

  //Verifies that the command received the correct number of arguments.
  private void verifyArgs(List<Value> args, int numArgs) throws MissingArgumentException {
    if (args.size() < numArgs) {
      throw new MissingArgumentException("NOT ENOUGH ARGS");
    }
  }

  protected void verifyCommandLists(List<Deque<Command>> lists, int numCommandLists)
      throws MissingArgumentException {
    if (lists.size() < numCommandLists) {
      throw new MissingArgumentException("NOT ENOUGH LISTS");
    }
  }
}

