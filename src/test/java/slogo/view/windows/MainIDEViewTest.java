package slogo.view.windows;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.sun.javafx.scene.control.LabeledText;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputControl;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import slogo.controller.Controller;
import slogo.model.turtle.TurtleManager;
import slogo.view.turtle.TurtleViewManager;
import slogo.view.windows.sections.ButtonSection;
import slogo.view.windows.sections.DataSection;
import slogo.view.windows.sections.HistorySection;
import slogo.view.windows.sections.TextSection;
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
  void rawCommandText() {
    String toWrite = "fd 50";
    TextInputControl area = lookup("#textArea").query();
    clickOn(area).write(toWrite);
    clickOn(lookup("run").query());
    Button b = lookup("fd 50").query();
    assertEquals(b.getText(), "fd 50");
  }

  @Test
  void setVariable(){
    String toWrite = "set :rr 40";
    TextInputControl area = lookup("#textArea").query();
    clickOn(area).write(toWrite);
    clickOn(lookup("run").query());
    Button b = lookup("rr: 40.00\n").query();
    assertEquals(b.getText(), "rr: 40.00\n");
  }

}