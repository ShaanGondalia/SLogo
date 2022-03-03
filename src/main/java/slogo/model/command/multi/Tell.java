package slogo.model.command.multi;

import java.util.Deque;
import java.util.List;
import slogo.Main;
import slogo.model.command.Command;
import slogo.model.command.Value;
import slogo.model.command.multi.MultipleTurtleCommand;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;
import slogo.model.turtle.TurtleManager;

/**
 * Class that represents a tell command. Depends on MultipleTurtleCommand, Turtle, and TurtleManager.
 *
 * @author Jake Heller
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
    super(args, args.size());
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
    myTurtleManager.setFollowingIDs(ids);
    setReturnValue(ids.get(ids.size() - 1 ).getVal());
    return returnValue();
  }
}
