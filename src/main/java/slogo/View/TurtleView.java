package slogo.View;

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import slogo.Model.Pose;
import java.net.URL;

public class TurtleView {

    private Pose currentPose;
    private Node turtleNode;
    private double timeInSeconds = 1; //Hard coded for now

    public TurtleView(Pose p) {
        currentPose = p;
        URL url = getClass().getResource("View/img/turtle.png");
        turtleNode = new ImageView(url.toString());
    }

    /**
     * Expects a new Pose object and animates a turtle image from its current pose to the new pose
     * @param newPose the new pose for the view to animate
     */
    public void updateTurtle(Pose newPose) {
        makeAnimation(newPose).play();
        currentPose = newPose;
    }

    private Animation makeAnimation(Pose newPose) {
        Path path = new Path();
        path.getElements().add(new MoveTo(currentPose.x(), currentPose.y()));
        path.getElements().add(new LineTo(newPose.x(), newPose.y()));

        RotateTransition rt = new RotateTransition(Duration.seconds(timeInSeconds), turtleNode);
        rt.setByAngle(changeInBearing(newPose));

        PathTransition pt = new PathTransition(Duration.seconds(timeInSeconds), path, turtleNode);

        return new SequentialTransition(turtleNode, rt, pt);
    }

    private double changeInBearing(Pose newPose) {
        return currentPose.bearing() - newPose.bearing();
    }

}
