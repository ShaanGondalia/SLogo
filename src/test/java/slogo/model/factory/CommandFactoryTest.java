package slogo.model.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Deque;
import java.util.Stack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.color.ColorPalette;
import slogo.model.command.Command;
import slogo.model.command.Value;
import slogo.model.compiler.CommandFactory;
import slogo.model.exception.MissingArgumentException;
import slogo.model.exception.SymbolNotFoundException;
import slogo.model.turtle.Turtle;
import slogo.model.turtle.TurtleManager;

/**
 * Tests for CommandFactory class
 *
 * @author Shaan Gondalia
 */
public class CommandFactoryTest {

  private static final String LANGUAGE = "English";
  private static final String GIBBERISH = "asdfXCVd";
  private static final String FORWARD = "Forward";
  private static final String SUM = "Sum";
  private static final double ARG_1 = 50;

  private Stack<Value> args;
  private Stack<Deque<Command>> lists;
  private Turtle turtle;
  private CommandFactory commandFactory;


  @BeforeEach
  void setUp() {
    args = new Stack<>();
    args.add(new Value(ARG_1));
    lists = new Stack<>();
    lists.add(null);
    turtle = new Turtle();
    ColorPalette myColorPalette = new ColorPalette();
    TurtleManager myTurtleManager = new TurtleManager();
    commandFactory = new CommandFactory(LANGUAGE, myTurtleManager, myColorPalette);
  }

  @Test
  void testBaseCommand() throws MissingArgumentException, SymbolNotFoundException {
    Command c = commandFactory.getCommand(FORWARD, args, lists, 1);
    assertEquals(ARG_1, c.execute(turtle).getVal());
  }

  @Test
  void testBaseNotEnoughArgs() {
    assertThrows(MissingArgumentException.class, () -> commandFactory.getCommand(SUM, args, lists, 2));
  }

  @Test
  void testUserCommand() throws MissingArgumentException, SymbolNotFoundException {
    commandFactory.makeUserCommand(GIBBERISH, 1);
  }

  @Test
  void testUnknownCommand() {
    assertFalse(commandFactory.isCommand(GIBBERISH));
    assertThrows(SymbolNotFoundException.class, () -> commandFactory.getNumInputs(GIBBERISH));
  }


}
