package slogo.view.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import slogo.controller.Controller;
import slogo.view.turtle.TurtleViewManager;
import slogo.view.windows.TurtleWindowView;
import slogo.view.windows.sections.DataSection;
import slogo.view.windows.sections.HistorySection;
import slogo.view.windows.sections.TextSection;
import util.DukeApplicationTest;

class RunnerAndClickToExecuteGUITest extends DukeApplicationTest {
  private Runner runner;
  private HistorySection hs;
  @Override
  public void start(Stage stage){
    runner = new Runner();
    Controller c = new Controller("English", null);
    hs = new HistorySection(runner, "English");
    DataSection ds = new DataSection(c, "English", runner);
    TextSection ts = new TextSection();
    ButtonUtil info = new ButtonUtil(null, c, "English", hs, ts, ds, runner, null);
    runner.setParameters(c, ds, hs);
    ClickToExecuteGUI cGUI = new ClickToExecuteGUI(info, stage);
  }

  @Test
  void runAndSave() {
    assertEquals("", hs.getHistoryText());
    runner.runAndSave("fd 50");
    assertEquals("fd 50\n", hs.getHistoryText());
  }

  @Test
  void runQuietly() {
    runner.runQuietly("fd 50");
    assertEquals("", hs.getHistoryText());
  }

  @Test
  void forward(){
    clickOn(lookup("Forward ").query());
    assertEquals("", hs.getHistoryText());
  }

}