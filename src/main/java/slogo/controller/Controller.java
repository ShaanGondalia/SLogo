package slogo.controller;

import java.beans.PropertyChangeListener;
import java.util.*;

import java.util.Map;
import slogo.Errors;
import slogo.model.command.Command;

import slogo.model.compiler.Compiler;
import slogo.model.turtle.Turtle;
import slogo.model.turtle.TurtleManager;
import slogo.view.turtle.TurtleView;

public class Controller {

  private Compiler myCompiler;
  private TurtleManager myTurtleManager;
  private List<Turtle> myTurtles;
  private List<TurtleView> myTurtleViews;
  private Map<String, MapGetter<String, String>> myMapGetters;

  public Controller(String lan) {
    myTurtleManager = new TurtleManager();
    myCompiler = new Compiler(lan, myTurtleManager);
    myTurtles = new ArrayList<>();
    myTurtleViews = new ArrayList<>();
    myMapGetters = new HashMap<>();
    myMapGetters.put("variables", () -> myCompiler.getVariables());
    myMapGetters.put("userCommands", () -> myCompiler.getUserCommandStrings());
    myMapGetters.put("default", () -> new HashMap<>());
  }

  public void runText(String program){
    try {
      Deque<Deque<Command>> commands = myCompiler.compile(program);
      while (!commands.isEmpty()) {
        Deque<Command> innerCommands = commands.removeFirst();
        myTurtleManager.executeCommandQueue(innerCommands);
      }
    } catch (Exception e){
      Errors.showError(e.getMessage());
    }
  }

  public void addTurtle(PropertyChangeListener turtleView) {
    myTurtleViews.add((TurtleView) turtleView);
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

  public List<TurtleView> getTurtleViews() {
    return myTurtleViews;
  }
}
