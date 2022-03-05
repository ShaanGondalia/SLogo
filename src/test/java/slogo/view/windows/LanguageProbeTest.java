package slogo.view.windows;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testing the Language Probe by clicking on implemented and non implemented languages
 *
 * @author Andy S. He
 */
class LanguageProbeTest extends DukeApplicationTest {

  private Splash ls;
  private Button eng;

  @Override
  public void start(Stage stage) {
    ls = new LanguageSplash();
    stage = new Stage();
    stage.setScene(ls.getMyScene());
    stage.show();
    eng = lookup("#English").query();
  }


  @Test
  void testImplemented() {
    clickOn(eng);
    assertTrue(ls.toString().equals("English"));
  }

}