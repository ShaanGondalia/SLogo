package slogo.view.windows.buttons;

import javafx.scene.control.TextInputControl;
import javafx.scene.layout.TilePane;

public class ButtonSection {

  private static final String BUTTON_SECTION_ID = "button_sec";
  private TilePane myTilePane;

  public ButtonSection(){
    myTilePane = new TilePane();
    myTilePane.setId(BUTTON_SECTION_ID);
  }

  public TilePane getSection(){
    return myTilePane;
  }

}
