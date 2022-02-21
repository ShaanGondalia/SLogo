package slogo.controller;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import slogo.View.TurtleView;
import slogo.model.Compiler;
import slogo.model.turtle.Turtle;

public class Controller {

  slogo.model.Compiler myCompiler;
  List<Turtle> myTurtles;

  public Controller(Compiler c) {
    myCompiler = c;
    myTurtles = new ArrayList<>();
  }

  public void runText(String program) throws Exception {
    myCompiler.run(program, myTurtles);
  }

  public void addTurtle(Turtle turtle, PropertyChangeListener turtleView) {
    myTurtles.add(turtle);
    turtle.addListener(turtleView);
  }
}
