package slogo.model.command.bool;

import java.util.List;
import slogo.model.turtle.Turtle;
import slogo.model.exception.MissingArgumentException;

/**
 * Class that represents a greater command. Depends on BooleanOperation.
 *
 * @author Shaan Gondalia
 */
public class Greater extends BooleanOperation {

  private final Double greater;
  private static final int NUM_ARGS = 2;

  /**
   * Creates a greater command
   *
   * @param turtle the Turtle that is attached to this command
   * @param args the arguments that the command takes
   * @throws MissingArgumentException if the list of arguments does not contain enough arguments
   */
  public Greater(Turtle turtle, List<Double> args) throws MissingArgumentException {
    super(turtle, args, NUM_ARGS);
    System.out.println(args.get(0));
    System.out.println(args.get(1));
    greater = args.get(0) > args.get(1) ? 1.0 : 0.0;
  }

  @Override
  public Double returnValue() {
    return greater;
  }
}
