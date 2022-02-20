package slogo.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Queue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.command.Command;
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
  private static final String UNKNOWN_PROGRAM = "xz' 50";
  private static final String TWO_ARG_PROGRAM = "+ 50 50";
  private static final String MULTI_PROGRAM = "fd + fd 50 50";
  private static final String COMPLEX_PROGRAM = "fd + fd 50 50 lt sum 45 45";
  private static final String LANGUAGE = "English";

  private Turtle myTurtle;
  private Compiler compiler;

  @BeforeEach
  void setUp(){
    myTurtle = new Turtle();
    compiler = new Compiler(LANGUAGE);
  }

  @Test
  void testIncorrect() {
    assertThrows(MissingArgumentException.class, () -> compiler.compile(INCORRECT_PROGRAM, myTurtle));
  }

  @Test
  void testBasic() throws Exception {
    Queue<Command> q = compiler.compile(BASIC_PROGRAM, myTurtle);
    assertEquals(1, q.size());
  }

  @Test
  void testTwoArg() throws Exception {
    Queue<Command> q = compiler.compile(TWO_ARG_PROGRAM, myTurtle);
    assertEquals(1, q.size());
  }

  @Test
  void testMulti() throws Exception {
    Queue<Command> q = compiler.compile(MULTI_PROGRAM, myTurtle);
    assertEquals(3, q.size());
  }

  @Test
  void testComplex() throws Exception {
    Queue<Command> q = compiler.compile(COMPLEX_PROGRAM, myTurtle);
    assertEquals(5, q.size());
  }

  @Test
  void testUnknown() throws Exception {
    assertThrows(SymbolNotFoundException.class, () -> compiler.compile(UNKNOWN_PROGRAM, myTurtle));
  }

}
