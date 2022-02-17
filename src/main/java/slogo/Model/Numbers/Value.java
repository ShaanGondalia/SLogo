package slogo.Model.Numbers;

public abstract class Value {

  public abstract double getValue();

  public Constant add(Value a) {
    return new Constant(this.getValue() + a.getValue());
  }

  public Constant subtract(Value a) {
    return new Constant(this.getValue() - a.getValue());
  }

  public Constant multiply(Value a) {
    return new Constant(this.getValue() * a.getValue());
  }

  public Constant divide(Value a) {
    return new Constant(this.getValue() / a.getValue());
  }
}
