package slogo.controller;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import slogo.view.TurtleView;
import slogo.model.Compiler;
import slogo.model.turtle.Turtle;

public class Controller {

  slogo.model.Compiler myCompiler;
  List<Turtle> myTurtles;

  public Controller(String lan) {
    myCompiler = new Compiler(lan);
    myTurtles = new ArrayList<>();
  }

  public void runText(String program) throws Exception {
    myCompiler.compile(program, myTurtles);
  }

  public void addTurtle(PropertyChangeListener turtleView) {
    Turtle turtle = new Turtle();
    myTurtles.add(turtle);
    turtle.addListener(turtleView);
  }

  public List<Turtle> getTurtles() {
    return myTurtles;
  }
}
