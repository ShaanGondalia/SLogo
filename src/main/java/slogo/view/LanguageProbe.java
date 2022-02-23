package slogo.view;

import javafx.stage.Stage;

/**
 * Used to probe what language the whole program will be in
 *
 * @author Andy S He
 */
public class LanguageProbe {
  private static final String TITLE = "Language Probe";
  private static final int HEIGHT = 400;
  private static final int WIDTH = 400;

  public LanguageProbe(){
    Stage stage = new Stage();
  }

  public String languageToUse(){
    return "English";
  }
}
