package slogo.Model.Commands;

import slogo.Model.Numbers.Constant;

public interface Command {

  public Constant execute();

  public Constant returnValue();
}
