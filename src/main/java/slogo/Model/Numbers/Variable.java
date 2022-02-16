package slogo.Model.Numbers;

import slogo.Model.Numbers.Number;

public class Variable implements Number {

  private double myValue;

  public Variable(double value) {
    myValue = value;
  }

  @Override
  public double getValue() {
    return myValue;
  }

  public void setValue(double value) {
    myValue = value;
  }
}
