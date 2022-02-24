package slogo.model.command;

import slogo.model.exception.MissingArgumentException;

/**
 * abstract class that formalizes how return values should be used
 *
 * @author Jake Heller
 */
public abstract class AbstractCommand implements Command {

  private Value myReturnValue;

  public AbstractCommand() {
    myReturnValue = new Value();
  }

  /**
   * Abstract method for that gets the returnValue of the command. Implemented by subclasses.
   *
   * @return the return value of the command
   */
  @Override
  public Value returnValue() {
    return myReturnValue;
  }

  protected void setReturnValue(double val) {
    myReturnValue.setVal(val);
  }
}
