package slogo.view.util;

import java.io.File;
import java.io.IOException;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileWriter {
  private static final String TITLE = "Save";
  public FileWriter(String toWrite){
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle(TITLE);
    File file = fileChooser.showSaveDialog(new Stage());
    if (file != null){
      try{
        java.io.FileWriter myWriter = new java.io.FileWriter(file);
        myWriter.write(toWrite);
        myWriter.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
