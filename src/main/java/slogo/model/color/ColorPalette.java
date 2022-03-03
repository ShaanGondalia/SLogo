package slogo.model.color;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;

public class ColorPalette {

  Map<Double, ColorRecord> myColors;

  public ColorPalette() {
    myColors = new HashMap<>();
  }

  public void addColor(List<Value> args) throws MissingArgumentException {
    if (args.size() != 4) {
      throw new MissingArgumentException("Message");
    } else {
      ColorRecord c = new ColorRecord(args.get(1).asInt(), args.get(2).asInt(), args.get(3).asInt());
      myColors.put(args.get(0).getVal(), c);
    }
  }

  public ColorRecord getColor(int key) {
    return myColors.get(key);
  }


}
