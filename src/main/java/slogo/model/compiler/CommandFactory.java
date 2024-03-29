package slogo.model.compiler;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Stack;
import slogo.model.color.ColorPalette;
import slogo.model.command.Command;
import slogo.model.command.ExtendedSyntaxCommand;
import slogo.model.command.Value;
import slogo.model.command.control.MakeUserInstruction;
import slogo.model.exception.MissingArgumentException;
import slogo.model.exception.SymbolNotFoundException;
import slogo.model.turtle.TurtleManager;

/**
 * Factory that has methods that return commands using reflection.
 *
 * @author Shaan Gondalia
 */
public class CommandFactory {

  private static final String REFLECTION_RESOURCES = "model.Reflection";
  private static final String EXCEPTION_RESOURCES = "model.exception.";
  private static final String PARAMETER_RESOURCES = "model.Parameter";
  private static final String LIST_PARAMETER_RESOURCES = "model.ListParameter";
  private static final String CONSTRUCTOR_RESOURCES = "model.Constructor";

  private final ResourceBundle reflectionResources = ResourceBundle.getBundle(REFLECTION_RESOURCES);
  private final ResourceBundle constructorResources = ResourceBundle.getBundle(
      CONSTRUCTOR_RESOURCES);

  private final Map<String, MakeUserInstruction> myUserCommands;
  private final Map<String, String> myUserCommandStrings;
  private final Map<String, Integer> myListParameterCounts;
  private final Map<String, Integer> myParameterCounts;

  private final TurtleManager myTurtleManager;
  private final ColorPalette myColorPalette;

  private final ResourceBundle exceptionResources;

  private String lastAddedSymbol;

  /**
   * Creates a new command factory that operates in the given language
   *
   * @param language the language of the command factory
   */
  public CommandFactory(String language, TurtleManager turtleManager, ColorPalette colorPalette) {
    exceptionResources = ResourceBundle.getBundle(EXCEPTION_RESOURCES + language);
    myTurtleManager = turtleManager;
    myColorPalette = colorPalette;
    myUserCommands = new HashMap<>();
    myUserCommandStrings = new HashMap<>();
    myListParameterCounts = new HashMap<>();
    myParameterCounts = new HashMap<>();

    ResourceBundle listParameterResources = ResourceBundle.getBundle(LIST_PARAMETER_RESOURCES);
    ResourceBundle parameterResources = ResourceBundle.getBundle(PARAMETER_RESOURCES);
    loadResources(myParameterCounts, parameterResources);
    loadResources(myListParameterCounts, listParameterResources);
  }

  /**
   * Returns an instance of a command using reflection
   *
   * @param symbol the symbol of the command
   * @param values the stack of values to pass to the command
   * @param implicitVariables the implicit variables of the command (empty if none)
   * @return the command built from the given parameters.
   * @throws MissingArgumentException
   */
  public Command getCommand(String symbol, Stack<Value> values,
      Stack<Deque<Command>> commandLists,
      Map<String, Value> implicitVariables, int numInputs)
      throws MissingArgumentException, SymbolNotFoundException {
    List<Value> args = generateArgList(symbol, values, numInputs);
    List<Deque<Command>> commandQueues = generateCommandQueueList(symbol, commandLists);
    if (myUserCommands.containsKey(symbol)) {
      return getUserCommand(symbol, args);
    } else {
      return generateCommand(symbol, args, commandQueues, implicitVariables);
    }
  }

  // Generates list of command queues for a command
  private List<Deque<Command>> generateCommandQueueList(String symbol,
      Stack<Deque<Command>> commandLists) throws MissingArgumentException {
    List<Deque<Command>> commandQueues = new ArrayList<>();
    int numLists = getNumListInputs(symbol);

    if (commandLists.size() < numLists) {
      throw new MissingArgumentException(
          String.format(exceptionResources.getString("MissingArgument"), symbol));
    }
    for (int i = 0; i < getNumListInputs(symbol); i++) {
      commandQueues.add(0, commandLists.pop()); // add element to start of args
    }
    return commandQueues;
  }

  // Generates list of args for a command
  private List<Value> generateArgList(String symbol, Stack<Value> values, int numInputs)
      throws MissingArgumentException {
    List<Value> args = new ArrayList<>();
    if (values.size() < numInputs) {
      throw new MissingArgumentException(
          String.format(exceptionResources.getString("MissingArgument"), symbol));
    }
    for (int i = 0; i < numInputs; i++) {
      args.add(0, values.pop()); // add element to start of args
    }
    return args;
  }

  private Command getUserCommand(String symbol, List<Value> args) throws MissingArgumentException {
    MakeUserInstruction c = myUserCommands.get(symbol);
    return c.getUserCommand(args);
  }

