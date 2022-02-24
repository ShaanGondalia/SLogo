package slogo.view.util;

import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.stage.Stage;

public record OptionGenerator(Stage stage, ResourceBundle res, String splashType,
                              Set<String> implemented) {

}
