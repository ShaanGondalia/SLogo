package slogo.view.windows.buttons;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import slogo.controller.Controller;
import slogo.model.turtle.Turtle;
import slogo.view.turtle.TurtleView;
import slogo.view.util.ButtonUtil;
import slogo.view.util.ColorPickerGenerator;

/**
 * Button that sets the pen color of the turtle's path
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
          for (TurtleView tv : info.c().getTurtleViews()) {
              tv.setTrailColor(c);
          }
      };
      cpg.getCp().setOnAction(colorChangeEvent);

      s.show();
  }
}
