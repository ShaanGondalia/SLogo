package slogo.model.command.multi;

import java.util.Deque;
import java.util.List;
import slogo.model.command.Command;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;
import slogo.model.turtle.TurtleManager;

/**
 * Class that represents a ask command. Depends on MultipleTurtleCommand, Turtle, and TurtleManager.
 *
 * @author Shaan Gondalia
 */
public class Ask extends MultipleTurtleCommand {

  private static final int NUM_LISTS = 1;

  private TurtleManager myTurtleManager;
  private List<Value> ids;
  private Deque<Command> myBody;

  /**
   * Asks turtles with given ids to follow commands in the body. Creates turtles if needed.
   *
   * @param args The Ids of the turtles that will the commands in the body
   * @throws MissingArgumentException
   */
  public Ask(List<Value> args, List<Deque<Command>> lists, TurtleManager turtleManager)
      throws MissingArgumentException {
    super(args, args.size() > 0 ? args.size() : -1);
    verifyCommandLists(lists, NUM_LISTS);
    myBody = lists.get(0);
    ids = args;
    myTurtleManager = turtleManager;
  }

  /**
   * Tells the turtles given in the arguments to follow the commands in the body.
   *
   * @return the return value of the last command that was run
   * @param turtle the turtle to execute the command on (this command ignores this turtle)
   */
  @Override
  public Value execute(Turtle turtle) throws MissingArgumentException {
    List<Value> oldIds = myTurtleManager.swapFollowingIDs(ids);
    myTurtleManager.executeCommandQueue(myBody);
    myTurtleManager.swapFollowingIDs(oldIds);
    setReturnValue(myBody.peekLast().returnValue().getVal());
    return returnValue();
  }
}
