package slogo.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.controller.Controller;
import util.DukeApplicationTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CSSSplashTest extends DukeApplicationTest {

  LanguageSplash languageSplash;
  Controller c;
  CSSSplash window;
  TurtleView tv;

  @BeforeEach
  void setUp() {
    languageSplash = new LanguageSplash();
    c = new Controller(languageSplash.toString());
    window = new CSSSplash(languageSplash.toString());
  }

  @Test
  void createStage() {
    assertTrue(true);
  }

  @Test
  void styleSheetToUse() {
    assertTrue(window.toString().equals(""));
  }
}