package slogo.view.windows.sections;

import java.util.ResourceBundle;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.TilePane;
import slogo.controller.Controller;
import slogo.view.util.ButtonUtil;
import slogo.view.windows.buttons.IDEButtonFactory;

public class ButtonSection implements IDESection {

  private static final String BUTTON_SECTION_ID = "button_sec";
  private static final String RESOURCE_PREFIX = "view.buttons.";

  private TilePane myTilePane;
  private ResourceBundle myResources;

  public ButtonSection(String language, Controller c, HistorySection histSec, TextSection textSec){
    myTilePane = new TilePane();
    myTilePane.setId(BUTTON_SECTION_ID);
    myResources = ResourceBundle.getBundle(RESOURCE_PREFIX + language);
    IDEButtonFactory factory = new IDEButtonFactory();
    for (String button : myResources.keySet()){
      myTilePane.getChildren().add(factory.createButton(new ButtonUtil(button, c, myResources, histSec, textSec)));
    }
  }

  @Override
  public Region getSection(){
    return myTilePane;
  }

}
