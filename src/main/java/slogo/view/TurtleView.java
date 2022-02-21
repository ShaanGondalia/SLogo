package slogo.view;

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedList;
import java.util.Queue;

import slogo.model.turtle.Pose;
import slogo.model.turtle.TurtleStatus;

public class TurtleView implements PropertyChangeListener  {
    private static double SIZE = 20;

    private Rectangle turtleNode; // for now
    private double timeInSeconds = 10; //Hard coded for now
    private Queue<PropertyChangeEvent> changeQueue = new LinkedList<>();

    public TurtleView() {
//        URL url = getClass().getResource("View/img/turtle.png");
//        turtleNode = new ImageView(url.toString());
        turtleNode = new Rectangle(SIZE, SIZE);
        turtleNode.setFill(Color.RED);
    }

    private Animation makeAnimation(Pose oldPose, Pose newPose) {
        Path path = new Path();
        path.getElements().add(new MoveTo(oldPose.x(), oldPose.y()));
        path.getElements().add(new LineTo(newPose.x(), newPose.y()));

        RotateTransition rt = new RotateTransition(Duration.seconds(timeInSeconds), turtleNode);
        rt.setByAngle(newPose.bearing() - oldPose.bearing());

        PathTransition pt = new PathTransition(Duration.seconds(timeInSeconds), path, turtleNode);

        return new SequentialTransition(turtleNode, rt, pt);
    }

    /**
     * Expects a new Pose object and animates a turtle image from its current pose to the new pose
     * @param evt the new pose for the view to animate
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //changeQueue.add(evt);
        TurtleStatus oldT = (TurtleStatus) evt.getOldValue();
        TurtleStatus newT = (TurtleStatus) evt.getNewValue();
        makeAnimation(oldT.pose(), newT.pose()).play();
    }

    public Node getTurtleNode() {
        return turtleNode;
    }

}
