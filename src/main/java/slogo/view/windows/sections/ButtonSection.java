package slogo.view.windows.sections;

import java.util.ResourceBundle;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.TilePane;
import slogo.controller.Controller;
import slogo.view.turtle.TurtleViewManager;
import slogo.view.util.ButtonUtil;
import slogo.view.util.Runner;
import slogo.view.windows.buttons.IDEButtonFactory;

/**
 * The section that holds all the buttons that the user can use to interact with the IDE
 *
 * @author Andy S. He
 */
public class ButtonSection implements IDESection {

  private static final String BUTTON_SECTION_ID = "button_sec";
  public static final String RESOURCE_PREFIX = "view.buttons.";

  private TilePane myTilePane;
  private ResourceBundle myResources;

  /**
   * Default constructor used to generate the buttons and the button section
   *
   * @param language language to display the buttons
   * @param c        controller to interact with the model
   * @param histSec  History Section to add commands to the history
   * @param textSec  Text Section to get the text
   * @see IDEButtonFactory
   */
  public ButtonSection(String language, Controller c, HistorySection histSec, TextSection textSec, VariablesAndCommandsSection userDefinedSection, Runner runner, TurtleViewManager tvm) {
    myTilePane = new TilePane();
    myTilePane.setId(BUTTON_SECTION_ID);
    myResources = ResourceBundle.getBundle(RESOURCE_PREFIX + language);
    IDEButtonFactory factory = new IDEButtonFactory();
    for (String button : myResources.keySet()) {
      myTilePane.getChildren()
          .add(factory.createButton(new ButtonUtil(button, c, language, histSec, textSec, userDefinedSection, runner, tvm)));
    }
  }

  @Override
  public Region getSection() {
    return myTilePane;
  }

}
