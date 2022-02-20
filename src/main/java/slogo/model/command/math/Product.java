package slogo.model.command.math;

import java.util.List;
import slogo.model.turtle.Turtle;
import slogo.model.exception.MissingArgumentException;

/**
 * Class that represents a product (*) command. Depends on MathOperation and Turtle.
 *
 * @author Shaan Gondalia
 */
public class Product extends MathOperation {

  private final Double product;
  private static final int NUM_ARGS = 2;

  /**
   * Creates a product command
   *
   * @param turtle the Turtle that is attached to this command
   * @param args the arguments that the command takes
   * @throws MissingArgumentException if the list of arguments does not contain enough arguments
   */
  public Product(Turtle turtle, List<Double> args) throws MissingArgumentException {
    super(turtle, args, NUM_ARGS);
    product = args.get(0) * args.get(1);
  }

  /**
   * Gets the returnValue of the operation.
   *
   * @return arg1 * arg2
   */
  @Override
  public Double returnValue() {
    return product;
  }
}
