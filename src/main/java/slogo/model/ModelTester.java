package slogo.model;

import java.util.Deque;
import slogo.model.command.Command;
import slogo.model.compiler.Compiler;
import slogo.model.turtle.TurtleManager;

public class ModelTester {

  //public static final String PROGRAM = "make :random sum 1 random 100 fd :random";
  public static final String PROGRAM = "tell [ 1 2 3 ] ask [ 4 5 ] [ fd 50 ] fd 100";

  public static void main(String[] args) throws Exception {
    TurtleManager tm = new TurtleManager();
    Compiler c = new Compiler("English", tm);

    Deque<Deque<Command>> q = c.compile(PROGRAM);

    for (Deque<Command> innerQueue : q) {
      tm.executeCommandQueue(innerQueue);
    }
  }
}
