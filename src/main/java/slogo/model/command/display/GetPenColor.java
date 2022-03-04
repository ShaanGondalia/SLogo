package slogo.model.command.display;

import java.util.List;
import java.util.Set;
import slogo.model.color.ColorPalette;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

/**
 * @author Jake Heller
 */
public class GetPenColor extends ColorPaletteCommand{

  public static final int NUM_ARGS = 0;
  public GetPenColor(ColorPalette palette, List<Value> args) throws MissingArgumentException {
    super(palette, args, NUM_ARGS);
  }
  @Override
  public Value execute(Turtle turtle) throws MissingArgumentException {
    Set<Double> indices = getPalette().getIndices();
    double target = 0;
    for (double index : indices) {
      if (getPalette().getColor(index).equals(turtle.getStatus().penState().color())) {
        target = index;
        break;
      }
    }
    setReturnValue(target);
    return returnValue();
  }
}
