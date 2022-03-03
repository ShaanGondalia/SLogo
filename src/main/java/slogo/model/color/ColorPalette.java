package slogo.model.color;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;

public class ColorPalette {

  private static final int NUM_ARGS = 4;
  private static final int PALETTE_ID_INDEX = 0;
  private static final int R_INDEX = 1;
  private static final int G_INDEX = 2;
  private static final int B_INDEX = 3;

  Map<Integer, Color> myColors;

  public ColorPalette() {
    myColors = new HashMap<>();
  }

  public void addColor(List<Value> args) throws MissingArgumentException {
    if (args.size() != NUM_ARGS) {
      throw new MissingArgumentException("Message");
    } else {
      Color c = new Color(args.get(R_INDEX), args.get(G_INDEX), args.get(B_INDEX));
      myColors.put(args.get(PALETTE_ID_INDEX).asInt(), c);
    }
  }

  public Color getColor(int key) {
    return myColors.get(key);
  }


}
