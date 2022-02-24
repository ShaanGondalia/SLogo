package slogo.view;

import java.awt.Dimension;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import slogo.controller.Controller;

/**
 * Commons to display a new stage
 *
 * @author Andy S. He
 */
public abstract class Display {
  protected Stage createStage(String title, Dimension size, Pane root){
    Stage stage = new Stage();
    Scene scene = new Scene(root, size.width, size.height);
    stage.setScene(scene);
    stage.setTitle(title);
    return stage;
  }
}
