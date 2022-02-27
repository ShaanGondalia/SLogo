package slogo.model.compiler;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;
import slogo.model.command.Command;
import slogo.model.command.Value;

/**
 * Class that represents a single context of a program. A new context is defined every time the
 * compiler encounters a new list. Contexts exist because lists need to be separated from each other.
 * 
 * @author Shaan Gondalia
 */
public class Context {
  private final Stack<String> pendingCommands;
  private final Deque<Command> resolvedCommands;
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
    values = new Stack<>();
    lists = new Stack<>();
    valuesBefore = new Stack<>();
    listsBefore = new Stack<>();
  }

  /**
   * Returns a stack of pending command symbols
   *
   * @return a stack of pending command symbols
   */
  public Stack<String> getPendingCommands() {
    return pendingCommands;
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
   * Returns the list stack
   *
   * @return the list stack
   */
  public Stack<Deque<Command>> getLists() {
    return lists;
  }

  /**
   * Returns the number of values that exist before a command is parsed
   *
   * @return the number of values that exist before a command is parsed
   */
  public Stack<Integer> getValuesBefore() {
    return valuesBefore;
  }

  /**
   * Returns the number of lists that exist before a command is parsed
   *
   * @return the number of lists that exist before a command is parsed
   */
  public Stack<Integer> getListsBefore() {
    return listsBefore;
  }

  /**
   * Resolves the context with respect to the previous context
   *
   * @param previousContext the previous context that we wish to compare to
   */
  public void resolve(Context previousContext) {
    // If list only contained values (no commands) add values to values in previous context
    if(previousContext.getResolvedCommands().isEmpty()) {
      values.addAll(previousContext.getValues());
    } else {
      // Add resolved commands of list to lists in outer context
      lists.push(previousContext.getResolvedCommands());
    }
  }
}
