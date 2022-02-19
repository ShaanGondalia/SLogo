package slogo.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.Stack;
import slogo.model.command.Command;
import slogo.model.exception.MissingArgumentException;
import slogo.model.parser.Parser;
import slogo.model.turtle.Turtle;

/**
 * Compiles user input into a queue of commands that can be executed. Depends on Turtle, Parser, and Command.
 *
 * @author Jake Heller and Shaan Gondalia
 */
public class Compiler {

  public static final String WHITESPACE = "\\s+";
  private static final String PARAMETER_RESOURCES = "model.Parameter";
  private static final String REFLECTION_RESOURCES = "model.Reflection";
  private final static String ERROR_MESSAGE = "Property %s is not a %s: %s";

  private Parser myParser;
  private Map<String, Double> myVariables;
  private final ResourceBundle parameterResources = ResourceBundle.getBundle(PARAMETER_RESOURCES);
  private final ResourceBundle reflectionResources = ResourceBundle.getBundle(REFLECTION_RESOURCES);

  /**
   * Creates an instance of a compiler for the given language.
   */
  public Compiler (String language) {
    myParser = new Parser();
    myParser.addPatterns(language);
    myParser.addPatterns("Syntax");
    myVariables = new HashMap<>();
  }

  /**
   * Compiles a program into a queue of executable commands.
   *
   * @param program the string input of the program
   * @param turtle the turtle to attach commands to
   * @return a queue of executable commands
   * @throws Exception if there is an issue compiling the program
   */
  public Queue<Command> compile(String program, Turtle turtle) throws Exception {
    //Scanner scanner = new Scanner(input);
    Queue<Command> commandQueue = new LinkedList<>();

    Stack<String> pendingCommands = new Stack<>();
    Stack<Double> values = new Stack<>();

    for (String token : program.split(WHITESPACE)) {
      String symbol = myParser.getSymbol(token);
      if (parameterResources.containsKey(symbol)){
        pendingCommands.push(symbol);
      } else if(symbol.equals("Constant")) {
        values.push(Double.parseDouble(token));
      } else if(symbol.equals("Variable")) {
        values.push(myVariables.get(token));
      }

      while(!pendingCommands.isEmpty() && getNumInputs(pendingCommands.peek()) == values.size()){
        List<Double> args = new ArrayList<>();
        String pendingCommand = pendingCommands.pop();
        for (int i = 0; i < getNumInputs(pendingCommand); i++) {
          args.add(values.pop());
        }
        // Use reflection to create command
        Command command = getCommand(pendingCommand, turtle, args);
        commandQueue.add(command);
        values.add(command.returnValue());
        if (pendingCommands.isEmpty()) {
          values.clear();
        }
      }
    }
    return commandQueue;
  }

  // Gets the number of inputs a given command takes.
  private int getNumInputs(String command) {
    return Integer.parseInt(parameterResources.getString(command));
  }

  // Returns an instance of a command using reflection
  private Command getCommand(String symbol, Turtle turtle, List<Double> args){
    String command = reflectionResources.getString(symbol).trim();
    try {
      // convert string into Java object that represents that Java class
      Class<?> clazz = Class.forName(command);
      // use reflection to find the appropriate constructor of that class to call to create a new instance
      Constructor<?> ctor = clazz.getDeclaredConstructor(Turtle.class, List.class);
      return (Command)ctor.newInstance(turtle, args);
    }
    catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException |
        InstantiationException | IllegalAccessException e) {
      throw new InputMismatchException(String.format(ERROR_MESSAGE, symbol, "command", command));
    }

  }
}
