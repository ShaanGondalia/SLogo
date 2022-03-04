package slogo.view.windows;

import java.awt.Dimension;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import slogo.controller.Controller;
import slogo.view.turtle.TurtleViewManager;
import slogo.view.util.Runner;
import slogo.view.windows.sections.ButtonSection;
import slogo.view.windows.sections.HistorySection;
import slogo.view.windows.sections.IDESection;
import slogo.view.windows.sections.TextSection;
import slogo.view.windows.sections.DataSection;

/**
 * Creates the central window to display where the user can type in text commands
 *
 * @author Andy S. He
 */
public class MainIDEView extends Display {

  private static final String TITLE = "SLOGO TEAM 3";
  private static final Dimension MAIN_SIZE = new Dimension(900, 400);
  private static final String ROOT_ID = "root";
  public static final String IDE_RESOURCES_ROOT = "view.defaultIdeText.";

  private BorderPane myPane;

  public MainIDEView(String language, Controller controller, String css, Stage stage, TurtleViewManager turtleViewManager) {
    myPane = new BorderPane();
    myPane.setId(ROOT_ID);

    Runner runner = new Runner();

    IDESection variablesSection = new DataSection(controller, language, runner);
    IDESection textSection = new TextSection();
    IDESection historySection = new HistorySection(runner, language);

    runner.setParameters(controller, (DataSection) variablesSection,
        (HistorySection) historySection);
    IDESection buttonSection = new ButtonSection(language, controller, (HistorySection) historySection,
        (TextSection) textSection, (DataSection) variablesSection, runner, turtleViewManager);

    myPane.setBottom(buttonSection.getSection());
    myPane.setRight(historySection.getSection());
    myPane.setCenter(textSection.getSection());
    myPane.setLeft(variablesSection.getSection());

    stage = createStage(TITLE, MAIN_SIZE, myPane, css);
    stage.show();
  }

}
