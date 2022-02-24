package slogo.view.windows;

import java.awt.Dimension;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import slogo.view.turtle.TurtleView;

public class TurtleWindowView extends Display {
  private static final String TITLE = "SLOGO TEAM 3 - Turtle Window";
  public static final int HEIGHT = 400;
  public static final int WIDTH = 400;
  private static final Dimension SIZE = new Dimension(400,400);
  public static final String ROOT_ID = "turtleWindowRoot";

  private Pane myPane;

  public TurtleWindowView(String css){

    myPane = new Pane();
    myPane.setId(ROOT_ID);

    Stage stage = createStage(TITLE, SIZE, myPane, css);
    stage.show();
  }


  public void addTurtleView(TurtleView tv) {
      myPane.getChildren().add(tv.getTurtleNode());
  }
}
