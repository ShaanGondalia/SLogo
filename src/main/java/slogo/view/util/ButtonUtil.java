package slogo.view.util;

import java.util.ResourceBundle;
import slogo.controller.Controller;
import slogo.view.windows.sections.HistorySection;
import slogo.view.windows.sections.TextSection;

public record ButtonUtil(String name, Controller c, ResourceBundle resourceBundle, HistorySection histSec, TextSection textSec) {

}
