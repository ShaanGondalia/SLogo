package slogo.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.controller.Controller;
import util.DukeApplicationTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SplashViewTest extends DukeApplicationTest {

  LanguageProbe languageProbe;
  Controller c;
  SplashView window;
  TurtleView tv;

  @BeforeEach
  void setUp() {
    languageProbe = new LanguageProbe();
    c = new Controller(languageProbe.toString());
    window = new SplashView();
  }

  @Test
  void createStage() {
    window.createStage("English", c);
    assertTrue(true);
  }

  @Test
  void styleSheetToUse() {
    assertTrue(window.styleSheetToUse().equals(""));
  }
}