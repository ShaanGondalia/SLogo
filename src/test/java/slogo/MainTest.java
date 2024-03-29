package slogo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.compiler.Parser;
import slogo.model.turtle.Pose;
import slogo.model.turtle.TestListener;
import slogo.model.turtle.Turtle;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Feel free to completely change this code or delete it entirely. 
 */
class MainTest {

    private Main m;

    // create new instance of test object before each test is run
    @BeforeEach
    void setup () {
        m = new Main();
    }

    @Test
    void parserTest () {
        Parser p = new Parser("English");
        p.addPatterns("English");
        p.addPatterns("Syntax");
    }

    @Test
    void observerTest () {
        Turtle t = new Turtle();
        TestListener l = new TestListener();
        t.addListener(l);

        t.setPose(new Pose(1, 1, 1));

        assertTrue("Pose".equals(l.getChange()));
    }

    @Test
    void testVersionIsReady () {
        // how close do real valued numbers need to be to count as the same
        final double TOLERANCE = 0.0005;

        // different ways to test double results
        assertEquals(1, Math.round(m.getVersion() * 1000));
        assertTrue(m.getVersion() < 1);
        assertEquals(0.001, m.getVersion(), TOLERANCE);
    }

    @Test
    void testReadExampleProgram () {
        assertEquals("fd sum 1 random 100", m.getExampleProgram("simple", "random_fun"));
        assertEquals("to triangle [ ]", m.getExampleProgram("procedures", "geometry"));
    }

    @Test
    void testReadLanguageProperties () {
        final String COMMAND = "Forward";

        assertEquals("forward|fd", m.getCommand("English", COMMAND));
        assertEquals("devant|dev", m.getCommand("French", COMMAND));
        assertEquals("vpered|vr", m.getCommand("Russian", COMMAND));
    }
}
