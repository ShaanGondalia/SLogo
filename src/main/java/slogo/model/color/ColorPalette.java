package slogo.model.color;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;

public class ColorPalette {
  Map<Integer, Color> myColors;

  public ColorPalette() {
    myColors = new HashMap<>();
  }

  public void addColor(List<Value> args) throws MissingArgumentException {
    if (args.size() != 4) {
      throw new MissingArgumentException("Message");
    }
    else {
      Color c = new Color(args.get(1), args.get(2), args.get(3));
      myColors.put(args.get(0).asInt(), c);
    }
  }

  public Color getColor(int key) {
    return myColors.get(key);
  }


}
