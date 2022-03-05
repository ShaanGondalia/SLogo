package slogo.model.compiler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.Main;
import slogo.model.color.ColorPalette;
import slogo.model.turtle.Turtle;
import slogo.model.turtle.TurtleManager;

public class VariablesListTest {
  ColorPalette myColorPalette;
  TurtleManager myTurtleManager;
  Compiler myCompiler;
  List<Turtle> myTurtles;

  @BeforeEach
  void setup () {
    myTurtleManager = new TurtleManager();
    myColorPalette = new ColorPalette();
    myCompiler = new Compiler("English", myTurtleManager, myColorPalette);
    myTurtles = new ArrayList<>();
    myTurtles.add(new Turtle());
  }

  @Test
  void getVariableTest () throws Exception {
    String program = "make :a 20.0142\n "
        + "make :b 30";

    myCompiler.compile(program);

    Map<String, String> variables = myCompiler.getVariables();
    for (String name: variables.keySet()) {
      System.out.printf("%s : %s\n", name, variables.get(name));
    }
  }
}
