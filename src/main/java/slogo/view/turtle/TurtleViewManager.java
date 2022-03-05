package slogo.view.turtle;

import java.util.ArrayList;
import javafx.scene.Group;
import slogo.model.turtle.Turtle;
import slogo.model.turtle.TurtleManager;
import slogo.view.windows.TurtleWindowView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class TurtleViewManager implements PropertyChangeListener {

    private List<TurtleView> myTurtleViewList;
    private TurtleManager myTurtleManager;
    private TurtleWindowView myTurtleWindowView;
    private Group myNode = new Group();
    private int mouseDelta = 5;

    public TurtleViewManager(TurtleManager turtleManager, TurtleWindowView turtleWindowView) {
        myTurtleViewList = new ArrayList<>();
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

                }
            }
        });
    }

    private boolean withinTurtleCenter(double x, double y, TurtleView tv) {
       // return Math.abs(x - tv.getTurtleNode().getLayoutBounds().getCenterX());
        return false;
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
