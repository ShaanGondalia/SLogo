package slogo.Model.Numbers;

public class Variable extends Value {

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
