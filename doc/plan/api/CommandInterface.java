package slogo;

/**
 * This is the internal API for the commands in the model. It provides an interface for creating a
 * generic command. The inheritance structure will be as follows:
 *
 * public interface CommandInterface
 * public abstract class TurtleCommand implements CommandInterface
 * public abstract class TurtleQuery extends CommandInterface
 * public abstract class MathOperation extends CommandInterface
 * public abstract class BooleanOperation extends CommandInterface
 * public abstract class ControlOperation extends CommandInterface
 *
 */
public interface CommandInterface{

  /**
   * Internal - executes the command. Parameters are passed in to the constructor of each command,
   * so the execute method does not need the parameters.
   */
  public void execute();

  /**
   * Internal - Returns the double value that is associated with a command
   * @return the double value that a command returns (as specificed)
   */
  public double returnValue();
}