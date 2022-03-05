package slogo.view.windows.buttons;

import static slogo.view.windows.sections.ButtonSection.RESOURCE_PREFIX;

import java.lang.reflect.Constructor;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import slogo.Errors;
import slogo.view.util.ButtonUtil;

/**
 * Factory to create IDEButtons - utilizing the design patterns shown in class
 *
 * @author Andy S. He
 * @see IDEButton
 */
public class IDEButtonFactory {

  public static final String FILE_PATH_DELIMITER = ".";

  /**
   * Creates a new Button using reflection - the main factory method
   * <p>
   * Note, each of the buttons are added in a resource file, for a name to display, adn then a class
   * in teh buttons package is created to supplement that addition
   *
   * @param info contains all the information each of the buttons need to function
   * @return a Button object that will be added to the ButtonSection soon
   * @see slogo.view.windows.sections.ButtonSection
   */
  public Button createButton(ButtonUtil info) {
    Button b = new Button();
    b.setId(info.name());
    ResourceBundle resources = ResourceBundle.getBundle(RESOURCE_PREFIX + info.language());
    b.setText(resources.getString(info.name()));
    b.setOnAction((e) -> {
      try {
        getButtonClass(info.name()).doAction(info);
      } catch (Exception ex) {
        Errors.showAndClose(ex.getMessage());
      }
    });
    return b;
  }

  // uses reflection to create a new IDEButton class
  private IDEButton getButtonClass(String name) throws Exception {
    try {
      Class<?> clazz = Class.forName(IDEButton.class.getPackageName() + FILE_PATH_DELIMITER + name);
      Constructor<?> ctor = clazz.getDeclaredConstructor();
      return (IDEButton) ctor.newInstance();
    } catch (Exception exception) {
      throw new Exception(exception.getMessage());
    }
  }
}
