package slogo.model.command.turtle;

import java.util.List;
import slogo.model.command.AbstractCommand;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Defines abstract class for any turtle command with an arbitrary number of args.
 *
 * @author Shaan Gondalia
 */
public abstract class TurtleCommand extends AbstractCommand {

  /**
   * Default constructor for all Turtle Commands (Forward, Back, etc.)
   *
   * @param args    the arguments that the command takes
   * @param numArgs the number of arguments that this command takes
   * @throws MissingArgumentException if the list of arguments does not contain enough arguments
   */
  public TurtleCommand(List<Value> args, int numArgs)
      throws MissingArgumentException {
    super();
    verifyArgs(args, numArgs);
  }

  /**
   * Abstract method for when a command is executed. Implemented by subclasses.
   *
   * @return the value that is returned when the command is executed
   * @param turtle the turtle that this command executes on
   */
  @Override
  public abstract Value execute(Turtle turtle) throws MissingArgumentException;

  //Verifies that the command received the correct number of arguments.
  private void verifyArgs(List<Value> args, int numArgs) throws MissingArgumentException {
    if (args.size() < numArgs) {
      throw new MissingArgumentException("NOT ENOUGH ARGS");
    }
  }
}
