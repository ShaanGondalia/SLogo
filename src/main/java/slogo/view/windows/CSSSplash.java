package slogo.view.windows;

import java.awt.Dimension;
import java.util.List;
import java.util.ResourceBundle;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import slogo.Errors;

/**
 * Screen to display the first time program is running up. Here, can select language / css and
 * anything else needed before initialization
 *
 * @author Andy S. He
 */
public class CSSSplash extends Splash {
  private static final String TITLE = "Choose CSS";
  private static final Dimension SIZE = new Dimension(200,200);
  private static final String CSS_RESOURCE_ENDING = "CSS";
  private static final List<String> CSS_OPTIONS = List.of("light", "dark");
  
  private ResourceBundle myResources;
  private Stage myStage;
  private String style;

  public CSSSplash(String language){
    myResources = ResourceBundle.getBundle(MainIDEView.RESOURCE_PREFIX + language + CSS_RESOURCE_ENDING);
    myStage = createStage(TITLE, SIZE, makeOptions());
    try {
      myStage.showAndWait();
    }
    catch (Exception e){
      Errors.showAndClose(e.getMessage());
    }
  }
  
  private TilePane makeOptions(){
    TilePane root = new TilePane();
    for(String key : CSS_OPTIONS){
      makeOption(root, key);
    }
    root.setAlignment(Pos.CENTER);
    return root;
  }
  
  private void makeOption(TilePane root, String key){
    Button b = new Button();
    b.setText(myResources.getString(key));
    b.setOnAction( e -> {
      style = key;
      myStage.close();
    });
    b.setId(key);
    root.getChildren().add(b);
  }

  @Override
  public String toString() {
    return style;
  }

}
