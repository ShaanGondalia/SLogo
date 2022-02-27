package slogo.view.windows.buttons;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import slogo.model.turtle.Turtle;
import slogo.view.turtle.TurtleView;
import slogo.view.util.ButtonUtil;
import slogo.view.util.ColorPickerGenerator;
import slogo.view.windows.Display;
import slogo.view.windows.TurtleWindowView;

import javax.sound.midi.Soundbank;
import java.awt.*;

/**
 * Button that sets the background color of the turtle canvas window
 *
 * @author Zack Schrage
 */
public class SetBkColor extends Display implements IDEButton{

  @Override
  public void doAction(ButtonUtil info) {
    ColorPickerGenerator cpg = new ColorPickerGenerator();
    Stage s = cpg.cpStage();

    EventHandler<ActionEvent> colorChangeEvent = e -> {
        GraphicsContext gc = TurtleWindowView.CANVAS.getGraphicsContext2D();
        gc.setFill(cpg.getCp().getValue());
        gc.fillRect(0, 0, TurtleWindowView.CANVAS.getWidth(), TurtleWindowView.CANVAS.getHeight());
        for (TurtleView tv : info.c().getTurtleViews()) {
            gc.setFill(tv.getTrailColor());
            for (Line line : tv.getTrailHistory().getTrails()) {
                gc.strokeLine(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());
            }
        }
    };

    cpg.getCp().setOnAction(colorChangeEvent);
    s.show();

  }

}
