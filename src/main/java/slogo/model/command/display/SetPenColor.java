package slogo.model.command.display;

import java.util.List;
import slogo.model.color.ColorPalette;
import slogo.model.color.ColorRecord;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;
import slogo.model.turtle.Turtle;

public class SetPenColor extends ColorPaletteCommand{

  public static int NUM_ARGS = 1;

  public SetPenColor(ColorPalette palette, List<Value> args) throws MissingArgumentException {
    super(palette, args, NUM_ARGS);
  }

  @Override
  public Value execute(Turtle turtle) throws MissingArgumentException {
    int index = getArgs().get(0).asInt();
    ColorRecord color = getPalette().getColor(index);
    turtle.setPenColor(color);
    setReturnValue(index);
    return returnValue();
  }
}
