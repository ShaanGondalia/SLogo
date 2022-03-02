package slogo.view.util;

import slogo.controller.Controller;
import slogo.view.windows.sections.HistorySection;
import slogo.view.windows.sections.TextSection;
import slogo.view.windows.sections.VariablesAndCommandsSection;

public class Runner {

  private Controller myController;
  private VariablesAndCommandsSection myUserDefinedSection;
  private HistorySection myHistorySection;
  private TextSection myTextSection;

  public void setParameters(Controller c, VariablesAndCommandsSection userDefinedSection,
      HistorySection historySection, TextSection textSection){
    myController = c;
    myUserDefinedSection = userDefinedSection;
    myHistorySection = historySection;
    myTextSection = textSection;
  }

  public void runPublicly(String program) {
    runPrivately(program);
    myTextSection.clear();
  }

  public void runPrivately(String program) {
    myController.runText(program);
    myUserDefinedSection.updateList();
    myHistorySection.setNewHistory(program);
  }
}
