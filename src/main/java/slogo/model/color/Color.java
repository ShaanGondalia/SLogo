package slogo.model.color;

import slogo.model.command.Value;

public record Color(int r, int g, int b) {

  private static final int COLOR_RANGE = 256;
  private static final int HEX_TRIPLE = 3;
  private static final String HEX_PREFIX = "0";

  public Color(Value r, Value g, Value b) {
    this(r.asInt(), g.asInt(), b.asInt());
  }

  @Override
  public String toString() {
    return "#" + toHex(r) + toHex(g) + toHex(b);
  }

  private String toHex(int a) {
    a = a % COLOR_RANGE;
    String s = Integer.toHexString(a);
    if (s.length() == 1) {
      s = HEX_PREFIX + s;
    } else if (s.length() == HEX_TRIPLE) {
      s = s.substring(1);
    }
    return s;
  }
}
