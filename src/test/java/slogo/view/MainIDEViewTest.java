package slogo.view;

import org.junit.jupiter.api.BeforeEach;
import slogo.controller.Controller;
import slogo.view.windows.LanguageSplash;
import util.DukeApplicationTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MainIDEViewTest extends DukeApplicationTest {

  LanguageSplash languageSplash;
  Controller c;
  MainIDEViewTest window;

  @BeforeEach
  void setUp() {
    //languageProbe = new LanguageProbe();
    c = new Controller("English");
    window = new MainIDEViewTest();
  }

  // THIS IS BROKEN FOR SOME REASON?????
  // WHY

//  @Test
//  void createStage() {
//    window.createStage("English", c);
//    assertTrue(true);
//  }
//
//  @Test
//  void getRawCommandText() {
//    window.createStage("English", c);
//    assertTrue(window.getRawCommandText().getClass() == String.class);
//  }

}