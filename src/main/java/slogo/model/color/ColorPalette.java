package slogo.model.color;

import java.awt.Color;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import slogo.model.command.Value;
import slogo.model.exception.MissingArgumentException;

/**
 * Color Palette class
 *
 * This class contains a map of keys to ColorRecord objects. It is used by the
 * program to refer to colors through indices
 *
 * @author Jake Heller
 */
public class ColorPalette {

  Map<Double, ColorRecord> myColors;
  double myLargestIndex;

  private static final int NUM_ARGS = 4;
  private static final int PALETTE_ID_INDEX = 0;
  private static final int R_INDEX = 1;
  private static final int G_INDEX = 2;
  private static final int B_INDEX = 3;

  /**
   * initializes ColorPalette with empty map
   */
  public ColorPalette() {
    myColors = new HashMap<>();
    myLargestIndex = 0;
  }

  /**
   *
   * @param index index to be associated with this color
   * @param r ranges from 0 to 255
   * @param g ranges from 0 to 255
   * @param b ranges from 0 to 255
   */
  public void addColor(double index, int r, int g, int b) {
    ColorRecord color = new ColorRecord(r, g, b);
    myColors.put(index, color);
    if (index > myLargestIndex) {
      myLargestIndex = index;
    }
  }

  /**
   *
   * @return the set of all indices (keys) in the palette
   */
  public Set<Double> getIndices() {
    return myColors.keySet();
  }

  /**
   * Adds color to palette with index of 1 + the current largest index
   * @param r ranges from 0 to 255
   * @param g ranges from 0 to 255
   * @param b ranges from 0 to 255
   */
  public void addColor(int r, int g, int b) {
    ColorRecord color = new ColorRecord(r, g, b);
    myColors.put(myLargestIndex++, color);
  }

  /**
   *
   * @param key index of color to be retrieved
   * @return ColorRecord associated with given key
   */
  public ColorRecord getColor(double key) {
    ColorRecord color = myColors.getOrDefault(key, new ColorRecord(0, 0, 0));
    return color;
  }

  /**
   * Gets map containing the color palette's data for display usage
   *
   * @return map from color ID to the hex representation of the color
   */
  public Map<String, String> getStringMap() {
    Map<String, String> map = new LinkedHashMap<>();
    for (double key: myColors.keySet()) {
      map.put(Double.toString(key), myColors.get(key).toString());
    }
    return map;
  }


}
