package slogo.model.compiler;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;
import slogo.model.command.Command;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.exception.SymbolNotFoundException;

/**
 * Class that represents a single context of a program. A new context is defined every time the
 * compiler encounters a new list. Contexts exist because lists need to be separated from each
 * other.
 *
 * @author Shaan Gondalia
 */
public class Context {

  private final Stack<String> pendingCommands;
  private final Deque<Command> resolvedCommands;
  private final Deque<Deque<Command>> resolvedCommandQueues;
  private final Stack<Value> values;
  private final Stack<Deque<Command>> lists;
  private final Stack<Integer> valuesBefore;
  private final Stack<Integer> listsBefore;

  /**
   * Creates a new context
   */
  public Context() {
    pendingCommands = new Stack<>();
    resolvedCommands = new LinkedList<>();
    resolvedCommandQueues = new LinkedList<>();
    values = new Stack<>();
    lists = new Stack<>();
    valuesBefore = new Stack<>();
    listsBefore = new Stack<>();
  }

  /**
   * Returns a queue of resolved commands
   *
   * @return a queue of resolved commands
   */
  public Deque<Command> getResolvedCommands() {
    return resolvedCommands;
  }

  /**
   * Returns the value stack
   *
   * @return the value stack
   */
  public Stack<Value> getValues() {
    return values;
  }

  /**
   * Resolves a command in the given context
   *
   * @param commandFactory the command factory that will build the command
   * @param numInputs      the number of inputs for the command
   */
  public void resolveCommand(CommandFactory commandFactory, int numInputs)
      throws MissingArgumentException, SymbolNotFoundException {
    String pendingCommand = pendingCommands.pop();
    valuesBefore.pop();
    listsBefore.pop();
    Command command = commandFactory.getCommand(pendingCommand, values, lists, numInputs);
    values.add(command.returnValue());
    if (!lists.empty()) {
      lists.peek().addLast(command);
    } else {
      resolvedCommands.addLast(command);
    }
    if (pendingCommands.isEmpty()) {
      values.clear();
      resolvedCommands.add(null);
    }
  }

  /**
   * Returns a queue of queues of commands. Each inner queue represents a chunk of commands that oes
   * not rely on any other commands
   *
   * @return a queue of queues of resolved commands.
   */
  public Deque<Deque<Command>> constructResolvedCommandQueues() {
    Deque<Deque<Command>> resolvedCommandQueues = new LinkedList<>();
    resolvedCommandQueues.add(new LinkedList<>());
    for (Command c : getResolvedCommands()) {
      // null delimits completed blocks of commands
      if (c == null) {
        resolvedCommandQueues.add(new LinkedList<>());
      } else {
        resolvedCommandQueues.peekLast().add(c);
      }
    }
    if (resolvedCommandQueues.peekLast().isEmpty()) {
      resolvedCommandQueues.removeLast();
    }

    return resolvedCommandQueues;
  }

  /**
   * Returns the pending command, or null if pendingCommands is empty
   *
   * @return the pending command, or null if pendingCommands is empty
   */
  public String pendingCommand() {
    if (pendingCommands.empty()) {
      return null;
    }
    return pendingCommands.peek();
  }

  /**
   * Finds the number of new values in between a command's resolution
   *
   * @return the number of new values in between a command's resolution
   */
  public int numberNewValues() {
    return values.size() - valuesBefore.peek();
  }

  /**
   * Finds the number of new values in between a command's resolution
   *
   * @return the number of new values in between a command's resolution
   */
  public int numberNewLists() {
    return lists.size() - listsBefore.peek();
  }

  /**
   * Handles the addition of a pending command to the pending commands list
   *
   * @param symbol The symbol of the command
   */
  public void addPendingCommand(String symbol) {
    pendingCommands.push(symbol);
    valuesBefore.push(values.size());
    listsBefore.push(lists.size());
  }

  /**
   * Adds a value to the value stack
   *
   * @param val the value to add to the value stack
   */
  public void addValue(Value val) {
    values.push(val);
  }

  /**
   * Resolves the context with respect to the previous context
   *
   * @param previousContext the previous context that we wish to compare to
   */
  public void resolve(Context previousContext) {
    if (previousContext.getResolvedCommands().isEmpty()) {
      values.addAll(previousContext.getValues());
    } else {
      addResolvedCommands(previousContext);
    }
  }

  // Add resolved commands of a list to lists in outer context
  private void addResolvedCommands(Context previousContext) {
    Deque<Command> commandDeque = new LinkedList<>();
    for (Command c : previousContext.getResolvedCommands()) {
      // Removes null commands that are inserted to delimit resolved commands
      if (c != null) {
        commandDeque.add(c);
      }
    }
    lists.push(commandDeque);
  }

}
