package slogo.view;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import slogo.controller.Controller;

public class TurtleWindowView extends Displayable {
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
  }

  public void addTurtleView(TurtleView tv) {
      myPane.getChildren().add(tv.getTurtleNode());
  }
}
