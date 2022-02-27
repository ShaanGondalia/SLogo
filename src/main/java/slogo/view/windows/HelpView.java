package slogo.view.windows;

import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HelpView extends Display{

  private static final String TITLE = "Help View";
  private static final Dimension DIM = new Dimension(400,400);
  private static final String FILE_PATH = "src/main/resources/view/reference";

  private String myContent;
  private Text myContentTextBox;
  public HelpView(){
    myContent = "";
    BorderPane borderPane = new BorderPane();

    ScrollPane options = new ScrollPane();
    VBox buttons = new VBox();
    options.setContent(buttons);

    ScrollPane description = new ScrollPane();
    myContentTextBox = new Text(myContent);

    description.setContent(myContentTextBox);
    Stage stage = createStage(TITLE, DIM, borderPane, "light");

    borderPane.setLeft(options);
    borderPane.setCenter(description);

    stage.show();
  }

  private void setDescContent(String name) throws FileNotFoundException {
    myContent = "";
    File path = new File(FILE_PATH);
    File[] files = path.listFiles();
    for (int i = 0; i < files.length; i++){
      if (files[i].isFile() && files[i].getName().equals(name)){ //this line weeds out other directories/folders
        Scanner scanner = new Scanner(files[i]);
        while (scanner.hasNextLine()){
          myContent += scanner.nextLine() + "\n";
        }
      }
    }
    myContentTextBox.setText(myContent);
  }

}
