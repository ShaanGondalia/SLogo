package slogo.model.command.display;

import java.util.List;
import slogo.model.color.ColorPalette;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * Adds color to Color palette
 *
 * @author Jake Heller
 */
public class SetPalette extends ColorPaletteCommand {

  private static final int NUM_ARGS = 4;
  private static final int PALETTE_ID_INDEX = 0;
  private static final int R_INDEX = 1;
  private static final int G_INDEX = 2;
  private static final int B_INDEX = 3;

  // (args.get(R_INDEX), args.get(G_INDEX), args.get(B_INDEX));

  /**
   *
   * @param palette palette used by workspace
   * @param args arguments to command
   * @throws MissingArgumentException
   */
  public SetPalette(ColorPalette palette, List<Value> args) throws MissingArgumentException {
    super(palette, args, NUM_ARGS);
  }

  @Override
  public Value execute(Turtle turtle) throws MissingArgumentException {
    List<Value> args = getArgs();
    int r = args.get(R_INDEX).asInt();
    int g = args.get(G_INDEX).asInt();
    int b = args.get(B_INDEX).asInt();
    double index = args.get(0).getVal();

    getPalette().addColor(index, r, g, b);
    setReturnValue(index);
    return returnValue();
  }
}
