package slogo.model.command.query;

import slogo.model.Turtle;
import slogo.model.command.Command;

/**
 * Defines abstract class for any turtle query with an arbitrary number of args.
 *
 * @author Shaan Gondalia
 */
public abstract class TurtleQuery implements Command {

  private Turtle attachedTurtle;

  /**
   * Default constructor for all Turtle Queries
   *
   * @param turtle the Turtle that is attached to this query
   */
  public TurtleQuery(Turtle turtle) {
    attachedTurtle = turtle;
  }

  /**
   * Method for giving subclasses access to the attached Turtle
   *
   * @return the attached Turtle
   */
  protected Turtle getTurtle() {
    return attachedTurtle;
  }

  /**
   * Method for when a query is executed.
   *
   * @return the return value of the query.
   */
  @Override
  public Double execute(){
    return returnValue();
  }

  /**
   * Abstract method for that gets the returnValue of the command. Implemented by subclasses.
   *
   * @return the return value of the query
   */
  @Override
  public abstract Double returnValue();
}
