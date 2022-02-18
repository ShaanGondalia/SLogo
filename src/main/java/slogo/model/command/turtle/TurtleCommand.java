package slogo.model.command.turtle;

import java.util.List;
import slogo.model.Turtle;
import slogo.model.command.Command;

/**
 * Defines abstract class for any turtle command with an arbitrary number of args.
 *
 * @author Shaan Gondalia
 */
public abstract class TurtleCommand implements Command {

  private Turtle attachedTurtle;
  private List<Double> myArgs;

  /**
   * Default constructor for all Turtle Commands (Forward, Back, etc.)
   *
   * @param args the arguments for the command
   * @param turtle the Turtle that is attached to this command
   */
  public TurtleCommand(List<Double> args, Turtle turtle) {
    myArgs = args;
    attachedTurtle = turtle;
  }

  /**
   * Abstract method for when a command is executed. Implemented by subclasses.
   *
   * @return
   */
  @Override
  public abstract Double execute();

  /**
   * Abstract method for that gets the returnValue of the command. Implemented by subclasses.
   *
   * @return the return value of the command
   */
  @Override
  public abstract Double returnValue();
}
