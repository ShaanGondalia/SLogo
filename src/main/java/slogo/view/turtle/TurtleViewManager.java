package slogo.view.turtle;

import slogo.model.turtle.Turtle;
import slogo.view.windows.TurtleWindowView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class TurtleViewManager implements PropertyChangeListener {

    private TurtleWindowView turvleViewWindow;
    private List<TurtleView> turtleViewList;

    public TurtleViewManager(TurtleWindowView turvleViewWindow) {
        this.turvleViewWindow = turvleViewWindow;
    }

    public TurtleView createTurtleView() {
        TurtleView tv = new TurtleView();
        turtleViewList.add(tv);
        turvleViewWindow.addTurtleView(tv);
        return tv;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Turtle t = (Turtle) evt.getNewValue();
        t.addListener(createTurtleView());
    }
}
