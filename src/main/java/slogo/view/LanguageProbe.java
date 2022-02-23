package slogo.view;

import java.awt.Dimension;
import javafx.stage.Stage;

/**
 * Used to probe what language the whole program will be in
 *
 * @author Andy S He
 */
public class LanguageProbe {
  private static final String TITLE = "Language Probe";
  private static final Dimension SIZE = new Dimension(400,400);

  public LanguageProbe(){
    Stage stage = new Stage();
  }

  @Override
  public String toString(){
    return "English";
  }

}
