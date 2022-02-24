package slogo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import slogo.model.command.Command;
import slogo.model.turtle.Turtle;

public class ModelTester {

  public static final String PROGRAM = "CS PU HT TOWARDS 50 SUM 50 XCOR BK 100 PD FD * PI 100";
  //public static final String PROGRAM = "+ 50 fd 75";

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
