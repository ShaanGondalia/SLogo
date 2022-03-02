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
  private static final String TITLE = "Save";
  @Override
  public void doAction(ButtonUtil info) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle(TITLE);
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
