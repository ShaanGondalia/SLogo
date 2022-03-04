package slogo.view.turtle;

import java.util.ArrayList;
import javafx.scene.Group;
import slogo.model.turtle.Turtle;
import slogo.view.windows.TurtleWindowView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class TurtleViewManager implements PropertyChangeListener {

    private TurtleWindowView turvleViewWindow;
    private List<TurtleView> turtleViewList;

    private Group myNode = new Group();

    public TurtleViewManager(TurtleWindowView turtleViewWindow) {
        this.turvleViewWindow = turtleViewWindow;
        turtleViewList = new ArrayList<>();
    }

    public TurtleView createTurtleView() {
        TurtleView tv = new TurtleView();
        turtleViewList.add(tv);
        myNode.getChildren().add(tv.getTurtleNode());

        // this line would become irrelevant
        turvleViewWindow.addTurtleView(tv);
        return tv;
    }

    public Group getNode() {
        return myNode;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Turtle t = (Turtle) evt.getNewValue();
        t.addListener(createTurtleView());
    }

    public List<TurtleView> getTurtleViewList() {
        return turtleViewList;
    }
}
