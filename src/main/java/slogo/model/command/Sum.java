package slogo.model.command;

public class Sum implements Command {

  final Double myValue;

  public Sum(Double a, Double b) {
    myValue = a + b;
  }

  @Override
  public Double execute() {
    return myValue;
  }

  @Override
  public Double returnValue() {
    return myValue;
  }
}
