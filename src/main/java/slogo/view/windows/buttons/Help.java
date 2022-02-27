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

  public static void main(String[] args) throws FileNotFoundException {
    File path = new File("src/main/resources/view/reference");

    File[] files = path.listFiles();
    for (int i = 0; i < files.length; i++){
      if (files[i].isFile()){ //this line weeds out other directories/folders
        System.out.println(files[i].getName());
        Scanner scanner = new Scanner(files[i]);
//        while (scanner.hasNextLine()){
//          System.out.println(scanner.nextLine());
//        }
      }
    }
  }
}
