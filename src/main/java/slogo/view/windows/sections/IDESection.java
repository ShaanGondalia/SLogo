package slogo.view.windows.sections;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;

/**
 * The common interface that will be used to make a new Section in the IDE
 *
 * @author Andy S. He
 */
public interface IDESection {

  /**
   * get the region that can be set to a specific location on the MainIDEView
   * @return a TilePane, for example
   */
  Region getSection();
}
