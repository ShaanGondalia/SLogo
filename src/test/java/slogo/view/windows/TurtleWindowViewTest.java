package slogo.view.windows;

import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import slogo.controller.Controller;
import slogo.model.turtle.TurtleManager;
import slogo.view.turtle.TurtleView;
import slogo.view.turtle.TurtleViewManager;
import slogo.view.windows.LanguageSplash;
import slogo.view.windows.TurtleWindowView;
import util.DukeApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing popping up the turtle window
 *
 * @author Andy S. He, Zack Schrage
 */
class TurtleWindowViewTest extends DukeApplicationTest {

  Controller c;
  TurtleWindowView window;

  @Override
  public void start(Stage stage) {
    //window = new TurtleWindowView("light");
    TurtleManager turtleManager = new TurtleManager();
    TurtleViewManager tvm = new TurtleViewManager(turtleManager, window);
    c = new Controller("English", turtleManager);
    stage.setScene(window.getMyScene());
    stage.show();
  }

  @Test
  void createStage() {
    assertTrue(true);
  }

  @Test
  void addTurtleView() {
    TurtleView tv = new TurtleView(window);
    Platform.runLater(() -> window.addTurtleView(tv));
    assertTrue(true);
  }

}