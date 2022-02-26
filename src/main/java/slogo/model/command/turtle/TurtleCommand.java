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

  private Turtle attachedTurtle;

  /**
   * Default constructor for all Turtle Commands (Forward, Back, etc.)
   *
   * @param turtle  the Turtle that is attached to this command
   * @param args    the arguments that the command takes
   * @param numArgs the number of arguments that this command takes
   * @throws MissingArgumentException if the list of arguments does not contain enough arguments
   */
  public TurtleCommand(Turtle turtle, List<Value> args, int numArgs)
      throws MissingArgumentException {
    super();
    attachedTurtle = turtle;
    verifyArgs(args, numArgs);
  }

  /**
   * Method for giving subclasses access to the attached Turtle
   *
   * @return the attached Turtle
   */
  protected Turtle getTurtle() {
    return attachedTurtle;
  }

  /**
   * Abstract method for when a command is executed. Implemented by subclasses.
   *
   * @return the value that is returned when the command is executed
   */
  @Override
  public abstract Value execute() throws MissingArgumentException;

  //Verifies that the command received the correct number of arguments.
  private void verifyArgs(List<Value> args, int numArgs) throws MissingArgumentException {
    if (args.size() < numArgs) {
      throw new MissingArgumentException("NOT ENOUGH ARGS");
    }
  }
}
