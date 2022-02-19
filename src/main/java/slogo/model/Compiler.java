package slogo.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import slogo.model.command.Command;
import slogo.model.command.math.Sum;
import slogo.model.parser.Parser;

public class Compiler {

  public static final String WHITESPACE = "\\s+";

  private Parser myParser;
  private Map<String, Double> myVariables;

  public Compiler () {
    myVariables = new HashMap<>();
  }

  public Queue<Command> compile(Parser parser, String program) {
    //Scanner scanner = new Scanner(input);
    Stack<String> pendingCommands = new Stack<>();
    Stack<Double> values = new Stack<>();

    for (String token : program.split(WHITESPACE)) {
      if (inputs(pendingCommands.peek()) == values.size()) {
        List<Double> doubleParameters = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
          doubleParameters.add(values.pop());
        }
        Command a = new Sum(doubleParameters);
      }
      String symbol = parser.getSymbol(program);
      System.out.printf("%s : %s\n", program, symbol);
    }
    return null;
  }

  private int inputs(String peek) {
    return 2;
  }
}
