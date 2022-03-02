package slogo.view.util;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import slogo.view.windows.Display;

import java.awt.*;

/**
 * Generator class for Color Pickers displays
 * cpStage is a factory method that creates a window with a color picker on it
 * The picked color is stored in the color picker itself and can be accessed via the getCp getter method
 *
 * @author Zack Schrage
 */
public class ColorPickerGenerator extends Display {

    public static final int SIZE_X = 400;
    public static final int SIZE_Y = 400;
    private ColorPicker cp;

    /**
     * Factory for a stage with a color picker on it
     * Is made visible by having the returned stage have the show() method called on it
     * @return JavaFX Stage with a color picker on it
     */
    public Stage cpStage() {
        Pane root = new Pane();
        cp = new ColorPicker();
        root.getChildren().add(cp);
        Stage stage =  createStage("Color Picker", new Dimension(SIZE_X, SIZE_Y), root, "light");

        stage.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                stage.close();
            }
        });

        return stage;
    }

    /**
     * Getter method for the color picker
     * @return JavaFX Color picker
     */
    public ColorPicker getCp() {
        return cp;
    }
}
