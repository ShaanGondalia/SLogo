package slogo.view;

import slogo.controller.Controller;

/**
 * Common shared interface to display a new stage
 *
 * @author Andy S. He
 */
public interface Displayable {

  void createStage(String language, Controller c);

}
