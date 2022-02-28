package slogo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import slogo.model.command.Command;
import slogo.model.compiler.Compiler;
import slogo.model.turtle.Turtle;
import slogo.model.compiler.Compiler;

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
    Compiler c = new Compiler("English");

    List<Turtle> turtles = new ArrayList<>();
    Turtle t = new Turtle();
    turtles.add(t);
    Queue<Command> q = c.compile(PROGRAM, turtles);

    while (!q.isEmpty()) {
      Command command = q.remove();
      System.out.println(command);
      command.execute();
      System.out.println(t.getPose());
    }
  }
}