  // Generates a command using reflection
  private Command generateCommand(String symbol, List<Value> args, List<Deque<Command>> queues,
      Map<String, Value> implicitVariables)
      throws SymbolNotFoundException {
    String command = reflectionResources.getString(symbol).trim();
    try {
      String handlerMethod = constructorResources.getString(symbol);
      Method handle = CommandFactory.class.getDeclaredMethod(handlerMethod, Class.class, List.class,
          List.class, Map.class);
      Command c = (Command) handle.invoke(this, Class.forName(command), args, queues, implicitVariables);
      if (symbol.equals("MakeUserInstruction")) {
        myUserCommands.put(lastAddedSymbol, (MakeUserInstruction) c);
      } else if (getNumInputs(symbol) != args.size() && getNumInputs(symbol) != -1) {
        return new ExtendedSyntaxCommand(args, c, getNumInputs(symbol));
      }
      return c;
    } catch (Exception e) {
      throw new SymbolNotFoundException(
          String.format(exceptionResources.getString("SymbolNotFound"), symbol));
    }
  }

  private Command makeArgCommand(Class<?> commandClass, List<Value> args,
      List<Deque<Command>> commandQueues, Map<String, Value> implicitVariables)
      throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    Constructor<?> ctor = commandClass.getDeclaredConstructor(List.class);
    return (Command) ctor.newInstance(args);
  }

  private Command makeListCommand(Class<?> commandClass, List<Value> args,
      List<Deque<Command>> commandQueues, Map<String, Value> implicitVariables)
      throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    Constructor<?> ctor = commandClass.getDeclaredConstructor(List.class, List.class);
    return (Command) ctor.newInstance(args, commandQueues);
  }

  private Command makeListMultiCommand(Class<?> commandClass, List<Value> args,
      List<Deque<Command>> commandQueues, Map<String, Value> implicitVariables)
      throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    Constructor<?> ctor = commandClass.getDeclaredConstructor(List.class, List.class,
        TurtleManager.class);
    return (Command) ctor.newInstance(args, commandQueues, myTurtleManager);
  }

  private Command makeArgMultiCommand(Class<?> commandClass, List<Value> args,
      List<Deque<Command>> commandQueues, Map<String, Value> implicitVariables)
      throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    Constructor<?> ctor = commandClass.getDeclaredConstructor(List.class, TurtleManager.class);
    return (Command) ctor.newInstance(args, myTurtleManager);
  }

  private Command makeArgColorCommand(Class<?> commandClass, List<Value> args,
      List<Deque<Command>> commandQueues, Map<String, Value> implicitVariables)
      throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    Constructor<?> ctor = commandClass.getDeclaredConstructor(ColorPalette.class, List.class);
    return (Command) ctor.newInstance(myColorPalette, args);
  }

  private Command makeListImplicitCommand(Class<?> commandClass, List<Value> args,
      List<Deque<Command>> commandQueues, Map<String, Value> implicitVariables)
      throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    Constructor<?> ctor = commandClass.getDeclaredConstructor(List.class, List.class, Map.class);
    return (Command) ctor.newInstance(args, commandQueues, implicitVariables);
  }

  /**
   * Makes a user defined command
   *
   * @param symbol the name of the command
   * @param inputs the number of inputs to the command
   */
  public void makeUserCommand(String symbol, int inputs) {
    myParameterCounts.put(symbol, inputs);
    lastAddedSymbol = symbol;
  }

  /**
   * Removes a user command from the map of user commands. Necessary for command redefinition.
   * @param symbol the name of the command
   */
  public void clearUserCommand(String symbol) {
    myParameterCounts.remove(symbol);
  }

  /**
   * Returns whether a symbol is a known command
   *
   * @param symbol the symbol to check
   * @return whether symbol is a command
   */
  public boolean isCommand(String symbol) {
    return myParameterCounts.containsKey(symbol) || myUserCommands.containsKey(symbol);
  }

  /**
   * Gets the number of inputs a given command takes.
   *
   * @param command the command to get the number of inputs for
   * @return the number of inputs the command takes.
   */
  public int getNumInputs(String command) throws SymbolNotFoundException {
    if (command.equals("MakeUserInstruction")) {
      return myParameterCounts.getOrDefault(lastAddedSymbol, 0);
    }
    if (!myParameterCounts.containsKey(command)) {
      throw new SymbolNotFoundException(
          String.format(exceptionResources.getString("SymbolNotFound"), command));
    }
    return myParameterCounts.get(command);
  }

  /**
   * Adds the string associated with user command
   *
   * @param program entire program that was run
   * @param p       parser used to parse program
   */
  public void addUserDefinedCommandStrings(String program, Parser p) {
    Map<String, String> userCommands = UserCommandFinder.findUserCommands(program, p,
        myUserCommands.keySet());
    myUserCommandStrings.putAll(userCommands);
  }

  public Map<String, String> getUserCommandStrings() {
    return myUserCommandStrings;
  }

  /**
   * Returns the number of list inputs a given command takes.
   *
   * @param command the command to get the number of list inputs for
   * @return the number of list inputs the command takes
   */
  public int getNumListInputs(String command) {
    if (!myListParameterCounts.containsKey(command)) {
      return 0;
    }
    return myListParameterCounts.get(command);
  }

  // Loads property files into a map
  private void loadResources(Map<String, Integer> map, ResourceBundle resource) {
    for (String key : resource.keySet()) {
      Integer value = Integer.parseInt(resource.getString(key));
      map.put(key, value);
    }
  }

  /**
   *
   * @return color palette being used by CommandFactory instance
   */
  public ColorPalette getPalette() {
    return myColorPalette;
  }

}
