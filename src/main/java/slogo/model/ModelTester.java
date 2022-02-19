package slogo.model;

import java.util.Queue;
import slogo.model.command.Command;

import slogo.model.turtle.Turtle;

public class ModelTester {

  public static void main(String[] args) throws Exception {
    Compiler c = new Compiler("English");
    Turtle turtle = new Turtle();
    Queue<Command> q = c.compile("fd 50 + fd 50 50", turtle);
  }
}
