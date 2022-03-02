package slogo.view.windows.buttons;

import javafx.scene.shape.Line;
import slogo.view.turtle.Trail;
import slogo.view.turtle.TurtleView;
import slogo.view.util.ButtonUtil;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 *
 */
public class SetImage implements IDEButton {

  public static final String imagePath = "src/main/resources/view/img/";

  @Override
  public void doAction(ButtonUtil info) {
      FileChooser fc = new FileChooser();
      fc.setInitialDirectory(new File(imagePath));
      File file = fc.showOpenDialog(new Stage());
      if (file != null) {
          for (TurtleView tv : info.c().getTurtleViews()) {
              tv.setTurtleImage(file.getName());
          }
      }

  }
}
