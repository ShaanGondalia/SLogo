package slogo.model.command.turtle;

import java.util.List;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.PenState;
import slogo.model.turtle.Turtle;

/**
 * Class that represents a penup (pu) command. Depends on TurtleCommand and Turtle.
 *
 * @author Shaan Gondalia
 */
public class PenUp extends TurtleCommand {

  private static final int NUM_ARGS = 0;

  /**
   * Creates a pen up command. Takes no arguments
   *
   * @param args the arguments for the command (no arguments for PenUp)
   * @throws MissingArgumentException if the list of arguments does not contain enough arguments
   */
  public PenUp(List<Value> args) throws MissingArgumentException {
    super(args, NUM_ARGS);
    setReturnValue(0.0);
  }

  /**
   * Puts the attached turtle's pen up
   *
   * @param turtle the Turtle that will be rotated
   * @return 0
   */
  @Override
  public Value execute(Turtle turtle) {
    PenState current = turtle.getStatus().penState();
    PenState newState = new PenState(false, current.color(), current.thickness());
    turtle.setPen(newState);
    return returnValue();
  }
}
