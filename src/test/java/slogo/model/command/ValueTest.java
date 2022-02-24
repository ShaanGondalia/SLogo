package slogo.model.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.command.Value;

/**
 * Tests for Value object
 *
 * @author Jake Heller
 */
public class ValueTest {

  @Test
  void testValue() {
    Value a = new Value(4.5);
    double b = a.getVal();
    b++;
    System.out.printf("b, a.val: %f, %f", b, a.getVal());
  }
}
