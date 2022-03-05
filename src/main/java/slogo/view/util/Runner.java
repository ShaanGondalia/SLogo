package slogo.view.util;

import slogo.Errors;
import slogo.controller.Controller;
import slogo.view.windows.sections.HistorySection;
import slogo.view.windows.sections.DataSection;

/**
 * Class that is used in the front-end to run code in the compiler Made a new class in order to
 * standardize the interaction with the controller throughout the front-end
 *
 * @author Andy S. He
 * @see slogo.view.windows.buttons.Run
 * @see HistorySection
 * @see slogo.view.windows.sections.VariablesSection
 */
public class Runner {

  private Controller myController;
  private DataSection myUserDefinedSection;
  private HistorySection myHistorySection;

  /**
   * Used as a constructor - since Runner must be passed originally to each of the sections
   *
   * @param c                  - controller used to interact with back-end
   * @param userDefinedSection - used to save any new variables or commands
   * @param historySection     - used to save to the history
   */
  public void setParameters(Controller c, DataSection userDefinedSection,
      HistorySection historySection) {
    myController = c;
    myUserDefinedSection = userDefinedSection;
    myHistorySection = historySection;
  }

  /**
   * Used to run a program and save it to the history
   *
   * @param program text to compile and run
   */
  public void runAndSave(String program) {
    if (runQuietly(program)) {
      myHistorySection.setNewHistory(program);
    }
  }

  /**
   * Used to run code in the background - say changing a variable's value
   *
   * @param program text to compile and run
   * @return if the program was compiled successfully
   */
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
