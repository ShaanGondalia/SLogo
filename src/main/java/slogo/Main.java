package slogo;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import javafx.application.Application;
import javafx.stage.Stage;
import slogo.controller.Controller;
import slogo.model.turtle.TurtleManager;
import slogo.view.turtle.TurtleViewManager;
import slogo.view.windows.Display;
import slogo.view.windows.LanguageSplash;
import slogo.view.windows.MainIDEView;
import slogo.view.windows.CSSSplash;
import slogo.view.windows.Splash;
import slogo.view.turtle.TurtleView;
import slogo.view.windows.TurtleNumSplash;
import slogo.view.windows.TurtleWindowView;


/**
 * Feel free to completely change this code or delete it entirely.
 */
public class Main extends Application {

  private static final String LANGUAGE_RESOURCE_PATH = "/slogo/languages/";
  private static final String EXAMPLE_PROGRAMS_PATH = "/examples";

  public static final double TOLERANCE = 0.0001;

  /**
   * Get command in a given language.
   */
  public String getCommand(String language, String command) {
    ResourceBundle resources = ResourceBundle.getBundle(LANGUAGE_RESOURCE_PATH + language);
    return resources.getString(command);
  }

  /**
   * Get first line of given example program.
   */
  public String getExampleProgram(String category, String example) {
    // regular expression representing one or more whitespace characters (space, tab, or newline)
    final String NEWLINE = "\\n+";

    List<String> lines = readFile(
        String.format("%s/%s/%s.slogo", EXAMPLE_PROGRAMS_PATH, category, example), NEWLINE);
    String program = "";
    for (String line: lines) {
      program += line + "\n";
    }
    return program;
  }

  /**
   * A method to test (and a joke :).
   */
  public double getVersion() {
    return 0.001;
  }

  // this code is dense, hard to read, and throws exceptions so better to wrap in method
  private List<String> readFile(String filename, String delimiter) {
    try {
      String path = getClass().getResource(filename).toExternalForm();
      String data = new String(Files.readAllBytes(Paths.get(new URI(path))));
      return Arrays.asList(data.split(delimiter));
    } catch (URISyntaxException | IOException | NullPointerException e) {
      // NOT ideal way to handle exception, but this is just a simple test program
      System.out.println("ERROR: Unable to read input file " + e.getMessage());
      return Collections.emptyList();
    }
  }

  @Override
  public void start(Stage stage) throws ClassNotFoundException {

    Splash languageProbe = new LanguageSplash();
    languageProbe.show();
    Splash splashView = new CSSSplash(languageProbe.toString());
    splashView.show();
    Splash turtleNumSplash = new TurtleNumSplash(languageProbe.toString());
    turtleNumSplash.show();
    int startingTurtles = Integer.parseInt(turtleNumSplash.toString());
    TurtleWindowView turtleWindowView = new TurtleWindowView(splashView.toString());

    TurtleManager turtleManager = new TurtleManager(startingTurtles);
    TurtleViewManager turtleViewManager = new TurtleViewManager(turtleManager, turtleWindowView);

    turtleWindowView.addGroup(turtleViewManager.getNode());

    Controller controller = new Controller(languageProbe.toString(), turtleManager);
    Display mainIDEView = new MainIDEView(languageProbe.toString(), controller, splashView.toString(), stage, turtleViewManager);

  }

}
