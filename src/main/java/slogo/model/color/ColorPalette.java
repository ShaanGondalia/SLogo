package slogo.model.color;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;

public class ColorPalette {

  Map<Double, ColorRecord> myColors;
  double myLargestIndex;

  public ColorPalette() {
    myColors = new HashMap<>();
    myLargestIndex = 0;
  }

  public void addColor(double index, int r, int g, int b) throws MissingArgumentException {
    ColorRecord color = new ColorRecord(r, g, b);
    myColors.put(index, color);
    if (index > myLargestIndex) {
      myLargestIndex = index;
    }
  }

  public void addColor(int r, int g, int b) {
    ColorRecord color = new ColorRecord(r, g, b);
    myColors.put(myLargestIndex++, color);
  }

  public ColorRecord getColor(int key) {
    return myColors.get(key);
  }


}
