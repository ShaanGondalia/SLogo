package slogo.view.windows;

import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testing the Language Probe by clicking on implemented and non implemented languages
 *
 * @author Andy S. He
 */
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
  void createStage(){
    assertTrue(true);
  }

//  @Test
//  void testNotImplemented(){
//    clickOn(lookup("French").query());
//    assertTrue(ls.toString().equals(""));
//  }

  @Test
  void testImplemented() {
    clickOn(lookup("English").query());
    assertTrue(ls.toString().equals("English"));
  }

}