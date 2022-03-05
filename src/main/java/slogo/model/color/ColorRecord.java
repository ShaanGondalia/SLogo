package slogo.model.color;

/**
 * Record class representing opaque color
 *
 * @author Jake Heller
 */
public record ColorRecord(int r, int g, int b) {

  private static final int COLOR_RANGE = 256;
  private static final int HEX_TRIPLE = 3;
  private static final String HEX_PREFIX = "0";
  private static final int EQUALS_THRESHHOLD = 5;

  @Override
  public String toString() {
    return "#" + toHex(r) + toHex(g) + toHex(b);
  }

  @Override
  // https://stackoverflow.com/questions/8180430/how-to-override-equals-method-in-java
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (obj.getClass() != this.getClass()) {
      return false;
    }

    final ColorRecord other = (ColorRecord) obj;
    return withinThreshhold(other.r(), this.r())
        && withinThreshhold(other.g(), this.g())
        && withinThreshhold(other.b(), this.b());
  }

  private boolean withinThreshhold(int a, int b) {
    return Math.abs(a - b) < EQUALS_THRESHHOLD;
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
