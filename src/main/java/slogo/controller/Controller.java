package slogo.controller;

import java.beans.PropertyChangeListener;
import java.util.*;

import java.util.Map;
import slogo.Errors;
import slogo.model.command.Command;

import slogo.model.compiler.Compiler;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;
import slogo.model.turtle.TurtleManager;
import slogo.view.turtle.TurtleView;
import slogo.view.turtle.TurtleViewManager;

public class Controller {

  private Compiler myCompiler;
  private TurtleManager myTurtleManager;
  private List<Turtle> myTurtles;
  private Map<String, MapGetter<String, String>> myMapGetters;

  public Controller(String lan, TurtleViewManager turtleViewManager) {
    myTurtleManager = new TurtleManager();
    myTurtleManager.addListener((PropertyChangeListener) turtleViewManager);
    myCompiler = new Compiler(lan, myTurtleManager);
    myTurtles = new ArrayList<>();
    myMapGetters = new HashMap<>();
    myMapGetters.put("variables", () -> myCompiler.getVariables());
    myMapGetters.put("userCommands", () -> myCompiler.getUserCommandStrings());
    myMapGetters.put("default", () -> new HashMap<>());
  }


  public void runText(String program) throws Exception {
    Deque<Deque<Command>> commands = myCompiler.compile(program);
    while (!commands.isEmpty()) {
      Deque<Command> innerCommands = commands.removeFirst();
      myTurtleManager.executeCommandQueue(innerCommands);
    }
  }

  public void addTurtle(PropertyChangeListener turtleView) {
    Turtle turtle = new Turtle();
    myTurtles.add(turtle);
    turtle.addListener(turtleView);
  }

  public List<Turtle> getTurtles() {
    return myTurtles;
  }

  /**
   *
   * @param dataName "variables" for variable map and "userCommands" for user defined commands
   * @return
   */
  public Map<String, String> getMapData(String dataName) {
    return myMapGetters.getOrDefault(dataName, myMapGetters.get("default")).getMap();
  }

}
