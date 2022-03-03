package slogo.view.windows;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.controller.Controller;
import slogo.model.turtle.Pose;
import slogo.view.turtle.TurtleView;
import slogo.view.windows.LanguageSplash;
import slogo.view.windows.TurtleWindowView;

import static org.junit.jupiter.api.Assertions.*;

class TurtleViewTest {

  LanguageSplash languageSplash;
  Controller c;
  TurtleWindowView window;
  TurtleView tv;

  @BeforeEach
  void setUp() {
    //languageProbe = new LanguageProbe();
    c = new Controller("English", null);
    window = new TurtleWindowView("light");
    tv = new TurtleView();
    window.addTurtleView(tv);
    c.addTurtle(tv);
  }

  @Test
  void propertyChange() {
    c.getTurtles().get(0).setPose(new Pose(20, 20, 0));
    assertTrue(((ImageView) tv.getTurtleNode()).getX() == 20);
  }

  @Test
  void getTurtleNode() {
    Node n = tv.getTurtleNode();
    assertTrue(n.getClass() == javafx.scene.Node.class);
  }
}