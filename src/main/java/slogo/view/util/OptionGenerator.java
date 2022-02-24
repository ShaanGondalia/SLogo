package slogo.view.util;

import java.util.List;
import javafx.stage.Stage;

public record OptionGenerator(Stage stage, String toSet, List<String> options, String errorMessage,
                              List<String> implemented) {

}
