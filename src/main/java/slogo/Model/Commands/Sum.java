package slogo.Model.Commands;

import slogo.Model.Numbers.Constant;
import slogo.Model.Numbers.Value;

public class Sum implements Command {

  Constant myValue;

  public Sum(Value a, Value b) {
    myValue = a.add(b);
  }

  @Override
  public Constant execute() {
    return myValue;
  }

  @Override
  public Constant returnValue() {
    return myValue;
  }
}
