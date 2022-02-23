package slogo.model.command.control;

import java.util.List;
import slogo.model.command.Command;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Defines abstract class for any control command with an arbitrary number of args.
 *
 * @author Shaan Gondalia
 */
public abstract class ControlCommand implements Command {

  /**
   * @param turtle  the Turtle that is attached to this command
   * @param args    the arguments that the command takes
   * @param numArgs the number of arguments that this command takes
   * @throws MissingArgumentException if the list of arguments does not contain enough arguments
   */
  public ControlCommand(Turtle turtle, List<Value> args, int numArgs)
      throws MissingArgumentException {
    verifyArgs(args, numArgs);
  }

  /**
   * Abstract method for when the command is executed.
   *
   * @return the return value of the command.
   */
  @Override
  public abstract Value execute() throws MissingArgumentException;

  /**
   * Abstract method that gets the returnValue of the command. Implemented by subclasses.
   *
   * @return the return value of the command
   */
  @Override
  public abstract Value returnValue();

  //Verifies that the command received the correct number of arguments.
  private void verifyArgs(List<Value> args, int numArgs) throws MissingArgumentException {
    if (args.size() < numArgs) {
      throw new MissingArgumentException("NOT ENOUGH ARGS");
    }
  }
}

