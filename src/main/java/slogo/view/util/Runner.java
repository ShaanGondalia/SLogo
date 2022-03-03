package slogo.view.util;

import slogo.Errors;
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
    try {
      myController.runText(program);
    }
    catch (Exception e){
      Errors.showError(e.getMessage());
    }
    myUserDefinedSection.update();
    myHistorySection.setNewHistory(program);
  }
}
