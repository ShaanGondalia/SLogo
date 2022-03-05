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

    private Group myNode = new Group();

    public TurtleViewManager(TurtleManager turtleManager) {
        myTurtleViewList = new ArrayList<>();
        myTurtleManager = turtleManager;

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

    public TurtleView createTurtleView(Turtle turtle) {
        TurtleView turtleView = new TurtleView(turtle);
        turtle.addListener(turtleView);
        myTurtleViewList.add(turtleView);
        myNode.getChildren().add(turtleView.getTurtleNode());

        return turtleView;
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
