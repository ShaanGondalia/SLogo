package slogo.model.command;

/**
 * Generic interface for all commands. Has no dependencies.
 *
 * @author Jake Heller and Shaan Gondalia
 */
public interface Command {

  /**
   * Executes a command, returning a corresponding value
   *
   * @return the value that the command returns when executed
   */
  public Double execute();

  /**
   * Returns the return value of a command without executing it
   *
   * @return the return value of a command without execution.
   */
  public Double returnValue();
}