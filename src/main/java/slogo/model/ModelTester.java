package slogo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import slogo.model.command.Command;
import slogo.model.command.turtle.Forward;
import slogo.model.exception.MissingArgumentException;

public class ModelTester {

  public static void main(String[] args) throws MissingArgumentException {
    Compiler c = new Compiler();
    Queue<Command> q = c.compile("fd 50 + fd 50 50");
  }
}
