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
import java.util.*;

import slogo.model.turtle.PenState;
import slogo.model.turtle.Pose;
import slogo.model.turtle.Turtle;
import slogo.model.turtle.TurtleStatus;
import slogo.view.util.Coordinate;
import slogo.view.util.Matrix;
import slogo.view.util.TurtleAnimation;
import slogo.view.windows.TurtleAnimationController;
import slogo.view.windows.TurtleWindowView;

/**
 * Front end representation of the Turtle. It implements PropertyChangeListener and is tied to a backend turtle.
 * Its propertyChange method is invoked upon a property change occurring in the backend turtle
 * and that animation is reflected in this front end turtle
 *
 * @author Zack Schrage
 */
public class TurtleView implements PropertyChangeListener  {

    private static final double CENTER_X = TurtleWindowView.WIDTH / 2;
    private static final double CENTER_Y = TurtleWindowView.HEIGHT / 2;

    public static final String DEFAULT_ACTION = "Default";

    private static final Matrix ctm = new Matrix(1, 0, CENTER_X, 0, -1, CENTER_Y);
    private static Map<String, TurtleConsumer> ACTION_MAP;

    private TurtleWindowView turtleWindowView;
    private Coordinate origin = ctm.mapPoint(new Coordinate(0, 0));
    private Image turtleImage;
    private Image invisibleTurtle;
    private ImageView turtleNode;
    private List<Trail> trailHistory;
    private GraphicsContext graphicsContext;
    private Queue<TurtleAnimation> animationQueue = new LinkedList<>();
    private boolean isAnimating = false;
    private int lastDrawnTrailId = 0;
    private double epsilon = 0.001;
    private double epsilon2 = 10;
    private double pathSpeed = 0.01; //Hard coded default for now
    private double rotationSpeed = 0.75; //Hard coded default for now
    private int thickness = 2; //Hard coded default for now
    private Color trailColor = Color.BLACK; //Hard coded default for now

    public TurtleView(TurtleWindowView turtleWindowViewIn) {
        turtleImage = new Image(getClass().getResourceAsStream("/view/img/turtle.png"));
        invisibleTurtle = new Image(getClass().getResourceAsStream("/view/img/invisibleTurtle.png"));
        turtleNode = new ImageView(turtleImage);
        turtleNode.setX(origin.x() - turtleImage.getWidth()/2);
        turtleNode.setY(origin.y() - turtleImage.getHeight()/2);
        trailHistory = new ArrayList<>();
        turtleWindowView = turtleWindowViewIn;
        graphicsContext = turtleWindowView.canvas.getGraphicsContext2D();
        graphicsContext.setLineWidth(thickness);
        graphicsContext.setFill(trailColor);
        graphicsContext.setStroke(trailColor);
        instantiateLambdaMap();
    }

    public TurtleView(Turtle turtle, TurtleWindowView turtleWindowView) {
        this(turtleWindowView);
        // moves turtle to starting position
        Pose startPose = new Pose(0, 0, 0);
        TurtleStatus newValue = turtle.getStatus();
        TurtleStatus oldValue = new TurtleStatus(startPose, newValue.penState(), newValue.isActive(),
            newValue.isActive());
        PropertyChangeEvent evt = new PropertyChangeEvent(turtle, "Pose", oldValue, newValue);
        changePose(evt);
    }

    private Animation makeAnimation(Pose oldPose, Pose newPose, PenState penState, boolean hasRealTime) {
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

        if (hasRealTime) realTimeTrailAnimation(pt, penState, start, end);

        SequentialTransition st = new SequentialTransition(turtleNode);
        if (deltaS < epsilon) st.getChildren().add(rt);
        else st.getChildren().add(pt);
        return st;
    }

