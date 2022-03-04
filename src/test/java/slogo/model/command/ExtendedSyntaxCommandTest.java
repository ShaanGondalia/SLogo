package slogo.model.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.Main;
import slogo.model.command.math.Sum;
import slogo.model.command.turtle.Forward;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Tests for ExtendedSyntaxCommand
 *
 * @author Shaan Gondalia
 */
public class ExtendedSyntaxCommandTest {


  private static final double ARG_1 = 10;
  private static final double ARG_2 = 20;
  private static final double ARG_3 = 30;
  private static final double ARG_4 = 40;

  private static final int NUM_FORWARD_ARGS = 1;
  private static final int NUM_SUM_ARGS = 2;

  Turtle myTurtle;
  List<Value> args;


  @BeforeEach
  void setUp() throws MissingArgumentException {
    myTurtle = new Turtle();
    args = new ArrayList<>();
  }

  @Test
  void testForwardTwoArgs() throws MissingArgumentException {
    args.add(new Value(ARG_1));
    args.add(new Value(ARG_2));
    Command fd = new Forward(args);
    Command ex = new ExtendedSyntaxCommand(args, fd, NUM_FORWARD_ARGS);

    assertEquals(30, ex.execute(myTurtle).getVal());
  }



  @Test
  void testForwardFourArgs() throws MissingArgumentException {
    args.add(new Value(ARG_1));
    args.add(new Value(ARG_2));
    args.add(new Value(ARG_3));
    args.add(new Value(ARG_4));
    Command fd = new Forward(args);
    Command ex = new ExtendedSyntaxCommand(args, fd, NUM_FORWARD_ARGS);

    assertEquals(100, ex.execute(myTurtle).getVal());
  }

  @Test
  void testSumFourArgs() throws MissingArgumentException {
    args.add(new Value(ARG_1));
    args.add(new Value(ARG_2));
    args.add(new Value(ARG_3));
    args.add(new Value(ARG_4));
    Command sum = new Sum(args);
    Command ex = new ExtendedSyntaxCommand(args, sum, NUM_SUM_ARGS);

    assertEquals(100, ex.execute(myTurtle).getVal());
  }



}
