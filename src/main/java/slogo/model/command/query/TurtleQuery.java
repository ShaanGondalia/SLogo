package slogo.model.command.query;

import java.util.List;
import slogo.model.turtle.Turtle;
import slogo.model.command.Command;
import slogo.model.exception.MissingArgumentException;

/**
 * Defines abstract class for any turtle query with an arbitrary number of args.
 *
 * @author Shaan Gondalia
 */
public abstract class TurtleQuery implements Command {

  private final Turtle attachedTurtle;

  /**
   * Default constructor for all Turtle Queries
   *
   * @param turtle the Turtle that is attached to this command
   * @param args the arguments that the command takes
   * @param numArgs the number of arguments that this command takes
   * @throws MissingArgumentException if the list of arguments does not contain enough arguments
   */
  public TurtleQuery(Turtle turtle, List<Double> args, int numArgs) throws MissingArgumentException {
    attachedTurtle = turtle;
    verifyArgs(args, numArgs);
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

  //Verifies that the command received the correct number of arguments.
  private void verifyArgs(List<Double> args, int numArgs) throws MissingArgumentException {
    if(args.size() < numArgs){
      throw new MissingArgumentException("NOT ENOUGH ARGS");
    }
  }
}
