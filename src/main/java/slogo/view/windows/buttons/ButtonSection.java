package slogo.view.windows.buttons;

import java.util.ResourceBundle;
import javafx.scene.layout.TilePane;
import slogo.controller.Controller;
import slogo.view.util.ButtonUtil;

public class ButtonSection {

  private static final String BUTTON_SECTION_ID = "button_sec";
  private static final String RESOURCE_PREFIX = "view.buttons.";

  private TilePane myTilePane;
  private ResourceBundle myResources;

  public ButtonSection(String language, Controller c){
    myTilePane = new TilePane();
    myTilePane.setId(BUTTON_SECTION_ID);
    myResources = ResourceBundle.getBundle(RESOURCE_PREFIX + language);
    IDEButtonFactory factory = new IDEButtonFactory();
    for (String button : myResources.keySet()){
      myTilePane.getChildren().add(factory.createButton(new ButtonUtil(button, c, myResources)));
    }
  }

  public TilePane getSection(){
    return myTilePane;
  }

}
