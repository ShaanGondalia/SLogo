package slogo.view.windows.sections;

import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

public class TextSection implements IDESection {

  private static final String TEXT_SECTION_ID = "textArea";

  private TextArea myTextArea;

  public TextSection() {
    myTextArea = new TextArea();
    myTextArea.setId(TEXT_SECTION_ID);
  }

  @Override
  public Region getSection() {
    return myTextArea;
  }

  /**
   * Sends the data from the TextBox over to the Parser
   *
   * @return raw text
   */
  public String getRawCommandText() {
    return myTextArea.getText();
  }

  public void clear(){
    myTextArea.clear();
  }
}
