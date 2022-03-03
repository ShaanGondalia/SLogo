package slogo.model.command.multi;

import java.util.Deque;
import java.util.List;
import slogo.model.command.Command;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;
import slogo.model.turtle.TurtleManager;

/**
 * Class that represents an ask with command. Depends on MultipleTurtleCommand, Turtle, and
 * TurtleManager.
 *
 * @author Shaan Gondalia
 */
public class AskWith extends MultipleTurtleCommand {

  private static final int NUM_ARGS = 0;
  private static final int NUM_LISTS = 2;

  private final TurtleManager myTurtleManager;
  private final Deque<Command> condition;
  private final Deque<Command> myBody;

  /**
   * Asks turtles with given ids to follow commands in the body. Creates turtles if needed.
   *
   * @param args The Ids of the turtles that will the commands in the body
   * @throws MissingArgumentException
   */
  public AskWith(List<Value> args, List<Deque<Command>> lists, TurtleManager turtleManager)
      throws MissingArgumentException {
    super(args, NUM_ARGS);
    verifyCommandLists(lists, NUM_LISTS);
    condition = lists.get(0);
    myBody = lists.get(1);
    myTurtleManager = turtleManager;
  }

  /**
   * Tells the turtles given in the arguments to follow the commands in the body.
   *
   * @param turtle the turtle to execute the command on (this command ignores this turtle)
   * @return the return value of the last command that was run
   */
  @Override
  public Value execute(Turtle turtle) throws MissingArgumentException {
    myTurtleManager.executeConditionally(condition, myBody);
    setReturnValue(myBody.peekLast().returnValue().getVal());
    return returnValue();
  }
}
