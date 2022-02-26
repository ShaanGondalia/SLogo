package slogo.view.windows;

import java.awt.Dimension;
import java.lang.reflect.Method;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import slogo.Errors;
import slogo.controller.Controller;
import slogo.view.windows.sections.ButtonSection;
import slogo.view.windows.sections.HistorySection;
import slogo.view.windows.sections.IDESection;
import slogo.view.windows.sections.TextSection;

/**
 * Creates the central window to display where the user can type in text commands
 *
 * @author Andy S. He
 */
public class MainIDEView extends Display {

  private static final String TITLE = "SLOGO TEAM 3";
  private static final Dimension MAIN_SIZE = new Dimension(300, 200);
  private static final String ROOT_ID = "root";


  private BorderPane myPane;

  public MainIDEView(String language, Controller c, String css, Stage stage) {
    myPane = new BorderPane();
    myPane.setId(ROOT_ID);
    stage = createStage(TITLE, MAIN_SIZE, myPane, css);
    stage.show();

    IDESection textSection = new TextSection();
    IDESection historySection = new HistorySection();
    IDESection buttonSection = new ButtonSection(language, c, (HistorySection) historySection, (TextSection) textSection);

    myPane.setBottom(buttonSection.getSection());
    myPane.setRight(historySection.getSection());
    myPane.setCenter(textSection.getSection());
  }

}
