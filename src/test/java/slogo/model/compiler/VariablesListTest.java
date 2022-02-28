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
import slogo.model.turtle.Turtle;

public class VariablesListTest {

  Compiler myCompiler;
  List<Turtle> myTurtles;

  @BeforeEach
  void setup () {
    myCompiler = new Compiler("English");
    myTurtles = new ArrayList<>();
    myTurtles.add(new Turtle());
  }

  @Test
  void getVariableTest () throws Exception {
    String program = "make :a 20.0142\n "
        + "make :b 30";

    myCompiler.compile(program, myTurtles);

    Map<String, String> variables = myCompiler.getVariables();
    for (String name: variables.keySet()) {
      System.out.printf("%s : %s\n", name, variables.get(name));
    }
  }
}
