package slogo.model.color;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;

public class ColorPalette {

  Map<Double, ColorRecord> myColors;
  double myLargestIndex;

  private static final int NUM_ARGS = 4;
  private static final int PALETTE_ID_INDEX = 0;
  private static final int R_INDEX = 1;
  private static final int G_INDEX = 2;
  private static final int B_INDEX = 3;

  public ColorPalette() {
    myColors = new HashMap<>();
    myLargestIndex = 0;
  }

  public void addColor(double index, int r, int g, int b) {
    ColorRecord color = new ColorRecord(r, g, b);
    myColors.put(index, color);
    if (index > myLargestIndex) {
      myLargestIndex = index;
    }
  }

  public Set<Double> getIndices() {
    return myColors.keySet();
  }

  public void addColor(int r, int g, int b) {
    ColorRecord color = new ColorRecord(r, g, b);
    myColors.put(myLargestIndex++, color);
  }

  public ColorRecord getColor(double key) {
    return myColors.get(key);
  }


}
