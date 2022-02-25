package slogo.model.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import slogo.model.command.Command;
import slogo.model.command.Value;
import slogo.model.command.control.MakeUserInstruction;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Factory that has methods that return commands using reflection.
 */
public class CommandFactory {
  private static final String REFLECTION_RESOURCES = "model.Reflection";
  private static final String EXCEPTION_RESOURCES = "model.exception.";
  private static final String PARAMETER_RESOURCES = "model.Parameter";
  private static final String LIST_PARAMETER_RESOURCES = "model.ListParameter";

  private final ResourceBundle reflectionResources = ResourceBundle.getBundle(REFLECTION_RESOURCES);
  private final ResourceBundle parameterResources = ResourceBundle.getBundle(PARAMETER_RESOURCES);

  private final Map<String, MakeUserInstruction> myUserCommands;
  private final Map<String, Integer> myListParameterCounts;
  private final Map<String, Integer> myParameterCounts;

  private final ResourceBundle exceptionResources;

  public CommandFactory(String language){
    exceptionResources = ResourceBundle.getBundle(EXCEPTION_RESOURCES + language);
    myUserCommands = new HashMap<>();
    myListParameterCounts = new HashMap<>();
    myParameterCounts = new HashMap<>();

    ResourceBundle listParameterResources = ResourceBundle.getBundle(LIST_PARAMETER_RESOURCES);
    loadResources(myParameterCounts, parameterResources);
    loadResources(myListParameterCounts, listParameterResources);
  }

  /**
   * Returns an instance of a command using reflection
   *
   * @param symbol
   * @param turtle
   * @param args
   * @return
   * @throws MissingArgumentException
   */
  public Command getCommand(String symbol, Turtle turtle, List<Value> args)
      throws MissingArgumentException {
    if (myUserCommands.containsKey(symbol)) {
      MakeUserInstruction c = myUserCommands.get(symbol);
      c.setActualParameters(args);
      return c;
    }
    String command = reflectionResources.getString(symbol).trim();
    try {
      // convert string into Java object that represents that Java class
      Class<?> clazz = Class.forName(command);
      // use reflection to find the appropriate constructor of that class to call to create a new instance
      Constructor<?> ctor = clazz.getDeclaredConstructor(Turtle.class, List.class);
      return (Command) ctor.newInstance(turtle, args);
    } catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException |
        InstantiationException | IllegalAccessException e) {
      throw new InputMismatchException(
          String.format(exceptionResources.getString("InputMismatch"), symbol, command));
    }
  }

  /**
   * Makes a user defined command
   */
  public void makeCommand(String symbol, int inputs) {
    myParameterCounts.put(symbol, inputs);
  }

  /**
   * Returns whether a symbol is a known command
   * @param symbol
   * @return
   */
  public boolean isCommand(String symbol){
    return myParameterCounts.containsKey(symbol) || myUserCommands.containsKey(symbol);
  }

  /**
   * Gets the number of inputs a given command takes.
   * @param command
   * @return
   */
  public int getNumInputs(String command) {
    return Integer.parseInt(parameterResources.getString(command));
  }


  private void loadResources(Map<String, Integer> map, ResourceBundle resource) {
    for (String key: resource.keySet()) {
      Integer value = Integer.parseInt(resource.getString(key));
      map.put(key, value);
    }
  }

}
