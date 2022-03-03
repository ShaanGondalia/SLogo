package slogo.model.color;

import slogo.model.command.Value;

public record ColorRecord(int r, int g, int b) {

  @Override
  public String toString() {
    return "#" + toHex(r) + toHex(g) + toHex(b);
  }

  private String toHex(int a) {
    a = a % 256;
    String s = Integer.toHexString(a);
    if (s.length() == 1) {
      s = "0" + s;
    } else if (s.length() == 3) {
      s = s.substring(1);
    }
    return s;
  }
}
