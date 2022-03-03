package slogo.model.command;

import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Generic interface for all commands. Depends on TurtleStatus.
 *
 * @author Jake Heller and Shaan Gondalia
 */
public interface Command {

  /**
   * Executes a command, returning a corresponding value
   *
   * @param turtle
   * @return the value that the command returns when executed
   */
  Value execute(Turtle turtle) throws MissingArgumentException;

  /**
   * Returns the return value of a command without executing it
   *
   * @return the return value of a command without execution.
   */
  Value returnValue();
}
