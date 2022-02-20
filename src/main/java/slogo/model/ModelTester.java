package slogo.model;

import java.util.Queue;
import slogo.model.command.Command;

import slogo.model.turtle.Turtle;

public class ModelTester {

  public static final String PROGRAM = "fd + fd 50 50";

  public static void main(String[] args) throws Exception {
    Compiler c = new Compiler("English");
    Turtle turtle = new Turtle();
    Queue<Command> q = c.compile(PROGRAM, turtle);
    System.out.printf("x: %f, y: %f, b: %f\n", turtle.getPose().x(), turtle.getPose().y(), turtle.getPose().bearing());
    while (!q.isEmpty()){
      System.out.println(q.remove().execute());

      System.out.printf("x: %f, y: %f, b: %f\n", turtle.getPose().x(), turtle.getPose().y(), turtle.getPose().bearing());
    }
  }
}
