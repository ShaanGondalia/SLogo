package slogo.model.command.multi;

import java.util.List;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;
import slogo.model.turtle.TurtleManager;

/**
 * Class that represents an id command. Depends on MultipleTurtleCommand, Turtle, and
 * TurtleManager.
 *
 * @author Shaan Gondalia
 */
public class ID extends MultipleTurtleCommand {

  private static final int NUM_ARGS = 0;
  private final TurtleManager myTurtleManager;

  /**
   * Makes an id command, which returns the id of the active turtle
   *
   * @param args          No args are required for this command
   * @param turtleManager The turtle manager that manages the active and following turtles
   * @throws MissingArgumentException
   */
  public ID(List<Value> args, TurtleManager turtleManager)
      throws MissingArgumentException {
    super(args, NUM_ARGS);
    myTurtleManager = turtleManager;
  }

  /**
   * Returns the id of the active turtle
   *
   * @param turtle the turtle to execute the command on
   * @return the id of the active turtle
   */
  @Override
  public Value execute(Turtle turtle) throws MissingArgumentException {
    setReturnValue(myTurtleManager.getActiveTurtleID());
    return returnValue();
  }
}
