package slogo.view.windows.buttons;

import java.lang.reflect.Constructor;
import javafx.scene.control.Button;
import slogo.Errors;
import slogo.view.util.ButtonUtil;

public class IDEButtonFactory {

  public Button createButton(ButtonUtil info) {
    Button b = new Button();
    b.setId(info.name());
    b.setText(info.resourceBundle().getString(info.name()));
    b.setOnAction((e) -> {
      try {
        getButtonClass(info.name()).doAction(info);
      } catch (Exception ex) {
        Errors.showAndClose(ex.getMessage());
      }
    });
    return b;
  }

  private IDEButton getButtonClass(String name) throws Exception {
    try {
      Class<?> clazz = Class.forName(IDEButton.class.getPackageName() + name);
      Constructor<?> ctor = clazz.getDeclaredConstructor();
      return (IDEButton) ctor.newInstance();
    } catch (Exception exception) {
      throw new Exception(exception.getMessage());
    }
  }
}
