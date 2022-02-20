package slogo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
  private static final String TWO_ARG_PROGRAM = "fd + 50 50";
  private static final String MULTI_PROGRAM = "fd + fd 50 50";
  private static final String COMPLEX_PROGRAM = "fd + fd 50 50 lt sum 45 45";
  private static final String LANGUAGE = "English";

  private Turtle myTurtle;
  private Compiler compiler;

  @BeforeEach
  void setUp() {
    myTurtle = new Turtle();
    compiler = new Compiler(LANGUAGE);
  }

  @Test
  void testIncorrect() {
    assertThrows(MissingArgumentException.class, () -> compiler.run(INCORRECT_PROGRAM, myTurtle));
  }

  @Test
  void testBasic() throws Exception {
    compiler.run(BASIC_PROGRAM, myTurtle);
    assertEquals(50, myTurtle.getPose().y());
  }

  @Test
  void testTwoArg() throws Exception {
    compiler.run(TWO_ARG_PROGRAM, myTurtle);
    assertEquals(100, myTurtle.getPose().y());
  }

  @Test
  void testMulti() throws Exception {
    compiler.run(MULTI_PROGRAM, myTurtle);
    assertEquals(150, myTurtle.getPose().y());
  }

  @Test
  void testComplex() throws Exception {
    compiler.run(COMPLEX_PROGRAM, myTurtle);
    assertEquals(150, myTurtle.getPose().y());
    assertEquals(270, myTurtle.getPose().bearing());
  }

  @Test
  void testUnknown() throws Exception {
    assertThrows(SymbolNotFoundException.class, () -> compiler.run(UNKNOWN_PROGRAM, myTurtle));
  }

  @Test
  void testParameterOrder() throws Exception {
    String program = "sum 50 fd";
    assertThrows(MissingArgumentException.class, () -> compiler.compile(program, myTurtle));
  }

}
