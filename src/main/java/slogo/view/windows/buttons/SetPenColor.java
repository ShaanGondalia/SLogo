package slogo.view.windows.buttons;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import slogo.view.turtle.TurtleView;
import slogo.view.util.ButtonUtil;
import slogo.view.util.ColorPickerGenerator;

/**
 * Button that opens a window with a color picker that sets the pen color of the turtle's path
 *
 * @author Zack Schrage
 */
public class SetPenColor implements IDEButton{

  @Override
  public void doAction(ButtonUtil info) {
      ColorPickerGenerator cpg = new ColorPickerGenerator();
      Stage s = cpg.cpStage();

      EventHandler<ActionEvent> colorChangeEvent = e -> {
          Color c = cpg.getCp().getValue();
          for (TurtleView tv : info.tvm().getMyTurtleViewList()) {
              tv.setTrailColor(c);
          }
      };
      cpg.getCp().setOnAction(colorChangeEvent);

      s.show();
  }
}
