package slogo.view.windows;

import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import slogo.view.windows.LanguageSplash;
import slogo.view.windows.Splash;
import util.DukeApplicationTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LanguageProbeTest extends DukeApplicationTest {

  private Splash ls;

  @Override
  public void start(Stage stage) {
    ls = new LanguageSplash();
    stage = new Stage();
    stage.setScene(ls.getMyScene());
    stage.show();
  }

  @Test
   void languageToUse() {
    clickOn(lookup("English").query());
    assertTrue(ls.toString().equals("English"));
  }
}