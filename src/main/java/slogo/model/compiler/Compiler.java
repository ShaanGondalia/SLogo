package slogo.model.compiler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Deque;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Stack;
import slogo.model.command.Command;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.exception.SymbolNotFoundException;
import slogo.model.turtle.TurtleManager;

/**
 * Compiles user input into a queue of commands that can be executed. Depends on Turtle, Parser, and
 * Command.
 *
 * @author Jake Heller and Shaan Gondalia
 */
public class Compiler {

  public static final String WHITESPACE = "\\s+";

  private static final String CONSTANT = "Constant";
  private static final String VARIABLE = "Variable";
  private static final String USER_COMMAND = "UserCommand";
  private static final String LIST_START = "ListStart";
  private static final String LIST_END = "ListEnd";
  private static final String EXCEPTION_RESOURCES = "model.exception.";
  private static final String HANDLER_RESOURCES = "model.Handler";

  private final ResourceBundle exceptionResources;
  private final ResourceBundle handlerResources;
  private final Parser myParser;
  private final Map<String, Value> myVariables;
  private final CommandFactory commandFactory;
  private Context activeContext;
  private Stack<Context> inactiveContexts;

  private String waitingUserCommandName;

  /**
   * Creates an instance of a compiler for the given language.
   */
  public Compiler(String language, TurtleManager turtleManager) {
    exceptionResources = ResourceBundle.getBundle(EXCEPTION_RESOURCES + language);
    handlerResources = ResourceBundle.getBundle(HANDLER_RESOURCES);
    myParser = new Parser(language);
    myParser.addPatterns(language);
    myParser.addPatterns("Syntax");
    myVariables = new LinkedHashMap<>(); // linked hashmap preserves insertion order for display
    commandFactory = new CommandFactory(language, turtleManager);
  }

  /**
   * Compiles and runs a program.
   *
   * @param program the string input of the program
   * @throws Exception if there is an issue running the program
   */
  public Deque<Deque<Command>> compile(String program) throws Exception {
    reset();
    program = myParser.removeComments(program);
    for (String token : program.split(WHITESPACE)) {
      handleToken(token);
      while (canBeResolved()) {
        String pendingCommand = activeContext.getPendingCommands().pop();
        int numInputs = getNumInputs(pendingCommand);
        if (pendingCommand.equals("MakeUserInstruction")) {
          commandFactory.makeUserCommand(waitingUserCommandName, numInputs);
        }
        resolveCommandInContext(pendingCommand, numInputs);
      }
    }
    checkPendingCommandsEmpty();
    commandFactory.addUserDefinedCommandStrings(program, myParser);
    return constructResolvedCommandQueues();
  }

  // Throws an error if there are pending commands after the compiler loop ends
  private void checkPendingCommandsEmpty() throws MissingArgumentException {
    if (!activeContext.getPendingCommands().empty()) {
      throw new MissingArgumentException(
          String.format(exceptionResources.getString("MissingArgument"),
              activeContext.getPendingCommands().peek()));
    }
  }

  // Builds and resolves a command's parameters in the active context.
  private void resolveCommandInContext(String pendingCommand, int numInputs) throws MissingArgumentException {
    activeContext.getValuesBefore().pop();
    activeContext.getListsBefore().pop();
    Command command = commandFactory.getCommand(pendingCommand, activeContext.getValues(),
        activeContext.getLists(), numInputs);
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

  private int getNumInputs(String pendingCommand) throws SymbolNotFoundException {
    int numInputs = commandFactory.getNumInputs(pendingCommand);
    if (numInputs == -1) {
      numInputs = activeContext.getValues().size() - activeContext.getValuesBefore().peek();
    }
    return numInputs;
  }

  //Returns true if the pending command can be resolved
  private boolean canBeResolved() throws SymbolNotFoundException {
    if (activeContext.getPendingCommands().isEmpty()) {
      return false;
    }
    int numInputs = commandFactory.getNumInputs(activeContext.getPendingCommands().peek());
    if (commandFactory.getNumInputs(activeContext.getPendingCommands().peek()) == -1) {
      numInputs = 1;
    }
    return numInputs <= activeContext.getValues().size() - activeContext.getValuesBefore().peek()
        && commandFactory.getNumListInputs(activeContext.getPendingCommands().peek())
        <= activeContext.getLists().size() - activeContext.getListsBefore().peek();
  }

  // Returns a queue of queues of commands. Each inner queue represents a chunk of commands that
  // does not rely on any other commands
  private Deque<Deque<Command>> constructResolvedCommandQueues() {
    Deque<Deque<Command>> resolvedCommandQueues = new LinkedList<>();
    resolvedCommandQueues.add(new LinkedList<>());
    for (Command c : activeContext.getResolvedCommands()) {
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

  // Handles a token
  private void handleToken(String token) throws SymbolNotFoundException {
    String symbol = myParser.getSymbol(token);
    if (commandFactory.isCommand(symbol)) {
      handleCommand(symbol);
    } else {
      try {
        String handlerMethod = handlerResources.getString(symbol);
        Method handle = Compiler.class.getDeclaredMethod(handlerMethod, String.class);
        handle.invoke(this, token);
      } catch (Exception e) {
          throw new SymbolNotFoundException(
              String.format(exceptionResources.getString("SymbolNotFound"), token));
      }
    }
  }

  // Handles what happens when a command is detected by the parser
  private void handleCommand(String symbol) {
    activeContext.getPendingCommands().push(symbol);
    activeContext.getValuesBefore().push(activeContext.getValues().size());
    activeContext.getListsBefore().push(activeContext.getLists().size());
  }

  // Handles what happens when a constant is detected by the parser. Called with reflection
  private void handleConstant(String token) {
    activeContext.getValues().push(new Value(Double.parseDouble(token)));
  }

  // Handles what happens when a variable is detected by the parser. Called with reflection
  private void handleVariable(String value) {
    if (!myVariables.containsKey(value)) {
      myVariables.put(value, new Value());
    }
    activeContext.getValues().push(myVariables.get(value));
  }

  // Handles what happens when a user command is detected by the parser. Called with reflection
  private void handleUserCommand(String token) throws SymbolNotFoundException {
    try {
      if (commandFactory.isCommand(token)) {
        handleCommand(token);
      } else if (activeContext.getPendingCommands().peek().equals("MakeUserInstruction")) {
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

  // Swaps the context of the compiler in the case of a list. Called with reflection
  private void swapContext(String token) {
    inactiveContexts.push(activeContext);
    activeContext = new Context();
  }

  // Resolves the context of the compiler in the case of a list ending. Called with reflection
  private void resolveContext(String token) {
    inactiveContexts.peek().resolve(activeContext);
    activeContext = inactiveContexts.pop();
  }

  public Map<String, String> getVariables() {
    Map<String, String> variables = new LinkedHashMap<>();
    for (String name : myVariables.keySet()) {
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
