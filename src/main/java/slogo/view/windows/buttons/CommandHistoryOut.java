package slogo.view.windows.buttons;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import slogo.view.util.ButtonUtil;

public class CommandHistoryOut implements IDEButton {

  @Override
  public void doAction(ButtonUtil info) {
//    Stage stage = new Stage();
//    Text text = new Text(info.histSec().getHistoryText());
//    Menu file = new Menu("File");
//    MenuItem item = new MenuItem("Save", text);
//    file.getItems().addAll(item);
//    FileChooser fileChooser = new FileChooser();
//    fileChooser.setTitle("Save");
//    item.setOnAction((e) -> fileChooser.showSaveDialog(stage));
//    MenuBar menuBar = new MenuBar(file);
//    Group root = new Group(menuBar);
//    Scene scene = new Scene(root, 400, 400);
//    stage.setScene(scene);
//    stage.show();
    test(info);
  }

  private void test(ButtonUtil info) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Save");
    File file = fileChooser.showSaveDialog(new Stage());
    if (file != null){
      try{
        FileWriter myWriter = new FileWriter(file);
        myWriter.write(info.histSec().getHistoryText());
        myWriter.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

}
