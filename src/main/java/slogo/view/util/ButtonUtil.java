package slogo.view.util;

import slogo.controller.Controller;
import slogo.view.turtle.TurtleViewManager;
import slogo.view.windows.sections.HistorySection;
import slogo.view.windows.sections.TextSection;
import slogo.view.windows.sections.DataSection;

public record ButtonUtil(String name, Controller c, String language, HistorySection histSec, TextSection textSec, DataSection userDefinedSection, Runner runner, TurtleViewManager tvm) {

}
