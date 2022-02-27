package slogo.model;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Stack;
import slogo.model.command.Command;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.exception.SymbolNotFoundException;
import slogo.model.factory.CommandFactory;
import slogo.model.parser.Parser;
import slogo.model.turtle.Turtle;

/**
 * Compiles user input into a queue of commands that can be executed. Depends on Turtle, Parser, and
 * Command.
 *
 * @author Jake Heller and Shaan Gondalia
 */
public class Compiler {

  public static final String WHITESPACE = "\\s+";
  private static final String EXCEPTION_RESOURCES = "model.exception.";

  private Parser myParser;
  private Map<String, Value> myVariables;

  private CommandFactory commandFactory;
  private final ResourceBundle exceptionResources;

  private Stack<String> pendingCommandsInContext = new Stack<>();
  private Stack<Value> valuesInContext = new Stack<>();
  private Stack<Deque<Command>> listsInContext = new Stack<>();

  private Stack<Integer> valuesBeforeInContext = new Stack<>();
  private Stack<Integer> listsBeforeInContext = new Stack<>();

  private Stack<Deque<Command>> commandQueuesInContext = new Stack<>();


  /**
   * Creates an instance of a compiler for the given language.
   */
  public Compiler(String language) {
    exceptionResources = ResourceBundle.getBundle(EXCEPTION_RESOURCES + language);
    myParser = new Parser(language);
    myParser.addPatterns(language);
    myParser.addPatterns("Syntax");
    myVariables = new HashMap<>();
    commandFactory = new CommandFactory(language);
  }

  /**
   * Compiles and runs a program.
   *
   * @param program the string input of the program
   * @param turtles list of turtles to attach commands to
   * @throws Exception if there is an issue running the program
   */
  public Deque<Command> compile(String program, List<Turtle> turtles) throws Exception {
    Deque<Command> commandQueue = new LinkedList<>();
    Stack<Deque<Command>> completedQueues = new Stack<>();

    // will be changed when we can have multiple turtles
    Turtle turtle = turtles.get(0);

    for (String token : program.split(WHITESPACE)) {

      handleToken(token);
      // LOGIC:
      // After a command is added, we can only resolve the commands arguments once numInputs values have been added to the value stack.
      // We must keep track of how many values have been added to the stack after each command has been added.
      // We know the number of inputs each command requires, and we know the size of the values stack when each command is added.
      // We can create another data structure that tracks this information, and use it to determine when

      while (!pendingCommandsInContext.isEmpty()
          && commandFactory.getNumInputs(pendingCommandsInContext.peek())
          <= valuesInContext.size() - valuesBeforeInContext.peek()
          && commandFactory.getNumListInputs(pendingCommandsInContext.peek())
          <= listsInContext.size() - listsBeforeInContext.peek()) {
        String pendingCommand = pendingCommandsInContext.pop();
        valuesBeforeInContext.pop();
        listsBeforeInContext.pop();
        Command command = commandFactory.getCommand(pendingCommand, turtle, valuesInContext, listsInContext);
        valuesInContext.add(command.returnValue());
        //System.out.println(pendingCommand);
        //values.forEach((value) -> System.out.println(value));
        if (!commandQueuesInContext.empty()) {
          commandQueuesInContext.peek().addLast(command);
        } else {
          commandQueue.addLast(command);
        }
        if (pendingCommandsInContext.isEmpty()) {
          valuesInContext.clear();
        }
      }
    }
    if (!pendingCommandsInContext.empty()) {
      throw new MissingArgumentException(
          String.format(exceptionResources.getString("MissingArgument"), pendingCommandsInContext.peek()));
    }
    return commandQueue;
  }


  private void handleToken(String token) throws SymbolNotFoundException {
    String symbol = myParser.getSymbol(token);

    if (commandFactory.isCommand(symbol)) {
      pendingCommandsInContext.push(symbol);
      valuesBeforeInContext.push(valuesInContext.size());
      listsBeforeInContext.push(listsInContext.size());
    } else if (symbol.equals("Constant")) {
      valuesInContext.push(new Value(Double.parseDouble(token)));
    } else if (symbol.equals("Variable")) {
      if (!myVariables.containsKey(token)) {
        myVariables.put(token, new Value());
      }
      valuesInContext.push(myVariables.get(token));
    } else if (symbol.equals("UserCommand")) {
      if (pendingCommandsInContext.peek().equals("MakeUserInstruction")) {
        // TODO: figure out how make user instruction works
        int inputs = 0; // need to figure out how many inputs user instruction takes
        commandFactory.makeCommand(token, inputs);
      } else {
        throw new SymbolNotFoundException(
            String.format(exceptionResources.getString("SymbolNotFound"), symbol));
      }
    } else if (symbol.equals("ListStart")) {
      commandQueuesInContext.push(new LinkedList<>());
    } else if (symbol.equals("ListEnd")) {
      Deque<Command> commandList = commandQueuesInContext.pop();
      if(!commandList.isEmpty()) {
        listsInContext.push(commandList);
      }
    }
  }



}
