package slogo.model.command.math;

import java.util.List;
import slogo.model.turtle.Turtle;
import slogo.model.command.Command;
import slogo.model.exception.MissingArgumentException;

/**
 * Defines abstract class for any math operation with an arbitrary number of args.
 *
 * @author Shaan Gondalia
 */
public abstract class MathOperation implements Command {

  /**
   *
   * @param turtle the Turtle that is attached to this command
   * @param args the arguments that the command takes
   * @param numArgs the number of arguments that this command takes
   * @throws MissingArgumentException if the list of arguments does not contain enough arguments
   */
  public MathOperation(Turtle turtle, List<Double> args, int numArgs)
      throws MissingArgumentException {
    verifyArgs(args, numArgs);
  }

  /**
   * Abstract method for when an operation is executed.
   *
   * @return the return value of the operation. An operation does not have anything to execute.
   */
  @Override
  public Double execute(){
    return returnValue();
  }

  /**
   * Abstract method for that gets the returnValue of the operation. Implemented by subclasses.
   *
   * @return the return value of the operation
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

