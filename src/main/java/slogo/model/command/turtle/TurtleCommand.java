package slogo.model.command.turtle;

import java.util.List;
import slogo.model.Turtle;
import slogo.model.command.Command;
import slogo.model.exception.MissingArgumentException;

/**
 * Defines abstract class for any turtle command with an arbitrary number of args.
 *
 * @author Shaan Gondalia
 */
public abstract class TurtleCommand implements Command {

  private Turtle attachedTurtle;

  /**
   * Default constructor for all Turtle Commands (Forward, Back, etc.)
   *
   * @param turtle the Turtle that is attached to this command
   */
  public TurtleCommand(Turtle turtle, List<Double> args, int numArgs)
      throws MissingArgumentException {
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
   * @return
   */
  @Override
  public abstract Double execute();

  /**
   * Abstract method for that gets the returnValue of the command. Implemented by subclasses.
   *
   * @return the return value of the command
   */
  @Override
  public abstract Double returnValue();

  //Verifies that the command received the correct number of arguments.
  private void verifyArgs(List<Double> args, int numArgs) throws MissingArgumentException {
    if(args.size() < numArgs){
      throw new MissingArgumentException("NOT ENOUGH ARGS");
    }
  }
}
