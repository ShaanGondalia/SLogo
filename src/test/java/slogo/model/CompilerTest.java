package slogo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.Main;
import slogo.model.command.Command;
import slogo.model.compiler.Compiler;
import slogo.model.exception.MissingArgumentException;
import slogo.model.exception.SymbolNotFoundException;
import slogo.model.turtle.Turtle;

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
  public static final String LOOP_PROGRAM = "dotimes [ :dist 200 ] [  fd :dist rt 89 ]";
  private static final String LANGUAGE = "English";

  private List<Turtle> myTurtles;
  private Compiler compiler;

  @BeforeEach
  void setUp() {
    myTurtles = new ArrayList<>();
    myTurtles.add(new Turtle());
    compiler = new Compiler(LANGUAGE);
  }

  @Test
  void testIncorrect() {
    assertThrows(MissingArgumentException.class,
        () -> compiler.compile(INCORRECT_PROGRAM, myTurtles));
  }

  @Test
  void testBasic() throws Exception {
    run(compiler.compile(BASIC_PROGRAM, myTurtles));
    assertEquals(50, myTurtles.get(0).getPose().y());
  }

  @Test
  void testTwoArg() throws Exception {
    run(compiler.compile(TWO_ARG_PROGRAM, myTurtles));
    assertEquals(100, myTurtles.get(0).getPose().y());
  }

  @Test
  void testMulti() throws Exception {
    run(compiler.compile(MULTI_PROGRAM, myTurtles));
    assertEquals(150, myTurtles.get(0).getPose().y());
  }

  @Test
  void testComplex() throws Exception {
    run(compiler.compile(COMPLEX_PROGRAM, myTurtles));
    assertEquals(150, myTurtles.get(0).getPose().y());
    assertEquals(270, myTurtles.get(0).getPose().bearing());
  }

  @Test
  void testLoop() throws Exception {
    run(compiler.compile(LOOP_PROGRAM, myTurtles));
    assertEquals(62.4117, myTurtles.get(0).getPose().x(), Main.TOLERANCE);
    assertEquals(128.9302, myTurtles.get(0).getPose().y(), Main.TOLERANCE);
    assertEquals(160, myTurtles.get(0).getPose().bearing(), Main.TOLERANCE);
  }

  @Test
  void testUnknown() throws Exception {
    assertThrows(SymbolNotFoundException.class, () -> compiler.compile(UNKNOWN_PROGRAM, myTurtles));
  }

  @Test
  void testParameterOrder() throws Exception {
    String program = "sum 50 fd";
    assertThrows(MissingArgumentException.class, () -> compiler.compile(program, myTurtles));
  }

  private void run(Queue<Command> q) throws MissingArgumentException {
    while (!q.isEmpty()) {
      Command command = q.remove();
      command.execute();
    }
  }

}
