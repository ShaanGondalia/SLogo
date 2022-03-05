package slogo.view.turtle;

import java.awt.*;
import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import slogo.model.turtle.Turtle;
import slogo.model.turtle.TurtleManager;
import slogo.view.windows.TurtleWindowView;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TurtleViewManager implements PropertyChangeListener {

    private List<TurtleView> myTurtleViewList;
    private Map<TurtleView, TurtleTooltip> tooltipMap;
    private TurtleManager myTurtleManager;
    private TurtleWindowView myTurtleWindowView;
    private Group myNode = new Group();
    private int mouseDelta = 10;

    public TurtleViewManager(TurtleManager turtleManager, TurtleWindowView turtleWindowView) {
        myTurtleViewList = new ArrayList<>();
        tooltipMap = new HashMap<>();
        myTurtleManager = turtleManager;
        myTurtleWindowView = turtleWindowView;
        appendListenerToWindow(myTurtleWindowView);

        addStartingTurtles();
        turtleManager.addListener(this);
    }

    private void addStartingTurtles() {
        if (myTurtleManager.numTurtles() > 0) {
            for (Turtle t : myTurtleManager.getTurtles()) {
                createTurtleView(t);
            }
        }
    }

    private void appendListenerToWindow(TurtleWindowView myTurtleWindowView) {
        myTurtleWindowView.getPane().setOnMouseMoved(e -> {
            for (TurtleView tv : myTurtleViewList) {
                if (withinTurtleCenter(e.getX(), e.getY(), tv)) {
                    if (tooltipMap.get(tv) == null) {
                        TurtleTooltip turtleTooltip = new TurtleTooltip((int)e.getX() + 10, (int)e.getY() + 10);
                        turtleTooltip.setText(getTurtleInfo(tv));
                        tooltipMap.put(tv, turtleTooltip);
                        myTurtleWindowView.getPane().getChildren().add(tooltipMap.get(tv).getBackground());
                        myTurtleWindowView.getPane().getChildren().add(tooltipMap.get(tv).getText());
                    }
                    else {
                        tooltipMap.get(tv).updatePosition((int)e.getX() + 10, (int)e.getY() + 10);
                    }
                }
                else if (tooltipMap.get(tv) != null) {
                    myTurtleWindowView.getPane().getChildren().remove(tooltipMap.get(tv).getBackground());
                    myTurtleWindowView.getPane().getChildren().remove(tooltipMap.get(tv).getText());
                    tooltipMap.put(tv, null);
                }
            }
        });
    }

    private boolean withinTurtleCenter(double x, double y, TurtleView tv) {
       return (Math.abs(x - (tv.getTurtleNode().getTranslateX() + TurtleView.CENTER_X)) < mouseDelta && Math.abs(y - (tv.getTurtleNode().getTranslateY() + TurtleView.CENTER_Y)) < mouseDelta);
    }

    private String getTurtleInfo(TurtleView tv) {
        double id = tv.getStatus().id();
        double x = tv.getStatus().pose().x();
        double y = tv.getStatus().pose().y();
        return String.format("ID: %.0f\nx: %.0f\ny: %.0f", id, x, y);
    }

    public TurtleView createTurtleView(Turtle turtle) {
        TurtleView turtleView = new TurtleView(turtle, myTurtleWindowView);
        turtle.addListener(turtleView);
        myTurtleViewList.add(turtleView);
        myNode.getChildren().add(turtleView.getTurtleNode());

        return turtleView;
    }

    public TurtleWindowView getTurtleWindowView() {
        return myTurtleWindowView;
    }

    public Group getNode() {
        return myNode;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Turtle t = (Turtle) evt.getNewValue();
        createTurtleView(t);
    }

    public List<TurtleView> getMyTurtleViewList() {
        return myTurtleViewList;
    }
}
