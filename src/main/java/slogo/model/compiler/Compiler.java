package slogo.model.compiler;

import java.util.Collections;
import java.util.Deque;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Stack;
import java.util.regex.Pattern;
import slogo.model.command.Command;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.exception.SymbolNotFoundException;
import slogo.model.turtle.Turtle;

/**
 * Compiles user input into a queue of commands that can be executed. Depends on Turtle, Parser, and
 * Command.
 *
 * @author Jake Heller and Shaan Gondalia
 */
public class Compiler {

  public static final String WHITESPACE = "\\s+";
  public static final String COMMENT = "^#.*\n";
  private static final String EXCEPTION_RESOURCES = "model.exception.";

  private Parser myParser;
  private Map<String, Value> myVariables;

  private CommandFactory commandFactory;
  private final ResourceBundle exceptionResources;

  private Context activeContext;
  private Stack<Context> inactiveContexts;

  private String waitingUserCommandName;

  /**
   * Creates an instance of a compiler for the given language.
   */
  public Compiler(String language) {
    exceptionResources = ResourceBundle.getBundle(EXCEPTION_RESOURCES + language);
    myParser = new Parser(language);
    myParser.addPatterns(language);
    myParser.addPatterns("Syntax");
    myVariables = new LinkedHashMap<>(); // linked hashmap preserves insertion order for display
    commandFactory = new CommandFactory(language);
  }

  /**
   * Compiles and runs a program.
   *
   * @param program the string input of the program
   * @param turtles list of turtles to attach commands to
   * @throws Exception if there is an issue running the program
   */
  public Deque<Deque<Command>> compile(String program, List<Turtle> turtles) throws Exception {
    reset();
    program = program.replaceAll(COMMENT, ""); // TODO: verify if this actually works
    // will be changed when we can have multiple turtles
    Turtle turtle = turtles.get(0);

    for (String token : program.split(WHITESPACE)) {
      handleToken(token);
      // LOGIC:
      // After a command is added, we can only resolve the commands arguments once numInputs values have been added to the value stack.
      // We must keep track of how many values have been added to the stack after each command has been added.
      // We know the number of inputs each command requires, and we know the size of the values stack when each command is added.
      // We can create another data structure that tracks this information, and use it to determine when

      while (!activeContext.getPendingCommands().isEmpty()
          && commandFactory.getNumInputs(activeContext.getPendingCommands().peek())
          <= activeContext.getValues().size() - activeContext.getValuesBefore().peek()
          && commandFactory.getNumListInputs(activeContext.getPendingCommands().peek())
          <= activeContext.getLists().size() - activeContext.getListsBefore().peek()) {
        String pendingCommand = activeContext.getPendingCommands().pop();
        if(pendingCommand.equals("MakeUserInstruction")) {
          int numInputs = activeContext.getValues().size() - activeContext.getValuesBefore().peek();
          commandFactory.makeCommand(waitingUserCommandName, numInputs);
        }
        activeContext.getValuesBefore().pop();
        activeContext.getListsBefore().pop();
        Command command = commandFactory.getCommand(pendingCommand, turtle, activeContext.getValues(), activeContext.getLists());
        activeContext.getValues().add(command.returnValue());
        if (!activeContext.getLists().empty()) {
          activeContext.getLists().peek().addLast(command);
        } else {
          activeContext.getResolvedCommands().addLast(command);
        }
        if (activeContext.getPendingCommands().isEmpty()) {
          activeContext.getValues().clear();
          activeContext.getResolvedCommands().add(null);
        }
      }
    }
    if (!activeContext.getPendingCommands().empty()) {
      throw new MissingArgumentException(
          String.format(exceptionResources.getString("MissingArgument"), activeContext.getPendingCommands().peek()));
    }
    commandFactory.addUserDefinedCommandStrings(program, myParser);

    return constructResolvedCommandQueues();
  }

  // Returns a queue of queues of commands. Each inner queue represents a chunk of commands that
  // does not rely on any other commands
  private Deque<Deque<Command>> constructResolvedCommandQueues() {
    Deque<Deque<Command>> resolvedCommandQueues = new LinkedList<>();
    resolvedCommandQueues.add(new LinkedList<>());
    for (Command c : activeContext.getResolvedCommands()){
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

  // "Resets" the compiler, wiping all contexts
  private void reset() {
    activeContext = new Context();
    inactiveContexts = new Stack<>();
  }


  private void handleToken(String token) throws SymbolNotFoundException {
    String symbol = myParser.getSymbol(token);
    // TODO: Replace this conditional chain with reflective method calls
    if (commandFactory.isCommand(symbol)) {
      handleCommand(symbol);
    } else if (symbol.equals("Constant")) {
      activeContext.getValues().push(new Value(Double.parseDouble(token)));
    } else if (symbol.equals("Variable")) {
      handleVariable(token);
    } else if (symbol.equals("UserCommand")) {
      handleUserCommand(token);
    } else if (symbol.equals("ListStart")) {
      swapContext();
    } else if (symbol.equals("ListEnd")) {
      resolveContext();
    } else {
      throw new SymbolNotFoundException(
          String.format(exceptionResources.getString("SymbolNotFound"), token));
    }

  }

  // Handles what happens when a command is detected by the parser
  private void handleCommand(String symbol){
    activeContext.getPendingCommands().push(symbol);
    activeContext.getValuesBefore().push(activeContext.getValues().size());
    activeContext.getListsBefore().push(activeContext.getLists().size());
  }

  // Handles what happens when a variable is detected by the parser
  private void handleVariable(String value){
    if (!myVariables.containsKey(value)) {
      myVariables.put(value, new Value());
    }
    activeContext.getValues().push(myVariables.get(value));
  }

  // Handles what happens when a user command is detected by the parser
  private void handleUserCommand(String token) throws SymbolNotFoundException {
    try {
      if (commandFactory.isCommand(token)) {
        handleCommand(token);
      }
      else if (activeContext.getPendingCommands().peek().equals("MakeUserInstruction")) {
        waitingUserCommandName = token;
      } else {
        throw new SymbolNotFoundException(
            String.format(exceptionResources.getString("SymbolNotFound"), token));
      }
    } catch (EmptyStackException e) {
      throw new SymbolNotFoundException(
          String.format(exceptionResources.getString("SymbolNotFound"), token));
    }
  }

  // Swaps the context of the compiler in the case of a list
  private void swapContext() {
    inactiveContexts.push(activeContext);
    activeContext = new Context();
  }

  // Resolves the context of the compiler in the case of a list ending
  private void resolveContext() {
    inactiveContexts.peek().resolve(activeContext);
    activeContext = inactiveContexts.pop();
  }

  public Map<String, String> getVariables() {
    Map<String, String> variables = new LinkedHashMap<>();
    for (String name: myVariables.keySet()) {
      String val = String.format("%.2f", myVariables.get(name).getVal());
      variables.put(name, val);
    }
    return variables;
  }

  /**
   * @return copy of command strings map (Strings are immutable, so this is a deep copy)
   */
  public Map<String, String> getUserCommandStrings() {
    return new HashMap<>(commandFactory.getUserCommandStrings());
  }

}
