package slogo.controller;

import java.beans.PropertyChangeListener;
import java.util.*;

import java.util.Map;
import slogo.model.command.Command;

import slogo.model.compiler.Compiler;
import slogo.model.turtle.Turtle;
import slogo.view.turtle.TurtleView;

public class Controller {

  private Compiler myCompiler;
  private List<Turtle> myTurtles;
  private List<TurtleView> myTurtleViews;
  private Map<String, MapGetter<String, String>> myMapGetters;

  public Controller(String lan) {
    myCompiler = new Compiler(lan);
    myTurtles = new ArrayList<>();
    myTurtleViews = new ArrayList<>();
    myMapGetters.put("variables", () -> myCompiler.getVariables());
    myMapGetters.put("userCommands", () -> myCompiler.getUserCommandStrings());
    myMapGetters.put("default", () -> new HashMap<>());
  }

  public void runText(String program) throws Exception {
    Deque<Command> commands = myCompiler.compile(program, myTurtles);
    while (!commands.isEmpty()){
      commands.removeFirst().execute();
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
