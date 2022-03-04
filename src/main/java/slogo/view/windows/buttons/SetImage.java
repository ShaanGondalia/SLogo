package slogo.view.windows.buttons;

import slogo.view.turtle.TurtleView;
import slogo.view.util.ButtonUtil;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * Button that opens a file chooser to select an image to act as the turtle image view
 *
 * @author Zack Schrage
 */
public class SetImage implements IDEButton {

  public static final String imagePath = "src/main/resources/view/img/";

  @Override
  public void doAction(ButtonUtil info) {
      FileChooser fc = new FileChooser();
      fc.setInitialDirectory(new File(imagePath));
      File file = fc.showOpenDialog(new Stage());
      if (file != null) {
          for (TurtleView tv : info.tvm().getMyTurtleViewList()) {
              tv.setTurtleImage(file.getName());
          }
      }

  }
}
