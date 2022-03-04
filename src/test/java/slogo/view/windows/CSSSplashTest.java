package slogo.view.windows;

import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testing the CSS Splash by seeing if the style sheet is indeed used
 *
 * @author Andy S. He
 */
class CSSSplashTest extends DukeApplicationTest {

  private CSSSplash window;

  @Override
  public void start(Stage stage) {
    window = new CSSSplash("English");
    stage.setScene(window.getMyScene());
    stage.show();
  }

  @Test
  void styleSheetToUse() {
    clickOn(lookup("light").query());
    assertTrue(window.toString().equals("light"));
  }
}