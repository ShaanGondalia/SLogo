package slogo.controller;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import slogo.model.command.Command;
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
    Deque<Command> commands = myCompiler.compile(program, myTurtles);
    for (Command c : commands) {
      c.execute();
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
}
