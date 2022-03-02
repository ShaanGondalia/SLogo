package slogo.model.color;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ColorTest {

  @Test
  void colorTest() {
    Color c = new Color(200, 2, 3);
    assertEquals(c.toString(), "#c80203");
  }
}
