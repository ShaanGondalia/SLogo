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
public class SetPenColor implements IDEButton {

    private static final double PEN_BUTTON_PALETTE_INDEX = -1;

    @Override
    public void doAction(ButtonUtil info) {
      ColorPickerGenerator cpg = new ColorPickerGenerator();
      Stage s = cpg.cpStage();

      EventHandler<ActionEvent> colorChangeEvent = e -> {
          Color c = cpg.getCp().getValue();
          int r = (int)(c.getRed()*255);
          int g = (int)(c.getGreen()*255);
          int b = (int)(c.getBlue()*255);
          String program = String.format("setpalette %f %d %d %d setpc %f", PEN_BUTTON_PALETTE_INDEX, r, g ,b, PEN_BUTTON_PALETTE_INDEX);
          info.runner().runQuietly(program);
      };
      cpg.getCp().setOnAction(colorChangeEvent);

      s.show();
    }
}
