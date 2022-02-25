package slogo.model.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Stack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.command.Command;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.exception.SymbolNotFoundException;
import slogo.model.turtle.Turtle;

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
  private Turtle turtle;
  private CommandFactory commandFactory;

  @BeforeEach
  void setUp() {
    args = new Stack<>();
    args.add(new Value(ARG_1));
    turtle = new Turtle();
    commandFactory = new CommandFactory(LANGUAGE);
  }

  @Test
  void testBaseCommand() throws MissingArgumentException, SymbolNotFoundException {
    Command c = commandFactory.getCommand(FORWARD, turtle, args);
    assertEquals(ARG_1, c.execute().getVal());
  }

  @Test
  void testBaseNotEnoughArgs() {
    assertThrows(MissingArgumentException.class, () -> commandFactory.getCommand(SUM, turtle, args));
  }

  @Test
  void testUserCommand() throws MissingArgumentException, SymbolNotFoundException {
    commandFactory.makeCommand(GIBBERISH, 1);
    Command c =commandFactory.getCommand(GIBBERISH, turtle, args);
  }

  @Test
  void testUnknownCommand() {
    assertFalse(commandFactory.isCommand(GIBBERISH));
    assertThrows(SymbolNotFoundException.class, () -> commandFactory.getCommand(GIBBERISH, turtle, args));
  }


}
