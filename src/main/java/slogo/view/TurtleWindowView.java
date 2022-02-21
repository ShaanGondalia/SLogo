package slogo.view;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import slogo.controller.Controller;
import slogo.model.turtle.Pose;
import slogo.model.turtle.Turtle;

public class TurtleWindowView implements Displayable {
  private static final String TITLE = "SLOGO TEAM 3 - Turtle Window";
  public static final int HEIGHT = 400;
  public static final int WIDTH = 400;
  public static final String ROOT_ID = "turtleWindowRoot";

  private Pane myPane;

  @Override
  public void createStage(String language, Controller c) {
    Stage stage = new Stage();
    myPane = new Pane();
    myPane.setId(ROOT_ID);
    Scene scene = new Scene(myPane, WIDTH, HEIGHT);
    stage.setTitle(TITLE);
    stage.setScene(scene);
    stage.show();

    TurtleView tv = new TurtleView();
    myPane.getChildren().add(tv.getTurtleNode());
    c.addTurtle(tv);
//    t.setPose(new Pose(200, 200, 1));
  }
}
