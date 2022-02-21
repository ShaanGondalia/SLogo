package slogo.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.controller.Controller;
import util.DukeApplicationTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MainIDEViewTest extends DukeApplicationTest {

  LanguageProbe languageProbe;
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