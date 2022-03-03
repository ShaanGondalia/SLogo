package slogo.model.command.multi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.Main;
import slogo.model.command.Command;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Pose;
import slogo.model.turtle.Turtle;
import slogo.model.turtle.TurtleManager;

/**
 * Tests for Tell Command
 *
 * @author Shaan Gondalia
 */
public class TellTest {

  private static final double ARG_1 = 50;
  private static final double ARG_2 = 100;

  private TurtleManager myTurtleManager;


  @BeforeEach
  void setUp() {
    myTurtleManager = new TurtleManager();
  }


}
