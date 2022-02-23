package slogo;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import javafx.application.Application;
import javafx.stage.Stage;
import slogo.controller.Controller;
import slogo.view.Displayable;
import slogo.view.LanguageProbe;
import slogo.view.TurtleView;
import slogo.view.TurtleWindowView;


/**
 * Feel free to completely change this code or delete it entirely.
 */
public class Main extends Application {

  private static final String LANGUAGE_RESOURCE_PATH = "/slogo/languages/";
  private static final String EXAMPLE_PROGRAMS_PATH = "/examples";

  public static double TOLERANCE = 0.01;

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
    return lines.get(0).trim();
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


  /**
   * Start of the program.
   */
//    public static void main (String[] args) {
//        Main m = new Main();
//        System.out.println(m.getVersion());
//        System.out.println(m.getCommand("English", "Forward"));
//        System.out.println(m.getExampleProgram("loops", "star"));
//    }

  private static final List<String> VIEWS_TO_CREATE = List.of("SplashView", "MainIDEView",
      "TurtleWindowView");
  private static Displayable turtleWindowView;

  @Override
  public void start(Stage stage) throws ClassNotFoundException {

    LanguageProbe languageProbe = new LanguageProbe();
    Controller c = new Controller(languageProbe.toString());
    for (String name : VIEWS_TO_CREATE) {
      Displayable d = createViews(name);
      turtleWindowView = d;
      d.createStage(languageProbe.toString(), c);
    }
    // THIS IS THE PROCEDURE FOR ADDING NEW TURTLES
    TurtleView tv = new TurtleView();
    c.addTurtle(tv);
    ((TurtleWindowView) turtleWindowView).addTurtleView(tv);
  }

  private Displayable createViews(String name) {
    try {
      Class<?> clazz = Class.forName("slogo.view." + name);
      Constructor ctor = clazz.getConstructor();
      return (Displayable) ctor.newInstance();
    } catch (Exception e) {
      Errors.showAndClose("Could not find class: " + name);
      throw new InputMismatchException();
    }
  }
}
