package slogo.model;

import java.util.Deque;
import slogo.model.command.Command;
import slogo.model.compiler.Compiler;
import slogo.model.turtle.TurtleManager;

public class ModelTester {

  //public static final String PROGRAM = "make :random sum 1 random 100 fd :random";
  public static final String PROGRAM = "# move all\n"
      + "tell [ 1 2 3 4 5 6 7 8 ]\n"
      + "st\n"
      + "pd\n"
      + "rt random 360\n"
      + "fd 100\n"
      + "\n"
      + "# move some\n"
      + "askwith [ greater? xcor 50 ] [\n"
      + "  lt random 360\n"
      + "  bk 100\n"
      + "]\n"
      + "\n"
      + "# move all again\n"
      + "rt random 360\n"
      + "fd 100\n"
      + "\n"
      + "# move some again\n"
      + "ask [ 1 8 2 ] [ \n"
      + "  lt random 360\n"
      + "  bk 100\n"
      + "]\n"
      + "\n"
      + "# move a different few\n"
      + "tell [ 1 2 3 ]\n"
      + "rt random 360\n"
      + "fd 100\n";

  public static void main(String[] args) throws Exception {
    TurtleManager tm = new TurtleManager(1);
    Compiler c = new Compiler("English", tm);

    Deque<Deque<Command>> q = c.compile(PROGRAM);

    for (Deque<Command> innerQueue : q) {
      tm.executeCommandQueue(innerQueue);
    }
  }
}
