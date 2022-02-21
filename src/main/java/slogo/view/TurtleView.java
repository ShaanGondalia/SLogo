package slogo.view;

import java.net.URL;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private static double CENTER = TurtleWindowView.HEIGHT / 2;

    private ImageView turtleNode; // for now
    private double timeInSeconds = 5; //Hard coded for now
    private Queue<PropertyChangeEvent> changeQueue = new LinkedList<>();

    public TurtleView() {
        Image image = new Image(getClass().getResourceAsStream("/view/img/turtle.png"));
        turtleNode = new ImageView(image);
//        turtleNode = new Rectangle(SIZE, SIZE);
//        turtleNode.setFill(Color.RED);
        turtleNode.setX(CENTER);
        turtleNode.setY(CENTER);

    }

    private Animation makeAnimation(Pose oldPose, Pose newPose) {
        Path path = new Path();

        double deltaR = newPose.bearing() - oldPose.bearing();
        path.getElements().add(new MoveTo(oldPose.x() + CENTER, oldPose.y() + CENTER));
        path.getElements().add(new LineTo(newPose.x() + CENTER, newPose.y() + CENTER));

        double timeS = timeInSeconds;
        if (Math.abs(deltaR) < 0.1){
            timeS = 0;
        }
        RotateTransition rt = new RotateTransition(Duration.seconds(timeS), turtleNode);
        rt.setByAngle(deltaR);

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
