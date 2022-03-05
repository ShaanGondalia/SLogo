package slogo.view.windows;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import slogo.view.turtle.TurtleView;
import slogo.view.util.ButtonUtil;
import slogo.view.util.TurtleAnimation;

import java.awt.*;

/**
 * A window containing different controls pertaining to a turtle's animation
 * A button toggles the playing and pausing of the animations
 * A slider changes the speed of the turtle's animation
 *
 * @author Zack Schrage
 */
public class TurtleAnimationController extends Display {

    public static final int SIZE_X = 400;
    public static final int SIZE_Y = 400;
    private static boolean play = true;

    public TurtleAnimationController(ButtonUtil info) {
        Pane root = new Pane();

        Button togglePlay = new Button("Pause");
        togglePlay.setOnAction(actionEvent ->  {
            togglePlayPause(info);
        });

        Slider speed = new Slider();
        speed.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                changeSpeed(info, t1.intValue());
            }
        });

        root.getChildren().add(togglePlay);
        root.getChildren().add(speed);
        Stage s = createStage("Animation Controller", new Dimension(SIZE_X, SIZE_Y), root, "light");
        s.show();
    }

    private void togglePlayPause(ButtonUtil info) {
        play = !play;
        for (TurtleView tv : info.tvm().getMyTurtleViewList()) {
            if (tv.isAnimating()) {
                if (!play) tv.getAnimationQueue().peek().getAnimation().pause();
                else tv.getAnimationQueue().peek().getAnimation().play();
            }
        }
    }

    private void changeSpeed(ButtonUtil info, int speed) {
        int speedMultiplier = (speed/10) + 1;
        for (TurtleView tv : info.tvm().getMyTurtleViewList()) {
            for (TurtleAnimation anim : tv.getAnimationQueue()) {
                anim.getAnimation().setRate(speedMultiplier);
            }
        }
    }

    /**
     * Getter method for the pause play status of the turtle animations
     * @return boolean should be playing (not paused)
     */
    public static boolean getPlayStatus() {
        return play;
    }

}
