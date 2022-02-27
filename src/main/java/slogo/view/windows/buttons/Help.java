package slogo.view.windows.buttons;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import slogo.view.util.ButtonUtil;
import slogo.view.windows.HelpView;

public class Help implements IDEButton{

  @Override
  public void doAction(ButtonUtil info) {
    HelpView helpView = new HelpView();
  }

}
