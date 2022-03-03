package slogo.model;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import slogo.model.command.Command;
import slogo.model.compiler.Compiler;
import slogo.model.turtle.Turtle;
import slogo.model.turtle.TurtleManager;

public class ModelTester {

  //public static final String PROGRAM = "make :random sum 1 random 100 fd :random";
  public static final String PROGRAM = "to dash [ :count :distance ]\n"
      + "[\n"
      + "  repeat :count \n"
      + "  [\n"
      + "    pu fd :distance pd fd :distance\n"
      + "  ]      \n"
      + "]\n"
      + "\n"
      + "cs\n"
      + "\n"
      + "dash 10 20\n"
      + "rt 120\n"
      + "dash 20 10\n"
      + "rt 120\n"
      + "dash 40 5\n";

  public static void main(String[] args) throws Exception {
    TurtleManager tm = new TurtleManager();
    Compiler c = new Compiler("English", tm);

    List<Turtle> turtles = new ArrayList<>();
    Deque<Deque<Command>> q = c.compile(PROGRAM);

    for (Deque<Command> innerQueue : q) {
      for (Turtle t : tm.getFollowingTurtles()) {
        for (Command command : innerQueue) {
          System.out.println(command);
          command.execute(t);
          System.out.println(t.getPose());
        }
      }
    }
  }
}
