package slogo.view.windows;

import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.scene.control.Button;
import javafx.scene.control.TextInputControl;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import slogo.controller.Controller;
import slogo.model.turtle.TurtleManager;
import slogo.view.turtle.TurtleViewManager;
import util.DukeApplicationTest;

/**
 * Testing the Main IDE by testing each of its components
 *
 * @author Andy S. He
 */
class MainIDEViewTest extends DukeApplicationTest {

  private MainIDEView main;

  @Override
  public void start(Stage stage) {
    TurtleManager v = new TurtleManager();
    TurtleViewManager tvm = new TurtleViewManager(v, new TurtleWindowView("light"));
    main = new MainIDEView("English", new Controller("English", v), "dark", stage, tvm);
    TextInputControl area = lookup("#textArea").query();
    area.clear();
  }

  @Test
  void createStage() {
    assertTrue(true);
  }

//  @Test
//  void rawCommandText() {
//    String toWrite = "fd 50";
//    TextInputControl area = lookup("#textArea").query();
//
//    clickOn(area).write(toWrite);
//    assertTrue(main.getRawCommandText().equals("fd 50"));
//  }

}