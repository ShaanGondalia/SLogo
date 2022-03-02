package slogo.model.compiler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParserTest {
  Parser myParser;

  @BeforeEach
  void setUp() {
    myParser = new Parser("English");
    myParser.addPatterns("English");
    myParser.addPatterns("Syntax");
  }

  @Test
  void testGetCommand() {
    String symbol1 = myParser.getSymbol("fd");
    String symbol2 = myParser.getSymbol("to");
    assertEquals("Forward", symbol1);
    assertEquals("MakeUserInstruction", symbol2);
  }
}
