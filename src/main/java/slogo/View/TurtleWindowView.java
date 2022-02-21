package slogo.View;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TurtleWindowView implements Displayable {
  private static final String TITLE = "SLOGO TEAM 3 - Turtle Window";
  private static final int HEIGHT = 400;
  private static final int WIDTH = 400;
  public static final String ROOT_ID = "turtleWindowRoot";

  private Pane myPane;
  @Override
  public void createStage(String language) {
    Stage stage = new Stage();
    myPane = new Pane();
    myPane.setId(ROOT_ID);
    Scene scene = new Scene(myPane, WIDTH, HEIGHT);
    stage.setTitle(TITLE);
    stage.setScene(scene);
    stage.show();
  }
}
