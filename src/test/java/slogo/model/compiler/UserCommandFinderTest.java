package slogo.model.compiler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.Main;

/**
 * Tests for UserCommandFinder
 *
 * @author Jake Heller
 */
public class UserCommandFinderTest {

  Main m = new Main();
  Parser myParser;
  @BeforeEach
  void setUp() {
    myParser = new Parser("English");
    //myParser.addPatterns("English");
    myParser.addPatterns("Syntax");
  }

  @Test
  void test() {
    String program = m.getExampleProgram("procedures", "pinwheel");
    // squiggle, pinwheel
    Set<String> compiledCommands = new HashSet<>();
    compiledCommands.add("squiggle");
    compiledCommands.add("pinwheel");
    Map<String, String> userCommands = UserCommandFinder.findUserCommands(program, myParser, compiledCommands);
    assertEquals(2, userCommands.keySet().size());
    for (String name: userCommands.keySet()) {
      System.out.printf("%s: %s\n\n", name, userCommands.get(name));
    }
  }

  @Test
  void test1() {
    String program = "forward sum 20 30";
    // squiggle, pinwheel
    Set<String> compiledCommands = new HashSet<>();
//    compiledCommands.add("squiggle");
//    compiledCommands.add("pinwheel");
    Map<String, String> userCommands = UserCommandFinder.findUserCommands(program, myParser, compiledCommands);
    assertEquals(0, userCommands.keySet().size());
    for (String name: userCommands.keySet()) {
      System.out.printf("%s: %s\n\n", name, userCommands.get(name));
    }
  }
}
