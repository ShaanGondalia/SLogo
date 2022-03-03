package slogo.model.command.multi;

import java.util.List;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;
import slogo.model.turtle.TurtleManager;

/**
 * Class that represents a tell command. Depends on MultipleTurtleCommand, Turtle, and TurtleManager.
 *
 * @author Shaan Gondalia
 */
public class Tell extends MultipleTurtleCommand {

  private TurtleManager myTurtleManager;
  private List<Value> ids;

  /**
   * Tells turtles with given ids to follow future commands. Creates turtles if needed.
   *
   * @param args The Ids of the turtles that will follow future commands
   * @throws MissingArgumentException
   */
  public Tell(List<Value> args, TurtleManager turtleManager)
      throws MissingArgumentException {
    super(args, args.size() > 0 ? args.size() : -1);
    ids = args;
    myTurtleManager = turtleManager;
  }

  /**
   * Sets the turtles to follow the new commands
   *
   * @return the last value in the id list
   * @param turtle the turtle to execute the command on
   */
  @Override
  public Value execute(Turtle turtle) throws MissingArgumentException {
    myTurtleManager.swapFollowingIDs(ids);
    setReturnValue(ids.get(ids.size() - 1 ).getVal());
    return returnValue();
  }
}
