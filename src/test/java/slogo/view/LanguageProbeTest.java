package slogo.view;

import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LanguageProbeTest extends DukeApplicationTest {

  @Test
  void languageToUse() {
    LanguageSplash languageSplash = new LanguageSplash();
    clickOn(lookup("English").query());
    assertTrue(languageSplash.toString().equals("English"));
  }
}