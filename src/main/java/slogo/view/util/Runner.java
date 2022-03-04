package slogo.view.util;

import slogo.Errors;
import slogo.controller.Controller;
import slogo.view.windows.sections.HistorySection;
import slogo.view.windows.sections.DataSection;

public class Runner {

  private Controller myController;
  private DataSection myUserDefinedSection;
  private HistorySection myHistorySection;

  public void setParameters(Controller c, DataSection userDefinedSection,
      HistorySection historySection) {
    myController = c;
    myUserDefinedSection = userDefinedSection;
    myHistorySection = historySection;
  }

  public void runAndSave(String program) {
    if (runQuietly(program)) {
      myHistorySection.setNewHistory(program);
    }
  }

  public boolean runQuietly(String program) {
    try {
      myController.runText(program);
    } catch (Exception e) {
      Errors.showError(e.getMessage());
      return false;
    }
    myUserDefinedSection.update();
    return true;
  }
}
