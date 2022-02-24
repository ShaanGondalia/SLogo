package slogo.model.command.control;

import java.util.List;
import slogo.model.command.AbstractCommand;
import slogo.model.command.Command;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Defines abstract class for any control command with an arbitrary number of args.
 *
 * @author Shaan Gondalia
 */
public abstract class ControlCommand extends AbstractCommand {

  /**
   * @param turtle  the Turtle that is attached to this command
   * @param args    the arguments that the command takes
   * @param numArgs the number of arguments that this command takes
   * @throws MissingArgumentException if the list of arguments does not contain enough arguments
   */
  public ControlCommand(Turtle turtle, List<Value> args, int numArgs)
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
}

