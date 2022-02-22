package slogo.view;

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedList;
import java.util.Queue;

import slogo.model.turtle.Pose;
import slogo.model.turtle.TurtleStatus;

public class TurtleView implements PropertyChangeListener  {

    private static double centerX = TurtleWindowView.WIDTH / 2;
    private static double centerY = TurtleWindowView.HEIGHT / 2;
    private static Matrix ctm = new Matrix(1, 0, centerX, 0, -1, centerY);

    private Coordinate origin = ctm.mapPoint(new Coordinate(0, 0));
    private ImageView turtleNode;
    private Queue<Animation> animationQueue = new LinkedList<>();
    private Animation currentAnimation = null;
    private double timeInSeconds = 1; //Hard coded for now

    public TurtleView() {
        turtleNode = new ImageView(new Image(getClass().getResourceAsStream("/view/img/turtle.png")));
//        turtleNode.setX(centerX - (turtleNode.getFitWidth()/2));
//        turtleNode.setY(centerY - (turtleNode.getFitHeight()/2));
        turtleNode.setX(origin.x());
        turtleNode.setY(origin.y());
        System.out.printf("Origin: (%f, %f)\n", origin.x(), origin.y());
    }

    private Animation makeAnimation(Pose oldPose, Pose newPose) {
        Coordinate start = ctm.mapPoint(new Coordinate(oldPose.x(), oldPose.y()));
        System.out.printf("Start: (%f, %f)\n", start.x(), start.y());
        Coordinate end = ctm.mapPoint(new Coordinate(newPose.x(), newPose.y()));
        Path path = new Path();
        path.getElements().add(new MoveTo(start.x(), start.y()));
        path.getElements().add(new LineTo(end.x(), end.y()));

        double deltaR = newPose.bearing() - oldPose.bearing();
        RotateTransition rt = new RotateTransition(Duration.seconds(timeInSeconds), turtleNode);
        rt.setByAngle(deltaR);

        double deltaS = normSquared(oldPose, newPose);
        PathTransition pt = new PathTransition(Duration.seconds(timeInSeconds), path, turtleNode);

        SequentialTransition st = new SequentialTransition(turtleNode);
        if (Math.abs(deltaR) > 0.1) st.getChildren().add(rt);
        else st.getChildren().add(pt);
        return st;
    }

    private double normSquared(Pose oldPose, Pose newPose) {
        return (Math.pow(oldPose.x() - newPose.x(), 2.0) + Math.pow(oldPose.y() - newPose.y(), 2.0));
    }

    private void handleAnimationQueue() {
        animationQueue.remove();
        if (animationQueue.size() > 0) {
            currentAnimation = animationQueue.peek();
            currentAnimation.play();
        }
        else currentAnimation = null;
    }

    /**
     * Expects a new Pose object and animates a turtle image from its current pose to the new pose
     * @param evt the new pose for the view to animate
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        TurtleStatus oldT = (TurtleStatus) evt.getOldValue();
        TurtleStatus newT = (TurtleStatus) evt.getNewValue();
        Animation anim = makeAnimation(oldT.pose(), newT.pose());
        anim.setOnFinished(finish -> handleAnimationQueue());
        animationQueue.add(anim);
        if (currentAnimation == null) {
            currentAnimation = anim;
            anim.play();
        }
    }

    public Node getTurtleNode() {
        return turtleNode;
    }

}
