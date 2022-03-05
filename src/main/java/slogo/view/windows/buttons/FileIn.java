package slogo.view.windows.buttons;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import slogo.Errors;
import slogo.view.util.ButtonUtil;

/**
 * Button that reads a file of text and parses it - created reflectively
 *
 * @author Andy S. He
 * @see slogo.view.windows.buttons.IDEButton
 * @see IDEButtonFactory
 * @see slogo.view.windows.sections.ButtonSection
 */
public class FileIn implements IDEButton {

  @Override
  public void doAction(ButtonUtil info) {
    FileChooser fileChooser = new FileChooser();
    File newFile = fileChooser.showOpenDialog(new Stage());
    Scanner scanner = null;
    try {
      scanner = new Scanner(newFile);
    } catch (FileNotFoundException e) {
      Errors.showError("internal error");
    }
    String toRun = "";
    while (scanner.hasNextLine()) {
      toRun += scanner.nextLine();
      toRun += "\n";
    }

    info.runner().runAndSave(toRun);
  }
}
