package slogo.model.command.math;

import java.util.List;
import slogo.model.turtle.Turtle;
import slogo.model.exception.MissingArgumentException;

/**
 * Class that represents a pi command. Depends on MathOperation and Turtle.
 *
 * @author Shaan Gondalia
 */
public class Pi extends MathOperation {

  private static final int NUM_ARGS = 0;

  /**
   * Creates a pi command. Takes no arguments
   *
   * @param turtle the Turtle that is attached to this command
   * @param args the arguments that the command takes (pi takes 0 args).
   * @throws MissingArgumentException if the list of arguments does not contain enough arguments
   */
  public Pi(Turtle turtle, List<Double> args) throws MissingArgumentException {
    super(turtle, args, NUM_ARGS);
  }

  /**
   * Returns pi
   *
   * @return pi
   */
  @Override
  public Double returnValue() {
    return Math.PI;
  }
}
