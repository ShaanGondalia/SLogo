package slogo.view.windows.buttons;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import slogo.view.turtle.Trail;
import slogo.view.turtle.TurtleView;
import slogo.view.util.ButtonUtil;
import slogo.view.util.ColorPickerGenerator;
import slogo.view.windows.Display;
import slogo.view.windows.TurtleWindowView;

/**
 * Button that opens a window with a color picker that sets the background color of the turtle canvas window
 *
 * @author Zack Schrage
 */
public class SetBkColor extends Display implements IDEButton{

  @Override
  public void doAction(ButtonUtil info) {
    ColorPickerGenerator cpg = new ColorPickerGenerator();
    Stage s = cpg.cpStage();

    EventHandler<ActionEvent> colorChangeEvent = e -> {
        GraphicsContext gc = info.tvm().getTurtleWindowView().canvas.getGraphicsContext2D();
        gc.setFill(cpg.getCp().getValue());
        gc.setStroke(cpg.getCp().getValue());
        gc.fillRect(0, 0, info.tvm().getTurtleWindowView().canvas.getWidth(), info.tvm().getTurtleWindowView().canvas.getHeight());
        for (TurtleView tv : info.tvm().getMyTurtleViewList()) {
            for (Trail trail : tv.getTrailHistory()) {
                Line line = trail.getLine();
                gc.setFill(trail.getColor());
                gc.setStroke(trail.getColor());
                gc.strokeLine(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());
            }
        }
    };

    cpg.getCp().setOnAction(colorChangeEvent);
    s.show();

  }

}
