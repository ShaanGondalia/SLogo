package slogo.model.command.math;

import java.util.List;

/**
 * Class that represents a sum (+) command. Depends on MathOperation.
 *
 * @author Shaan Gondalia
 */
public class Sum extends MathOperation {

  private final Double sum;

  /**
   * Creates a sum command
   * @param args the arguments for the command (single argument for sum)
   */
  public Sum(List<Double> args) {
    sum = args.get(0) + args.get(1);
  }

  @Override
  public Double returnValue() {
    return sum;
  }
}
