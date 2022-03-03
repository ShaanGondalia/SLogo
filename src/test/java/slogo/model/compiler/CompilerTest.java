package slogo.model.compiler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.Main;
import slogo.model.command.Command;
import slogo.model.exception.MissingArgumentException;
import slogo.model.exception.SymbolNotFoundException;
import slogo.model.turtle.Turtle;
import slogo.model.turtle.TurtleManager;

/**
 * Tests for Compiler class
 *
 * @author Shaan Gondalia
 */
public class CompilerTest {

  private static final String BASIC_PROGRAM = "fd 50";
  private static final String INCORRECT_PROGRAM = "fd + 50";
  private static final String UNKNOWN_PROGRAM = "xz 50";
  private static final String TWO_ARG_PROGRAM = "fd + 50 50";
  private static final String MULTI_PROGRAM = "fd + fd 50 50";
  private static final String COMPLEX_PROGRAM = "fd + fd 50 50 lt sum 45 45";
  private static final String LOOP_PROGRAM = "dotimes [ :dist 200 ] [  fd :dist rt 89 ]";
  private static final String PROCEDURE_PROGRAM = "set :count 12\n"
      + "set :distance 20\n"
      + "\n"
      + "to dash [ ]\n"
      + "[\n"
      + "  repeat :count \n"
      + "  [\n"
      + "    pu fd :distance pd fd :distance\n"
      + "  ]\n"
      + "]\n"
      + "\n"
      + "\n"
      + "dash";
  private static final String PROCEDURE_PROGRAM_ARGS = "to dash [ :count :distance ]\n"
      + "[\n"
      + "  repeat :count \n"
      + "  [\n"
      + "    pu fd :distance pd fd :distance\n"
      + "  ]      \n]\n\n"
      + "cs\n\ndash 10 20\nrt 120\ndash 20 10\nrt 120\ndash 40 5\n";


  private static final String LANGUAGE = "English";

  TurtleManager myTurtleManager;
  private Compiler compiler;

  @BeforeEach
  void setUp() {
    myTurtleManager = new TurtleManager();
    compiler = new Compiler(LANGUAGE, myTurtleManager);
  }

  @Test
  void testIncorrect() {
    assertThrows(MissingArgumentException.class,
        () -> compiler.compile(INCORRECT_PROGRAM));
  }

  @Test
  void testBasic() throws Exception {
    run(compiler.compile(BASIC_PROGRAM));
    assertEquals(50, myTurtleManager.getFollowingTurtles().get(0).getPose().y());
  }

  @Test
  void testTwoArg() throws Exception {
    run(compiler.compile(TWO_ARG_PROGRAM));
    assertEquals(100, myTurtleManager.getFollowingTurtles().get(0).getPose().y());
  }

  @Test
  void testMulti() throws Exception {
    run(compiler.compile(MULTI_PROGRAM));
    assertEquals(150, myTurtleManager.getFollowingTurtles().get(0).getPose().y());
  }

  @Test
  void testComplex() throws Exception {
    run(compiler.compile(COMPLEX_PROGRAM));
    assertEquals(150, myTurtleManager.getFollowingTurtles().get(0).getPose().y());
    assertEquals(270, myTurtleManager.getFollowingTurtles().get(0).getPose().bearing());
  }

  @Test
  void testLoop() throws Exception {
    run(compiler.compile(LOOP_PROGRAM));
    assertEquals(62.4117, myTurtleManager.getFollowingTurtles().get(0).getPose().x(), Main.TOLERANCE);
    assertEquals(128.9302, myTurtleManager.getFollowingTurtles().get(0).getPose().y(), Main.TOLERANCE);
    assertEquals(160, myTurtleManager.getFollowingTurtles().get(0).getPose().bearing(), Main.TOLERANCE);
  }

  @Test
  void testProcedure() throws Exception {
    run(compiler.compile(PROCEDURE_PROGRAM));
    assertEquals(0, myTurtleManager.getFollowingTurtles().get(0).getPose().x(), Main.TOLERANCE);
    assertEquals(480, myTurtleManager.getFollowingTurtles().get(0).getPose().y(), Main.TOLERANCE);
    assertEquals(0, myTurtleManager.getFollowingTurtles().get(0).getPose().bearing(), Main.TOLERANCE);
  }

  @Test
  void testProcedureArgs() throws Exception {
    run(compiler.compile(PROCEDURE_PROGRAM_ARGS));
    assertEquals(0, myTurtleManager.getFollowingTurtles().get(0).getPose().x(), Main.TOLERANCE);
    assertEquals(0, myTurtleManager.getFollowingTurtles().get(0).getPose().y(), Main.TOLERANCE);
    assertEquals(240, myTurtleManager.getFollowingTurtles().get(0).getPose().bearing(), Main.TOLERANCE);
  }

  @Test
  void testUnknown() throws Exception {
    assertThrows(SymbolNotFoundException.class, () -> compiler.compile(UNKNOWN_PROGRAM));
  }

  @Test
  void testParameterOrder() throws Exception {
    String program = "sum 50 fd";
    assertThrows(MissingArgumentException.class, () -> compiler.compile(program));
  }

  private void run(Deque<Deque<Command>> q) throws MissingArgumentException {
    System.out.println(q);
    for (Deque<Command> innerQueue : q) {
      for (Command command : innerQueue) {
        command.execute(myTurtleManager.getFollowingTurtles().get(0));
      }
    }
  }

}
