package slogo.model.command.query;

import java.util.List;
import slogo.model.command.AbstractCommand;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;

/**
 * Defines abstract class for any turtle query with an arbitrary number of args.
 *
 * @author Shaan Gondalia
 */
public abstract class TurtleQuery extends AbstractCommand {

  /**
   * Default constructor for all Turtle Queries
   *
   * @param args    the arguments that the command takes
   * @param numArgs the number of arguments that this command takes
   * @throws MissingArgumentException if the list of arguments does not contain enough arguments
   */
  public TurtleQuery(List<Value> args, int numArgs)
      throws MissingArgumentException {
    super();
    verifyArgs(args, numArgs);
  }
}
