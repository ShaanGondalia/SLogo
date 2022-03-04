package slogo.model.command.math;

import java.util.List;
import slogo.model.command.AbstractCommand;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;

/**
 * Defines abstract class for any math operation with an arbitrary number of args.
 *
 * @author Shaan Gondalia
 */
public abstract class MathOperation extends AbstractCommand {

  /**
   * @param turtle  the Turtle that is attached to this command
   * @param args    the arguments that the command takes
   * @param numArgs the number of arguments that this command takes
   * @throws MissingArgumentException if the list of arguments does not contain enough arguments
   */
  public MathOperation(List<Value> args, int numArgs)
      throws MissingArgumentException {
    super();
    verifyArgs(args, numArgs);
  }
}

