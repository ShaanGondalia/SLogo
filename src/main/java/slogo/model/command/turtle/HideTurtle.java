package slogo.model.command.turtle;

import java.util.List;
import slogo.model.turtle.Turtle;
import slogo.model.exception.MissingArgumentException;

/**
 * Class that represents a hideturtle (ht) command. Depends on TurtleCommand and Turtle.
 *
 * @author Shaan Gondalia
 */
public class HideTurtle extends TurtleCommand {

  private static final int NUM_ARGS = 0;

  /**
   * Creates a hide turtle command. Takes no arguments
   * @param args the arguments for the command (no arguments for HideTurtle)
   * @param turtle the Turtle that will be rotated
   * @throws MissingArgumentException if the list of arguments does not contain enough arguments
   */
  public HideTurtle(Turtle turtle, List<Double> args) throws MissingArgumentException {
    super(turtle, args, NUM_ARGS);
  }

  /**
   * Hides the attached turtle
   *
   * @return 0
   */
  @Override
  public Double execute() {
    getTurtle().setVisibility(false);
    return returnValue();
  }

  /**
   * Returns 0
   * @return 0
   */
  @Override
  public Double returnValue() {
    return 0.0;
  }
}
