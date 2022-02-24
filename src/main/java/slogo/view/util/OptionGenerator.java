package slogo.view.util;

import java.util.ResourceBundle;
import java.util.Set;
import javafx.stage.Stage;

/**
 * Record to pass in to create splash screen buttons
 * @author Andy S. He
 */
public record OptionGenerator(Stage stage, ResourceBundle res, String splashType,
                              Set<String> implemented) {

}
