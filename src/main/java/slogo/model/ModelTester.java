package slogo.model;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import slogo.model.command.Command;
import slogo.model.command.Value;
import slogo.model.compiler.Compiler;
import slogo.model.turtle.Turtle;
import slogo.model.turtle.TurtleManager;

public class ModelTester {

  //public static final String PROGRAM = "make :random sum 1 random 100 fd :random";
  public static final String PROGRAM = "tell [ 1 2 3 ] set :x 10 fd * id :x";

  public static void main(String[] args) throws Exception {
    TurtleManager tm = new TurtleManager();
    Compiler c = new Compiler("English", tm);

    Deque<Deque<Command>> q = c.compile(PROGRAM);

    for (Deque<Command> innerQueue : q) {
      tm.executeCommandQueue(innerQueue);
    }
  }
}
