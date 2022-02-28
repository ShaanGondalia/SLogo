package slogo.controller;

import java.beans.PropertyChangeListener;
import java.util.*;

import slogo.model.command.Command;

import slogo.model.compiler.Compiler;
import slogo.model.turtle.Turtle;

public class Controller {

  private Compiler myCompiler;
  private List<Turtle> myTurtles;
  private List<TurtleView> myTurtleViews;

  public Controller(String lan) {
    myCompiler = new Compiler(lan);
    myTurtles = new ArrayList<>();
    myTurtleViews = new ArrayList<>();
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

  public List<TurtleView> getTurtleViews() {
    return myTurtleViews;
  }
}
