package slogo.view.windows.sections;

import static slogo.view.windows.sections.DataSection.NEW_LINE;

import java.util.Map;
import java.util.ResourceBundle;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import slogo.controller.Controller;
import slogo.view.windows.MainIDEView;

public class TurtlesAndColorsSection implements IDESection{
  private static final int TC_WIDTH = 150;
  private static final String PALETTE_STARTING_TEXT = "ColorPalette";
  private static final String TURTLE_STARTING_TEXT = "Turtles";


  private Text tAndCText;
  private ScrollPane tAndCPane;
  private ResourceBundle resourceBundle;
  private Controller myController;

  public TurtlesAndColorsSection(Controller c, String language){
    resourceBundle = ResourceBundle.getBundle(MainIDEView.IDE_RESOURCES_ROOT + language);
    tAndCPane = new ScrollPane();
    tAndCText = new Text(TURTLE_STARTING_TEXT);
    tAndCPane.setContent(tAndCText);
    tAndCPane.setPrefViewportWidth(TC_WIDTH);
    myController = c;
    update();
  }

  public void update() {
    String toDisplay = "";

    toDisplay += turtleDisplay() + "\n\n";
    toDisplay += colorDisplay();

    tAndCText.setText(toDisplay);
  }

  private String turtleDisplay() {
    String toDisplay = resourceBundle.getString(TURTLE_STARTING_TEXT) + NEW_LINE;
    Map<String, String> turtleIDS = myController.getMapData(Controller.TURTLE_GETTER);
    for (String key: turtleIDS.keySet()) {
      String color = turtleIDS.get(key);
      toDisplay += String.format("%s: %s\n", key, color);
    }
    return toDisplay;
  }

  private String colorDisplay() {
    String toDisplay = resourceBundle.getString(PALETTE_STARTING_TEXT) + NEW_LINE;
    Map<String, String> colorPalette = myController.getMapData(Controller.COLOR_PALETTE_GETTER);
    for (String key: colorPalette.keySet()) {
      String color = colorPalette.get(key);
      toDisplay += String.format("%s: %s\n", key, color);
    }
    return toDisplay;
  }

  @Override
  public Region getSection() {
    return tAndCPane;
  }
}
