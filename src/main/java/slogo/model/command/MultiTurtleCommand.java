package slogo.model.command;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import slogo.model.compiler.CommandFactory;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;
import slogo.model.turtle.TurtleManager;

public class MultiTurtleCommand extends AbstractCommand {

  private TurtleManager myManager;
  private Constructor<Command> myConstructor;
  private List<Value> myArgs;

  public MultiTurtleCommand(TurtleManager t, String commandName, List<Value> args)
      throws MissingArgumentException {
    myManager = t;
    myArgs = args;
    myConstructor = getConstructor(commandName);
  }

  public MultiTurtleCommand(TurtleManager t, Constructor<Command> constructor, List<Value> args)
      throws MissingArgumentException {
    myManager = t;
    myArgs = args;
    myConstructor = constructor;
  }



  @Override
  public Value execute() throws MissingArgumentException {
    try {
      Command lastCommand = null;
      for (Turtle t : myManager.getActiveTurtles()) {
        Command c = myConstructor.newInstance(t, myArgs);
        myManager.setActive(t);
        c.execute();
        lastCommand = c;
      }
      if (lastCommand != null) {
        setReturnValue(lastCommand.returnValue().getVal());
      }
    } catch (Exception e) {
      throw new MissingArgumentException("Message");
    }
    return returnValue();
  }

  // repeated code from Command Factory
  // would like to extract CommandFactory into static and non-static methods
  private static Command getTurtleCommand(String commandName, Turtle turtle, List<Value> args)
      throws MissingArgumentException {
    try {
      // convert string into Java object that represents that Java class
      Class<?> clazz = Class.forName(commandName);
      // use reflection to find the appropriate constructor of that class to call to create a new instance
      Constructor<?> ctor = clazz.getDeclaredConstructor(Turtle.class, List.class);
      Command c = (Command) ctor.newInstance(turtle, args);
      return c;
    } catch (Exception e) {
      throw new MissingArgumentException("Message");
    }
  }

  private Constructor<Command> getConstructor(String commandName) throws MissingArgumentException {
    try {
      // convert string into Java object that represents that Java class
      Class<?> clazz = Class.forName(commandName);
      // use reflection to find the appropriate constructor of that class to call to create a new instance
      Constructor<Command> ctor = (Constructor<Command>) clazz.getDeclaredConstructor(Turtle.class, List.class);
      return ctor;
    } catch (Exception e) {
      throw new MissingArgumentException("Message");
    }
  }
}
