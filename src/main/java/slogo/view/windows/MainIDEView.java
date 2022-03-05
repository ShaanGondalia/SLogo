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


  /**
   * Class that creates and stores all the elements in the Main IDE
   *
   * @param language          language to display text
   * @param controller        controller to run commands from the front-end
   * @param css               css that the main-ide should be styled to
   * @param stage             stage that can be used for testing
   * @param turtleViewManager contains all the information about turtles
   */
  public MainIDEView(String language, Controller controller, String css, Stage stage,
      TurtleViewManager turtleViewManager) {
    BorderPane myPane = new BorderPane();
    myPane.setId(ROOT_ID);

    Runner runner = new Runner();

    IDESection dataSection = new DataSection(controller, language, runner);
    IDESection textSection = new TextSection();
    IDESection historySection = new HistorySection(runner, language);

    runner.setParameters(controller, (DataSection) dataSection,
        (HistorySection) historySection);
    IDESection buttonSection = new ButtonSection(language, controller, (HistorySection) historySection,
        (TextSection) textSection, (DataSection) dataSection, runner, turtleViewManager);

    myPane.setBottom(buttonSection.getSection());
    myPane.setRight(historySection.getSection());
    myPane.setCenter(textSection.getSection());
    myPane.setLeft(dataSection.getSection());

    stage = createStage(TITLE, MAIN_SIZE, myPane, css);
    stage.show();
  }

}
