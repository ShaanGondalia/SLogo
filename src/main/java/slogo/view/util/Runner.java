package slogo.view.util;

import slogo.controller.Controller;
import slogo.view.windows.sections.HistorySection;
import slogo.view.windows.sections.VariablesAndCommandsSection;

public class Runner {

  private Controller myController;
  private VariablesAndCommandsSection myUserDefinedSection;
  private HistorySection myHistorySection;

  public void setParameters(Controller c, VariablesAndCommandsSection userDefinedSection,
      HistorySection historySection){
    myController = c;
    myUserDefinedSection = userDefinedSection;
    myHistorySection = historySection;
  }

  public void runAndSave(String program) {
    myController.runText(program);
    myUserDefinedSection.update();
    myHistorySection.setNewHistory(program);
  }
}
