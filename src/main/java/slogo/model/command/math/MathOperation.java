package slogo.model.command.math;

import java.util.List;
import slogo.model.Turtle;
import slogo.model.command.Command;

/**
 * Defines abstract class for any math operation with an arbitrary number of args.
 *
 * @author Shaan Gondalia
 */
public abstract class MathOperation implements Command {

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
}

