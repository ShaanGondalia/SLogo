package slogo.view.windows;

import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import slogo.Errors;

/**
 * Help View - it only supports english because Prof. Duvall only gave us english help menus
 *
 * @author Andy S. He
 */
public class HelpView extends Display {

  private static final String TITLE = "Help View";
  private static final Dimension DIM = new Dimension(400, 400);
  private static final String FILE_PATH = "src/main/resources/view/reference";
  private static final String DEFAULT_CONTENT = "click a command to see help";

  private String myContent;
  private Text myContentTextBox;
  private VBox myButtons;
  private File[] myFiles;

  /**
   * Constructor that will display everything - is called on press of the help button
   * @see slogo.view.windows.buttons.Help
   */
  public HelpView() {

    File path = new File(FILE_PATH);
    myFiles = path.listFiles();

    myContent = DEFAULT_CONTENT;
    BorderPane borderPane = new BorderPane();

    ScrollPane options = new ScrollPane();
    myButtons = new VBox();
    options.setContent(myButtons);

    ScrollPane description = new ScrollPane();
    myContentTextBox = new Text(myContent);

    description.setContent(myContentTextBox);
    Stage stage = createStage(TITLE, DIM, borderPane, "light");

    borderPane.setLeft(options);
    borderPane.setCenter(description);
    makeHelperButtons();
    stage.show();
  }

  private void makeHelperButtons() {
    for (File file : myFiles) {
      Button b = new Button();
      b.setText(file.getName());
      b.setOnAction((e) -> {
        try {
          setDescContent(file.getName());
        } catch (FileNotFoundException ex) {
          Errors.showError("internal error");
        }
      });
      myButtons.getChildren().add(b);
    }
  }

  private void setDescContent(String name) throws FileNotFoundException {
    myContent = "";

    for (int i = 0; i < myFiles.length; i++) {
      if (myFiles[i].isFile() && myFiles[i].getName()
          .equals(name)) { //this line weeds out other directories/folders
        Scanner scanner = new Scanner(myFiles[i]);
        while (scanner.hasNextLine()) {
          myContent += scanner.nextLine() + "\n";
        }
      }
    }
    myContentTextBox.setText(myContent);
  }
}