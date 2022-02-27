package slogo.view.turtle;

import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.List;

public class TrailHistory {

    private List<Line> trailHistory;

    public TrailHistory() {
        trailHistory = new ArrayList<>();
    }

    public void add(Line line) {
        trailHistory.add(line);
    }

    public List<Line> getTrails() {
        return trailHistory;
    }

}
