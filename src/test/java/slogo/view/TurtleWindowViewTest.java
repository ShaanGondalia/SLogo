package slogo.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.controller.Controller;

import static org.junit.jupiter.api.Assertions.*;

class TurtleWindowViewTest {

    LanguageProbe languageProbe;
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
        window.createStage("English", c);
        assertTrue(true);
    }

    @Test
    void addTurtleView(TurtleView tv) {
        window.addTurtleView(tv);
        assertTrue(true);
    }

}