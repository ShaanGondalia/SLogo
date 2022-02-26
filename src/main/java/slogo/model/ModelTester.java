package slogo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import slogo.model.command.Command;
import slogo.model.turtle.Turtle;

public class ModelTester {

  //public static final String PROGRAM = "make :random sum 1 random 100 fd :random";
  public static final String PROGRAM = "if 0 [ fd 50 ]";

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
