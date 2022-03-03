package slogo.model.command.display;

import java.awt.Color;
import java.util.List;
import slogo.model.color.ColorPalette;
import slogo.model.command.AbstractCommand;
import slogo.model.command.Value;

/**
 * Abstract class for all display related commands
 *
 * @author Jake Heller
 */
public abstract class ColorPaletteCommand extends AbstractCommand {

  private ColorPalette myPalette;
  private List<Value> myArgs;

  public ColorPaletteCommand(ColorPalette palette, List<Value> args) {
  }

  protected ColorPalette getPalette() {
    return myPalette;
  }

  protected List<Value> getArgs() {
    return myArgs;
  }
}
