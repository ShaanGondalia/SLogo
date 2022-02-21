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
    private static double SIZE = 20;
    private static double CENTER = TurtleWindowView.HEIGHT / 2;

    private ImageView turtleNode;
    private double timeInSeconds = 2; //Hard coded for now
    private Queue<Animation> animationQueue = new LinkedList<>();
    private Animation currentAnimation = null;

    public TurtleView() {
        Image image = new Image(getClass().getResourceAsStream("/view/img/turtle.png"));
        turtleNode = new ImageView(image);
        turtleNode.setX(CENTER);
        turtleNode.setY(CENTER);

    }

    private Animation makeAnimation(Pose oldPose, Pose newPose) {
        Path path = new Path();
        path.getElements().add(new MoveTo(oldPose.x() + CENTER, oldPose.y() + CENTER));
        path.getElements().add(new LineTo(newPose.x() + CENTER, newPose.y() + CENTER));

        double deltaR = newPose.bearing() - oldPose.bearing();
        double rotationTime = timeInSeconds;
        if (Math.abs(deltaR) < 0.1) rotationTime = 0;
        RotateTransition rt = new RotateTransition(Duration.seconds(rotationTime), turtleNode);
        rt.setByAngle(deltaR);

        double deltaS = normSquared(oldPose, newPose);
        double pathTime = timeInSeconds;
        if (Math.abs(deltaS) < 0.1) pathTime = 0;
        PathTransition pt = new PathTransition(Duration.seconds(pathTime), path, turtleNode);

        return new SequentialTransition(turtleNode, rt, pt);
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
        currentAnimation = null;
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
