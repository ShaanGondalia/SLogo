package slogo.view.util;

import java.io.File;
import java.io.IOException;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import slogo.Errors;

/**
 * A class that can write text to a file
 *
 * @author Andy S. He
 * @see slogo.view.windows.buttons.VariablesAndCommandsOut
 * @see slogo.view.windows.buttons.CommandHistoryOut
 */
public class FileWriter {

  private static final String TITLE = "Save";

  /**
   * Default constructor
   *
   * @param toWrite the text that will be written to the file
   */
  public FileWriter(String toWrite) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle(TITLE);
    File file = fileChooser.showSaveDialog(new Stage());
    if (file != null) {
      try {
        java.io.FileWriter myWriter = new java.io.FileWriter(file);
        myWriter.write(toWrite);
        myWriter.close();
      } catch (IOException e) {
        Errors.showError(e.getMessage());
      }
    }
  }
}
