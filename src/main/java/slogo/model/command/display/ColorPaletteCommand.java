package slogo.model.command.display;

import java.awt.Color;
import java.util.List;
import slogo.model.color.ColorPalette;
import slogo.model.command.AbstractCommand;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;

/**
 * Abstract class for all display related commands
 *
 * @author Jake Heller
 */
public abstract class ColorPaletteCommand extends AbstractCommand {

  private ColorPalette myPalette;
  private List<Value> myArgs;

  /**
   *
   * @param palette the color palette used by this workspace
   * @param args the arguments to the command
   * @param numArgs number of arguments this command takes
   * @throws MissingArgumentException
   */
  public ColorPaletteCommand(ColorPalette palette, List<Value> args, int numArgs)
      throws MissingArgumentException {
    super();
    myPalette = palette;
    myArgs = args;
    verifyArgs(args, numArgs);
  }

  protected ColorPalette getPalette() {
    return myPalette;
  }

  protected List<Value> getArgs() {
    return myArgs;
  }
}
