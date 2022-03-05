package slogo.controller;

import java.beans.PropertyChangeListener;
import java.util.*;

import java.util.Map;
import slogo.Errors;
import slogo.model.color.ColorPalette;
import slogo.model.command.Command;

import slogo.model.compiler.Compiler;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;
import slogo.model.turtle.TurtleManager;
import slogo.view.turtle.TurtleView;
import slogo.view.turtle.TurtleViewManager;

public class Controller {

  public static final String VARIABLE_GETTER = "variables";
  public static final String USER_COMMAND_GETTER = "userCommands";
  public static final String COLOR_PALETTE_GETTER = "colorPalette";
  public static final String TURTLE_GETTER = "turtles";
  public static final String DEFAULT_GETTER = "default";


  private Compiler myCompiler;
  private TurtleManager myTurtleManager;
  private ColorPalette myColorPalette;
  private Map<String, MapGetter<String, String>> myMapGetters;

  public Controller(String language, TurtleManager turtleManager) {
    myTurtleManager = turtleManager;
    myColorPalette = new ColorPalette();
    myCompiler = new Compiler(language, myTurtleManager, myColorPalette);
    myMapGetters = new HashMap<>();
    myMapGetters.put(VARIABLE_GETTER, () -> myCompiler.getVariables());
    myMapGetters.put(USER_COMMAND_GETTER, () -> myCompiler.getUserCommandStrings());
    myMapGetters.put(COLOR_PALETTE_GETTER, () -> myCompiler.getColorPaletteStrings());
    myMapGetters.put(TURTLE_GETTER, () -> myTurtleManager.getTurtleData());
    myMapGetters.put(DEFAULT_GETTER, () -> new HashMap<>());
  }

  /**
   * Function used to run a program
   *
   * @param program string containing slogo program to run
   * @throws Exception
   */
  public void runText(String program) throws Exception {
    Deque<Deque<Command>> commands = myCompiler.compile(program);
    while (!commands.isEmpty()) {
      Deque<Command> innerCommands = commands.removeFirst();
      myTurtleManager.executeCommandQueue(innerCommands);
    }
  }

  /**
   *
   * @param dataName "variables" for variable map and "userCommands" for user defined commands
   * @return
   */
  public Map<String, String> getMapData(String dataName) {
    return myMapGetters.getOrDefault(dataName, myMapGetters.get(DEFAULT_GETTER)).getMap();
  }

}
