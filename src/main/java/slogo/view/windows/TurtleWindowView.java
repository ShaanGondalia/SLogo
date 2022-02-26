package slogo.view.windows;

import java.awt.Dimension;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import slogo.view.turtle.TurtleView;

public class TurtleWindowView extends Display {
  private static final String TITLE = "SLOGO TEAM 3 - Turtle Window";
  public static final int HEIGHT = 400;
  public static final int WIDTH = 400;
  private static final Dimension SIZE = new Dimension(400,400);
  public static final String ROOT_ID = "turtleWindowRoot";
  private static Canvas canvas;

  private Pane myPane;

  public TurtleWindowView(String css){
    canvas = new Canvas();
    canvas.setWidth(WIDTH);
    canvas.setHeight(HEIGHT);
    myPane = new Pane();
    myPane.getChildren().add(canvas);
    myPane.setId(ROOT_ID);

    Stage stage = createStage(TITLE, SIZE, myPane, css);
    stage.show();
  }


  public void addTurtleView(TurtleView tv) {
      myPane.getChildren().add(tv.getTurtleNode());
  }

  public static Canvas getCanvas() {
    return canvas;
  }
}
