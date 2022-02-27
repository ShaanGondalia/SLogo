package slogo.view.turtle;

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
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
import slogo.view.util.Coordinate;
import slogo.view.util.Matrix;
import slogo.view.util.TurtleAnimation;
import slogo.view.windows.TurtleWindowView;

/**
 * Front end representation of the Turtle. It implements PropertyChangeListener and is tied to a backend turtle.
 * Its propertyChange method is invoked upon a property change in the backend turtle and that animation is reflected
 * on this front end turtle
 *
 * @author Zack Schrage
 */
public class TurtleView implements PropertyChangeListener  {

    private static double centerX = TurtleWindowView.WIDTH / 2;
    private static double centerY = TurtleWindowView.HEIGHT / 2;
    private static Matrix ctm = new Matrix(1, 0, centerX, 0, -1, centerY);
    private Coordinate origin = ctm.mapPoint(new Coordinate(0, 0));
    private Image turtleImage;
    private Image invisibleTurtle;
    private ImageView turtleNode;
    private TrailHistory trailHistory;
    private GraphicsContext gc;
    private Queue<TurtleAnimation> animationQueue = new LinkedList<>();
    private boolean isAnimating = false;
    private double epsilon = 0.001;
    private double epsilon2 = 10;
    private double pathSpeed = 0.01; //Hard coded default for now
    private double rotationSpeed = 0.75; //Hard coded default for now
    private int thickness = 2; //Hard coded default for now
    private Color trailColor = Color.BLACK; //Hard coded default for now

    public TurtleView() {
        turtleImage = new Image(getClass().getResourceAsStream("/view/img/turtle.png"));
        invisibleTurtle = new Image(getClass().getResourceAsStream("/view/img/invisibleTurtle.png"));
        turtleNode = new ImageView(turtleImage);
        turtleNode.setX(origin.x() - turtleImage.getWidth()/2);
        turtleNode.setY(origin.y() - turtleImage.getHeight()/2);
        trailHistory = new TrailHistory();
        gc = TurtleWindowView.CANVAS.getGraphicsContext2D();
        gc.setLineWidth(thickness);
        gc.setFill(trailColor);
    }

    private Animation makeAnimation(Pose oldPose, Pose newPose, boolean penDown) {
        Coordinate start = ctm.mapPoint(new Coordinate(oldPose.x(), oldPose.y()));
        Coordinate end = ctm.mapPoint(new Coordinate(newPose.x(), newPose.y()));

        Path path = new Path();
        path.getElements().add(new MoveTo(start.x(), start.y()));
        path.getElements().add(new LineTo(end.x(), end.y()));

        double deltaR = changeInBearing(oldPose, newPose);
        RotateTransition rt = new RotateTransition(Duration.seconds(rotationSpeed), turtleNode);
        rt.setByAngle(deltaR);

        double deltaS = normSquared(oldPose, newPose);
        PathTransition pt = new PathTransition(Duration.seconds(pathSpeed * Math.sqrt(deltaS)), path, turtleNode);

        if (penDown && deltaS > epsilon) realTimeTrailAnimation(pt, start, end);

        SequentialTransition st = new SequentialTransition(turtleNode);
        if (deltaS < epsilon) st.getChildren().add(rt);
        else st.getChildren().add(pt);
        return st;
    }

    private void realTimeTrailAnimation(PathTransition pt, Coordinate start, Coordinate end) {
        trailHistory.add(new Line(start.x(), start.y(), end.x(), end.y()));
        //System.out.printf("Line from (%5.2f, %5.2f) -> (%5.2f, %5.2f)\n", start.x(), start.y(), end.x(), end.y());
        pt.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            double prevX = start.x();
            double prevY = start.y();

            @Override
            public void changed(ObservableValue<? extends Duration> observableValue, Duration duration, Duration t1) {
                double x = turtleNode.getTranslateX();
                double y = turtleNode.getTranslateY();
                if (validTransitionStroke(prevX, x, prevY, y)) gc.strokeLine(prevX + centerX, prevY + centerY, x + centerX, y + centerY);
                //System.out.printf("(%5.2f, %5.2f) -> (%5.2f, %5.2f)\n", prevX + centerX, prevY + centerY, x + centerX, y + centerY);
                prevX = x;
                prevY = y;
            }
        });
    }

    private boolean validTransitionStroke(double x1, double x2, double y1, double y2) {
        if (Math.abs(x2- x1) > epsilon2) return false;
        if (Math.abs(y2- y1) > epsilon2) return false;
        return true;
    }

    private double normSquared(Pose oldPose, Pose newPose) {
        return (Math.pow(oldPose.x() - newPose.x(), 2.0) + Math.pow(oldPose.y() - newPose.y(), 2.0));
    }

    private double changeInBearing(Pose oldPose, Pose newPose) {
        return newPose.bearing() - oldPose.bearing();
    }

    private void handleAnimationQueue() {
        animationQueue.remove();
        handleAnimation();
    }

    private void handleAnimation() {
        if (animationQueue.size() > 0) {
            isAnimating = true;
            if (animationQueue.peek().getVisibility()) turtleNode.setImage(turtleImage);
            else turtleNode.setImage(invisibleTurtle);
            animationQueue.peek().getAnimation().play();
        }
        else isAnimating = false;
    }

    /**
     * Listens to changes in the backend turtle and reflects those changes in an animated manner
     * Turtles get animated from the old pose to a new pose
     * If a turtle's pen is down then it will animate and leave a trail as it moves
     * If a turtle's visibility is set to true, a turtle image will be shown, otherwise it will be hidden
     * @param evt a bundle of a property change containing an old and a new turtle status
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        TurtleStatus oldT = (TurtleStatus) evt.getOldValue();
        TurtleStatus newT = (TurtleStatus) evt.getNewValue();
        //System.out.println(newT.penDown());
        if (normSquared(oldT.pose(), newT.pose()) > epsilon || Math.abs(changeInBearing(oldT.pose(), newT.pose())) > epsilon) {
            Animation anim = makeAnimation(oldT.pose(), newT.pose(), newT.penDown());
            anim.setOnFinished(finish -> handleAnimationQueue());
            animationQueue.add(new TurtleAnimation(anim, newT.visibility()));
            if (!isAnimating) handleAnimation();
        }
    }

    /**
     * Getter method for the turtle's image view node
     * @return JavaFX Node containing the turtle image
     */
    public Node getTurtleNode() {
        return turtleNode;
    }

    /**
     * Getter method for the turtle's trail color
     * @return trail color
     */
    public Color getTrailColor() {
        return trailColor;
    }

    /**
     * Setter method for the turtle's trail color
     * @param color new trail color
     */
    public void setTrailColor(Color color) {
        trailColor = color;
        gc.setFill(trailColor);
    }

    /**
     * Getter method for a turtle's trail history
     * @return turtle's trail history
     */
    public TrailHistory getTrailHistory() {
        return trailHistory;
    }

}
