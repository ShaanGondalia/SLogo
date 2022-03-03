package slogo.model.command.multi;

import java.util.Deque;
import java.util.List;
import slogo.model.command.AbstractCommand;
import slogo.model.command.Command;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;

/**
 * Defines abstract class for any multiple turtle command with an arbitrary number of args.
 *
 * @author Shaan Gondalia
 */
public abstract class MultipleTurtleCommand extends AbstractCommand {

  /**
   * @param args    the arguments that the command takes
   * @param numArgs the number of arguments that this command takes
   * @throws MissingArgumentException if the list of arguments does not contain enough arguments
   */
  public MultipleTurtleCommand(List<Value> args, int numArgs)
      throws MissingArgumentException {
    super();
    verifyArgs(args, numArgs);
  }

  //Verifies that the command received the correct number of arguments.
  private void verifyArgs(List<Value> args, int numArgs) throws MissingArgumentException {
    if (args.size() < numArgs || numArgs == -1) {
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