    private void realTimeTrailAnimation(PathTransition pt, PenState ps, Coordinate start, Coordinate end) {
        trailHistory.add(new Trail(new Line(start.x(), start.y(), end.x(), end.y()), Color.web(ps.color().toString()), ps.thickness()));
        pt.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            Color color = Color.web(ps.color().toString());
            double thickness = ps.thickness();
            double prevX = start.x();
            double prevY = start.y();

            @Override
            public void changed(ObservableValue<? extends Duration> observableValue, Duration duration, Duration t1) {
                graphicsContext.setStroke(color);
                graphicsContext.setLineWidth(thickness);
                double x = turtleNode.getTranslateX();
                double y = turtleNode.getTranslateY();
                if (validTransitionStroke(prevX, x, prevY, y)) graphicsContext.strokeLine(prevX + CENTER_X, prevY + CENTER_Y, x + CENTER_X, y + CENTER_Y);
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

    private void handleAnimationQueue(boolean hasRealTime) {
        animationQueue.remove();
        if (hasRealTime) drawLastTrail(trailHistory.get(lastDrawnTrailId++));
        handleAnimation();
    }

    private void handleAnimation() {
        if (animationQueue.size() > 0 && TurtleAnimationController.getPlayStatus()) {
            isAnimating = true;
            handleTurtleImage(animationQueue.peek().getVisibility(), true);
            animationQueue.peek().getAnimation().play();
        }
        else isAnimating = false;
    }

    private void handleTurtleImage(boolean visible, boolean active) {
        if (visible) {
            turtleNode.setImage(turtleImage);
            if (active) turtleNode.setOpacity(1.0);
            else turtleNode.setOpacity(0.5);
        }
        else turtleNode.setImage(invisibleTurtle);
    }

    private void drawLastTrail(Trail trail) {
        Line line = trail.getLine();
        Color color = trail.getColor();
        double thickness = trail.getThickness();
        graphicsContext.setFill(color);
        graphicsContext.setStroke(color);
        graphicsContext.setLineWidth(thickness);
        graphicsContext.strokeLine(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());
    }

    private static void instantiateLambdaMap() {
        ACTION_MAP = new HashMap<>();
        ACTION_MAP.put(Turtle.POSE_CHANGED, (turtle, evt) -> turtle.changePose(evt));
        ACTION_MAP.put(Turtle.VISIBILITY_CHANGED, (turtle, evt) -> turtle.changeVisibility(evt));
        ACTION_MAP.put(Turtle.TRAILS_CLEARED, (turtle, evt) -> turtle.changeClear(evt));
        ACTION_MAP.put(DEFAULT_ACTION, (turtle, evt) -> {});
    }

    private void changePose(PropertyChangeEvent evt) {
        TurtleStatus oldT = (TurtleStatus) evt.getOldValue();
        TurtleStatus newT = (TurtleStatus) evt.getNewValue();
        boolean hasRealTime = (newT.penState().penDown() && normSquared(oldT.pose(), newT.pose()) > epsilon);
        Animation anim = makeAnimation(oldT.pose(), newT.pose(), newT.penState(), hasRealTime);
        anim.setOnFinished(finish -> handleAnimationQueue(hasRealTime));
        animationQueue.add(new TurtleAnimation(anim, newT.visibility()));
        if (!isAnimating) handleAnimation();
    }

    private void changeVisibility(PropertyChangeEvent evt) {
        TurtleStatus newT = (TurtleStatus) evt.getNewValue();
        handleTurtleImage(newT.visibility(), newT.isActive());
    }

    private void changeClear(PropertyChangeEvent evt) {
        trailHistory = new ArrayList<>();
        lastDrawnTrailId = 0;
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillRect(0, 0, turtleWindowView.canvas.getWidth(), turtleWindowView.canvas.getHeight());
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
        ACTION_MAP.getOrDefault(evt.getPropertyName(), ACTION_MAP.get("Default")).accept(this, evt);
    }

    /**
     * Getter method for the turtle's image view node
     * @return JavaFX Node containing the turtle image
     */
    public Node getTurtleNode() {
        return turtleNode;
    }
//
//    /**
//     * Setter method for the turtle's trail color
//     * @param color new trail color
//     */
//    public void setTrailColor(Color color) {
//        trailColor = color;
//        graphicsContext.setFill(trailColor);
//        graphicsContext.setStroke(trailColor);
//    }

    /**
     * Getter method for a turtle's trail history
     * @return turtle's trail history
     */
    public List<Trail> getTrailHistory() {
        return trailHistory;
    }

    /**
     * Setter method for a turtle's image
     * @param name of image in resources
     */
    public void setTurtleImage(String name) {
        turtleImage = new Image(getClass().getResourceAsStream("/view/img/" + name));
        handleTurtleImage(true, true);
    }

    public boolean isAnimating() {
        return isAnimating;
    }

    public Queue<TurtleAnimation> getAnimationQueue() {
        return animationQueue;
    }

}
