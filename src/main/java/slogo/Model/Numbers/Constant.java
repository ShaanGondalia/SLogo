package slogo.Model.Numbers;

public class Constant extends Value {

  private final double myValue;

  public Constant(double value) {
    myValue = value;
  }

  @Override
  public double getValue() {
    return myValue;
  }
}
