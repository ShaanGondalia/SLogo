package slogo.model.command;

import java.util.List;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Defines an extended syntax command, which is a command that can take any number of parameters.
 * Works by taking a command and executing it once for each set of arguments parsed by the compiler.
 * The return value is the sum of each execution of the command. Depends on Command, Value, and
 * AbstractCommand.
 *
 * @author Shaan Gondalia
 */
public class ExtendedSyntaxCommand extends AbstractCommand {

  private final Command myCommand;
  private final List<Value> myArgs;
  private final int argsPerCommand;

  /**
   * Creates new extended syntax command.
   *
   * @param args The complete list of arguments parsed by the compiler
   * @param command The command that will be executed multiple times
   * @param commandArgs The number of arguments that the command normally takes.
   */
  public ExtendedSyntaxCommand(List<Value> args, Command command, int commandArgs)
      throws MissingArgumentException {
    super();
    argsPerCommand = commandArgs;
    myCommand = command;
    myArgs = args;
    if (!validInputCount()) {
      throw new MissingArgumentException();
    }
  }

  /**
   * Executes the extended syntax command. Returns the sum of all return values of the original
   * command.
   *
   * @param turtle The turtle to execute the command on
   * @return the sum of each execution of the command
   * @throws MissingArgumentException if there are not enough arguments to the command
   */
  @Override
  public Value execute(Turtle turtle) throws MissingArgumentException {
    double sumOfExecutions = 0;
    int numExecutions = myArgs.size() / argsPerCommand;
    for (int i = 0; i < numExecutions; i++) {
      for (int j = 0; j < argsPerCommand; j++) {
        myArgs.get(j).setVal(myArgs.get(argsPerCommand * i + j).getVal());
      }
      sumOfExecutions += myCommand.execute(turtle).getVal();
    }
    setReturnValue(sumOfExecutions);
    return returnValue();
  }

  private boolean validInputCount() {
    return myArgs.size() % argsPerCommand == 0;
  }

}
