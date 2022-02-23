package slogo.view;

import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LanguageProbeTest extends DukeApplicationTest {

  @Test
  void languageToUse() {
    LanguageProbe languageProbe = new LanguageProbe();
    assertTrue(languageProbe.toString().equals("English"));
  }
}