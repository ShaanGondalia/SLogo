package slogo.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.Stack;
import slogo.model.command.Command;
import slogo.model.command.Value;
import slogo.model.command.control.MakeUserInstruction;
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
   * @param turtles  list of turtles to attach commands to
   * @throws Exception if there is an issue running the program
   */
  public Deque<Command> compile(String program, List<Turtle> turtles) throws Exception {
    Deque<Command> commandQueue = new LinkedList<>();
    Stack<Deque<Command>> completedQueues = new Stack<>();

    // will be changed when we can have multiple turtles
    Turtle turtle = turtles.get(0);

    Stack<String> pendingCommands = new Stack<>();
    Stack<Value> values = new Stack<>();
    Stack<Deque<Command>> lists = new Stack<>();
    Stack<Integer> valuesBefore = new Stack<>();
    Stack<Integer> listsBefore = new Stack<>();
    Stack<Deque<Command>> queueStack = new Stack<>();

    for (String token : program.split(WHITESPACE)) {
      String symbol = myParser.getSymbol(token);
      System.out.println(symbol);

      if (commandFactory.isCommand(symbol)) {
        System.out.println("HI");
        pendingCommands.push(symbol);
        valuesBefore.push(values.size());
        listsBefore.push(lists.size());
      } else if (symbol.equals("Constant")) {
        values.push(new Value(Double.parseDouble(token)));
      } else if (symbol.equals("Variable")) {
        if (!myVariables.containsKey(token)) {
          myVariables.put(token, new Value());
        }
        values.push(myVariables.get(token));
      } else if (symbol.equals("UserCommand")) {
        if (pendingCommands.peek().equals("MakeUserInstruction")) {
          // TODO: figure out how make user instruction works
          int inputs = 0; // need to figure out how many inputs user instruction takes
          commandFactory.makeCommand(token, inputs);
        } else {
          throw new SymbolNotFoundException(String.format(exceptionResources.getString("SymbolNotFound"), symbol));
        }
      } else if (symbol.equals("ListStart")) {
        queueStack.push(new LinkedList<>());
      } else if (symbol.equals("ListEnd")) {
        lists.push(queueStack.pop());
      }

      // LOGIC:
      // After a command is added, we can only resolve the commands arguments once numInputs values have been added to the value stack.
      // We must keep track of how many values have been added to the stack after each command has been added.
      // We know the number of inputs each command requires, and we know the size of the values stack when each command is added.
      // We can create another data structure that tracks this information, and use it to determine when

      while (!pendingCommands.isEmpty()
          && commandFactory.getNumInputs(pendingCommands.peek()) <= values.size() - valuesBefore.peek()
          && commandFactory.getNumListInputs(pendingCommands.peek()) <= lists.size() - listsBefore.peek()) {
        String pendingCommand = pendingCommands.pop();
        valuesBefore.pop();
        listsBefore.pop();
        Command command = commandFactory.getCommand(pendingCommand, turtle, values);
        values.add(command.returnValue());
        commandQueue.addLast(command);
        if (pendingCommands.isEmpty()) {
          values.clear();
        }
      }
    }
    if (!pendingCommands.empty()) {
      throw new MissingArgumentException(
          String.format(exceptionResources.getString("MissingArgument"), pendingCommands.peek()));
    }
    return commandQueue;
  }

}
