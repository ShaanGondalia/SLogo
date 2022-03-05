package slogo.view.windows.sections;

import java.util.ResourceBundle;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import slogo.controller.Controller;
import slogo.view.windows.MainIDEView;

public class TurtlesAndColorsSection implements IDESection{
  private static final int TC_WIDTH = 150;
  private static final String TC_DEFAULT_TEXT = "TurtleColors";

  private Text tAndCText;
  private ScrollPane tAndCPane;
  private ResourceBundle resourceBundle;

  public TurtlesAndColorsSection(Controller c, String language){
    resourceBundle = ResourceBundle.getBundle(MainIDEView.IDE_RESOURCES_ROOT + language);
    tAndCPane = new ScrollPane();
    tAndCText = new Text(resourceBundle.getString(TC_DEFAULT_TEXT));
    tAndCPane.setContent(tAndCText);
    tAndCPane.setPrefViewportWidth(TC_WIDTH);
  }

  @Override
  public Region getSection() {
    return tAndCPane;
  }
}
