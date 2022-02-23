package slogo.view;

import slogo.controller.Controller;

/**
 * Common shared interface to display a new stage
 *
 * @author Andy S. He
 */
public abstract class Displayable {

  public abstract void createStage(String language, Controller c);

}
