package slogo.view.windows;

import static org.junit.jupiter.api.Assertions.*;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

/**
 * Testing the turtleNumberSplash
 *
 * @author Andy S. He
 */
class TurtleNumSplashTest extends DukeApplicationTest {
  private Splash ls;
  private Button two;

  @Override
  public void start(Stage stage) {
    ls = new TurtleNumSplash("English");
    stage = new Stage();
    stage.setScene(ls.getMyScene());
    stage.show();
    two = lookup("#2").query();
  }

  @Test
  void turtleNumToUse(){
    clickOn(two);
    assertEquals(ls.toString(), String.valueOf(2));
  }
}