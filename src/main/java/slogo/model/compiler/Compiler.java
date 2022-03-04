package slogo.model.compiler;

import java.lang.reflect.Method;
import java.util.Deque;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Stack;
import slogo.model.color.ColorPalette;
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
  private static final String EXCEPTION_RESOURCES = "model.exception.";
  private static final String HANDLER_RESOURCES = "model.Handler";

  private final ResourceBundle exceptionResources;
  private final ResourceBundle handlerResources;
  private final Parser myParser;
  private final Map<String, Value> myVariables;
  private final CommandFactory commandFactory;
  private Context activeContext;
  private Stack<Context> inactiveContexts;
  private boolean inGroup;

  private String waitingUserCommandName;

  /**
   * Creates an instance of a compiler for the given language.
   */
  public Compiler(String language, TurtleManager turtleManager, ColorPalette colorPalette) {
    exceptionResources = ResourceBundle.getBundle(EXCEPTION_RESOURCES + language);
    handlerResources = ResourceBundle.getBundle(HANDLER_RESOURCES);
    myParser = new Parser(language);
    myParser.addPatterns(language);
    myParser.addPatterns("Syntax");
    myVariables = new LinkedHashMap<>(); // linked hashmap preserves insertion order for display
    commandFactory = new CommandFactory(language, turtleManager, colorPalette);
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
        String pendingCommand = activeContext.pendingCommand();
        // int numInputs = getNumInputs(pendingCommand);
        int numInputs = activeContext.numberNewValues(); // Not sure if this works
        if (pendingCommand.equals("MakeUserInstruction")) {
          commandFactory.makeUserCommand(waitingUserCommandName, numInputs);
        }
        activeContext.resolveCommand(commandFactory, numInputs);
      }
    }
    checkPendingCommandsEmpty();
    commandFactory.addUserDefinedCommandStrings(program, myParser);
    return activeContext.constructResolvedCommandQueues();
  }

  // Throws an error if there are pending commands after the compiler loop ends
  private void checkPendingCommandsEmpty() throws MissingArgumentException {
    if (activeContext.pendingCommand() != null) {
      throw new MissingArgumentException(
          String.format(exceptionResources.getString("MissingArgument"),
              activeContext.pendingCommand()));
    }
  }

  private int getNumInputs(String pendingCommand) throws SymbolNotFoundException {
    int numInputs = commandFactory.getNumInputs(pendingCommand);
    if (numInputs == -1) {
      numInputs = activeContext.numberNewValues();
    }
    return numInputs;
  }

  //Returns true if the pending command can be resolved
  private boolean canBeResolved() throws SymbolNotFoundException {
    if (activeContext.pendingCommand() == null || inGroup) {
      return false;
    }
    int numInputs = commandFactory.getNumInputs(activeContext.pendingCommand());
    if (commandFactory.getNumInputs(activeContext.pendingCommand()) == -1) {
      numInputs = 1;
    }
    return numInputs <= activeContext.numberNewValues()
        && commandFactory.getNumListInputs(activeContext.pendingCommand())
        <= activeContext.numberNewLists();
  }

  // "Resets" the compiler, wiping all contexts
  private void reset() {
    activeContext = new Context();
    inactiveContexts = new Stack<>();
    inGroup = false;
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
    activeContext.addPendingCommand(symbol);
  }

  // Handles what happens when a constant is detected by the parser. Called with reflection
  private void handleConstant(String token) {
    activeContext.addValue(new Value(Double.parseDouble(token)));
  }

  // Handles what happens when a variable is detected by the parser. Called with reflection
  private void handleVariable(String value) {
    if (!myVariables.containsKey(value)) {
      myVariables.put(value, new Value());
    }
    activeContext.addValue(myVariables.get(value));
  }

  // Handles what happens when a user command is detected by the parser. Called with reflection
  private void handleUserCommand(String token) throws SymbolNotFoundException {
    try {
      if (commandFactory.isCommand(token)) {
        handleCommand(token);
      } else if (activeContext.pendingCommand().equals("MakeUserInstruction")) {
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

  // Handles what happens when a group start ( symbol is recognized
  private void handleGroupStart(String token) {
    inGroup = true;
  }

  // Handles what happens when a group end ) symbol is recognized
  private void handleGroupEnd(String token) {
    inGroup = false;
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
