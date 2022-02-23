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

  @Override
  public Value returnValue() {
    return myReturnValue;
  }

  protected void setReturnValue(double val) {
    myReturnValue.setVal(val);
  }
}
