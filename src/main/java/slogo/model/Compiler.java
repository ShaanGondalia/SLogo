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

public class Compiler {

  public static final String WHITESPACE = "\\s+";
  private static final String PARAMETER_RESOURCES = "model.Parameter";
  private static final String REFLECTION_RESOURCES = "model.Reflection";
  private final static String ERROR_MESSAGE = "Property %s is not a %s: %s";

  private Turtle myTurtle;
  private Parser myParser;
  private Map<String, Double> myVariables;
  private final ResourceBundle parameterResources = ResourceBundle.getBundle(PARAMETER_RESOURCES);
  private final ResourceBundle reflectionResources = ResourceBundle.getBundle(REFLECTION_RESOURCES);

  public Compiler () {
    myParser = new Parser();
    myParser.addPatterns("English");
    myParser.addPatterns("Syntax");
    myVariables = new HashMap<>();
    myTurtle = new Turtle();
  }

  public Queue<Command> compile(String program) throws MissingArgumentException {
    //Scanner scanner = new Scanner(input);
    Queue<Command> commandQueue = new LinkedList<>();

    Stack<String> pendingCommands = new Stack<>();
    Stack<Double> values = new Stack<>();

    // Split the input program by whitespace
    for (String token : program.split(WHITESPACE)) {
      String symbol = myParser.getSymbol(token);
      if (parameterResources.containsKey(symbol)){
        pendingCommands.push(symbol);
      } else if(symbol.equals("Constant")) {
        values.push(Double.parseDouble(token));
      } else if(symbol.equals("Variable")) {
        values.push(myVariables.get(token));
      }

      String pendingCommand = pendingCommands.peek();
      int numInputs = getNumInputs(pendingCommand);

      if (numInputs == values.size()) {
        List<Double> args = new ArrayList<>();
        for (int i = 0; i < numInputs; i++) {
          args.add(values.pop());
        }
        // Use reflection to create command
        Command command = getCommand(pendingCommand, args);
        commandQueue.add(command);
        pendingCommands.pop();
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
  private Command getCommand(String symbol, List<Double> args){
    String command = reflectionResources.getString(symbol).trim();
    try {
      // convert string into Java object that represents that Java class
      Class<?> clazz = Class.forName(command);
      // use reflection to find the appropriate constructor of that class to call to create a new instance
      Constructor<?> ctor = clazz.getDeclaredConstructor(Turtle.class, List.class);
      return (Command)ctor.newInstance(myTurtle, args);
    }
    catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException |
        InstantiationException | IllegalAccessException e) {
      throw new InputMismatchException(String.format(ERROR_MESSAGE, symbol, "command", command));
    }

  }
}
