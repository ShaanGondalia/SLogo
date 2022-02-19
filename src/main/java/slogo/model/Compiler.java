package slogo.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.Stack;
import slogo.model.command.Command;
import slogo.model.command.math.Sum;
import slogo.model.exception.MissingArgumentException;
import slogo.model.parser.Parser;

public class Compiler {

  public static final String WHITESPACE = "\\s+";
  private static final String COMMAND_RESOURCES = "model.Command";

  private Turtle myTurtle;
  private Parser myParser;
  private Map<String, Double> myVariables;
  private ResourceBundle myCommandResources;

  public Compiler () {
    myParser = new Parser();
    myCommandResources = ResourceBundle.getBundle(COMMAND_RESOURCES);
    myParser.addPatterns("English");
    myParser.addPatterns("Syntax");
    myVariables = new HashMap<>();
    myTurtle = new Turtle();
  }

  public Queue<Command> compile(String program) throws MissingArgumentException {
    //Scanner scanner = new Scanner(input);

    Stack<String> pendingCommands = new Stack<>();
    Stack<Double> values = new Stack<>();

    // Split the input program by whitespace
    for (String token : program.split(WHITESPACE)) {
      String symbol = myParser.getSymbol(token);
      // If a token is in the list of known commands
      if (myCommandResources.containsKey(symbol)){
        pendingCommands.push(symbol);
      }
      // If a token is a constant
      else if(symbol.equals("Constant")) {
        values.push(Double.parseDouble(token));
      }
      // If a token is a variable
      else if(symbol.equals("Variable")) {
        values.push(myVariables.get(token));
      }
      int numInputs = getNumInputs(pendingCommands.peek());
      // If we have enough numbers to build the command, build it
      if (numInputs == values.size()) {
        // List of numbers to pass to command
        List<Double> doubleParameters = new ArrayList<>();
        for (int i = 0; i < numInputs; i++) {
          doubleParameters.add(values.pop());
        }
        // Use reflection to create command
        Command a = new Sum(myTurtle, doubleParameters);
      }
      System.out.printf("%s : %s\n", program, symbol);
    }
    return null;
  }

  // Gets the number of inputs a given command takes.
  private int getNumInputs(String command) {
    return Integer.parseInt(myCommandResources.getString(command));
  }
}
