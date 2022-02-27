package slogo.view.windows;

import java.awt.Dimension;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import slogo.controller.Controller;
import slogo.view.windows.sections.ButtonSection;
import slogo.view.windows.sections.HistorySection;
import slogo.view.windows.sections.IDESection;
import slogo.view.windows.sections.TextSection;
import slogo.view.windows.sections.VariablesAndCommandsSection;

/**
 * Creates the central window to display where the user can type in text commands
 *
 * @author Andy S. He
 */
public class MainIDEView extends Display {

  private static final String TITLE = "SLOGO TEAM 3";
  private static final Dimension MAIN_SIZE = new Dimension(700, 400);
  private static final String ROOT_ID = "root";


  private BorderPane myPane;

  public MainIDEView(String language, Controller c, String css, Stage stage) {
    myPane = new BorderPane();
    myPane.setId(ROOT_ID);

    IDESection variablesSection = new VariablesAndCommandsSection(c);
    IDESection textSection = new TextSection();
    IDESection historySection = new HistorySection();
    IDESection buttonSection = new ButtonSection(language, c, (HistorySection) historySection, (TextSection) textSection);

    myPane.setBottom(buttonSection.getSection());
    myPane.setRight(historySection.getSection());
    myPane.setCenter(textSection.getSection());
    myPane.setLeft(variablesSection.getSection());

    stage = createStage(TITLE, MAIN_SIZE, myPane, css);
    stage.show();
  }

}
