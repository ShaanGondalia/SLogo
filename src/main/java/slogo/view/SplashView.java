package slogo.view;

import slogo.controller.Controller;

/**
 * Screen to display the first time program is running up. Here, can select language / css and
 * anything else needed before initialization
 *
 * @author Andy S. He
 */
public class SplashView implements Displayable {

  @Override
  public void createStage(String language, Controller c) {

  }

  public String styleSheetToUse(){
    return "";
  }

}
