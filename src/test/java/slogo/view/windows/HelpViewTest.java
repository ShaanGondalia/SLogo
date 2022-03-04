package slogo.view.windows;

import static org.junit.jupiter.api.Assertions.*;

import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class HelpViewTest extends DukeApplicationTest {

  private Text content;
  public void start(Stage stage){
    Display hv = new HelpView();
  }

  @Test
  void testInitialText(){
    content = lookup("#content").query();
    assertEquals(content.getText() , "click a command to see help");
  }


}