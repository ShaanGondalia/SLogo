package slogo.model;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Stack;
import slogo.model.command.Command;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.exception.SymbolNotFoundException;
import slogo.model.factory.CommandFactory;
import slogo.model.parser.Parser;
import slogo.model.turtle.Turtle;

/**
 * Compiles user input into a queue of commands that can be executed. Depends on Turtle, Parser, and
 * Command.
 *
 * @author Jake Heller and Shaan Gondalia
 */
public class Compiler {

  public static final String WHITESPACE = "\\s+";
  private static final String EXCEPTION_RESOURCES = "model.exception.";

  private Parser myParser;
  private Map<String, Value> myVariables;

  private CommandFactory commandFactory;
  private final ResourceBundle exceptionResources;

  private Stack<String> pendingCommandsInContext = new Stack<>();
  private Deque<Command> resolvedCommandsInContext = new LinkedList<>();
  private Stack<Value> valuesInContext = new Stack<>();
  private Stack<Deque<Command>> listsInContext = new Stack<>();
  private Stack<Integer> valuesBeforeInContext = new Stack<>();
  private Stack<Integer> listsBeforeInContext = new Stack<>();

  private Stack<Stack<String>> pendingCommandsOutOfContext = new Stack<>();
  private Stack<Deque<Command>> resolvedCommandsOutOfContext = new Stack<>();
  private Stack<Stack<Value>> valuesOutOfContext = new Stack<>();
  private Stack<Stack<Deque<Command>>> listsOutOfContext = new Stack<>();
  private Stack<Stack<Integer>> valuesBeforeOutOfContext = new Stack<>();
  private Stack<Stack<Integer>> listsBeforeOutOfContext = new Stack<>();


  /**
   * Creates an instance of a compiler for the given language.
   */
  public Compiler(String language) {
    exceptionResources = ResourceBundle.getBundle(EXCEPTION_RESOURCES + language);
    myParser = new Parser(language);
    myParser.addPatterns(language);
    myParser.addPatterns("Syntax");
    myVariables = new HashMap<>();
    commandFactory = new CommandFactory(language);
  }

  /**
   * Compiles and runs a program.
   *
   * @param program the string input of the program
   * @param turtles list of turtles to attach commands to
   * @throws Exception if there is an issue running the program
   */
  public Deque<Command> compile(String program, List<Turtle> turtles) throws Exception {
    // will be changed when we can have multiple turtles
    Turtle turtle = turtles.get(0);

    for (String token : program.split(WHITESPACE)) {
      handleToken(token);
      // LOGIC:
      // After a command is added, we can only resolve the commands arguments once numInputs values have been added to the value stack.
      // We must keep track of how many values have been added to the stack after each command has been added.
      // We know the number of inputs each command requires, and we know the size of the values stack when each command is added.
      // We can create another data structure that tracks this information, and use it to determine when

      while (!pendingCommandsInContext.isEmpty()
          && commandFactory.getNumInputs(pendingCommandsInContext.peek())
          <= valuesInContext.size() - valuesBeforeInContext.peek()
          && commandFactory.getNumListInputs(pendingCommandsInContext.peek())
          <= listsInContext.size() - listsBeforeInContext.peek()) {
        String pendingCommand = pendingCommandsInContext.pop();
        valuesBeforeInContext.pop();
        listsBeforeInContext.pop();
        Command command = commandFactory.getCommand(pendingCommand, turtle, valuesInContext, listsInContext);
        valuesInContext.add(command.returnValue());
        if (!listsInContext.empty()) {
          listsInContext.peek().addLast(command);
        } else {
          resolvedCommandsInContext.addLast(command);
        }
        if (pendingCommandsInContext.isEmpty()) {
          valuesInContext.clear();
        }
      }
    }
    if (!pendingCommandsInContext.empty()) {
      throw new MissingArgumentException(
          String.format(exceptionResources.getString("MissingArgument"), pendingCommandsInContext.peek()));
    }
    return resolvedCommandsInContext;
  }


  private void handleToken(String token) throws SymbolNotFoundException {
    String symbol = myParser.getSymbol(token);

    if (commandFactory.isCommand(symbol)) {
      pendingCommandsInContext.push(symbol);
      valuesBeforeInContext.push(valuesInContext.size());
      listsBeforeInContext.push(listsInContext.size());
    } else if (symbol.equals("Constant")) {
      valuesInContext.push(new Value(Double.parseDouble(token)));
    } else if (symbol.equals("Variable")) {
      if (!myVariables.containsKey(token)) {
        myVariables.put(token, new Value());
      }
      valuesInContext.push(myVariables.get(token));
    } else if (symbol.equals("UserCommand")) {
      if (pendingCommandsInContext.peek().equals("MakeUserInstruction")) {
        // TODO: figure out how make user instruction works
        int inputs = 0; // need to figure out how many inputs user instruction takes
        commandFactory.makeCommand(token, inputs);
      } else {
        throw new SymbolNotFoundException(
            String.format(exceptionResources.getString("SymbolNotFound"), symbol));
      }
    } else if (symbol.equals("ListStart")) {
      swapContext();
    } else if (symbol.equals("ListEnd")) {
      resolveContext();
    }
  }

  // Swaps the context of the compiler in the case of a list
  private void swapContext() {
    pendingCommandsOutOfContext.push(pendingCommandsInContext);
    resolvedCommandsOutOfContext.push(resolvedCommandsInContext);
    valuesOutOfContext.push(valuesInContext);
    listsOutOfContext.push(listsInContext);
    valuesBeforeOutOfContext.push(valuesBeforeInContext);
    listsBeforeOutOfContext.push(listsBeforeInContext);

    pendingCommandsInContext = new Stack<>();
    resolvedCommandsInContext = new LinkedList<>();
    valuesInContext = new Stack<>();
    listsInContext = new Stack<>();
    valuesBeforeInContext = new Stack<>();
    listsBeforeInContext = new Stack<>();
  }

  // Resolves the context of the compiler in the case of a list ending
  private void resolveContext() {
    // Revert back to state before context swap
    pendingCommandsInContext = pendingCommandsOutOfContext.pop();
    valuesBeforeInContext = valuesBeforeOutOfContext.pop();
    listsBeforeInContext = listsBeforeOutOfContext.pop();
    listsInContext = listsOutOfContext.pop();
    // If list only contained values (no commands) add values to values in previous context
    if(resolvedCommandsInContext.isEmpty()) {
      valuesOutOfContext.peek().addAll(valuesInContext);
    } else {
      // Add resolved commands of list to lists in outer context
      listsInContext.push(resolvedCommandsInContext);
    }
    resolvedCommandsInContext = resolvedCommandsOutOfContext.pop();
    valuesInContext = valuesOutOfContext.pop();
  }

}
