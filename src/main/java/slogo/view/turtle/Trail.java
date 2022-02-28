package slogo.view.turtle;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Trail {

    private Line line;
    private Color color;

    public Trail(Line line, Color color) {
        this.line = line;
        this.color = color;
    }

    public Line getLine() {
        return line;
    }

    public Color getColor() {
        return color;
    }

}
