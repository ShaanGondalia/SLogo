package slogo.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.controller.Controller;
import slogo.view.turtle.TurtleView;
import slogo.view.windows.LanguageSplash;
import slogo.view.windows.TurtleWindowView;

import static org.junit.jupiter.api.Assertions.*;

class TurtleWindowViewTest {

    LanguageSplash languageSplash;
    Controller c;
    TurtleWindowView window;
    TurtleView tv;

    @BeforeEach
    void setUp() {
        //languageProbe = new LanguageProbe();
        c = new Controller("English");
        window = new TurtleWindowView();
    }

    @Test
    void createStage() {
        assertTrue(true);
    }

    @Test
    void addTurtleView(TurtleView tv) {
        window.addTurtleView(tv);
        assertTrue(true);
    }

}