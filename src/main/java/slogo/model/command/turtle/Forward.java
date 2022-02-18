package slogo.model.command.turtle;

import java.util.List;
import slogo.model.Turtle;

/**
 * Class that represents a forward (fd) command. Depends on TurtleCommand and Turtle.
 *
 * @author Shaan Gondalia
 */
public class Forward extends TurtleCommand {

  double pixels;
  /**
   * Creates a forward command
   * @param args the arguments for the command (single argument for FD)
   * @param turtle the Turtle that will be moved when the command is executed
   */
  public Forward(List<Double> args, Turtle turtle){
    super(turtle);
    pixels = args.get(0);
  }

  /**
   * Moves the attached turtle forward given the arguments
   * @return
   */
  @Override
  public Double execute() {
    getTurtle().move(pixels);
    return pixels;
  }

  @Override
  public Double returnValue() {
    return pixels;
  }
}
