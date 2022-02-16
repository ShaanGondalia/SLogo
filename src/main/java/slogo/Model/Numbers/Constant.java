package slogo.Model.Numbers;

import slogo.Model.Numbers.Number;

public class Constant implements Number {

  private final double myValue;

  public Constant(double value) {
    myValue = value;
  }
  @Override
  public double getValue() {
    return myValue;
  }
}
