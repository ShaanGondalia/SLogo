package slogo.model.command;

/**
 * Mutable class that wraps a double. Represents any value that can be passed to a SLogo command.
 * Required because Value is immutable. Has no Dependencies.
 *
 * @author Shaan Gondalia
 */
public class Value implements Comparable<Value> {

  private static final double TOLERANCE = 0.0001;

  private double val;

  /**
   * Creates Value that defaults to 0
   */
  public Value() {
    val = 0;
  }

  /**
   * Creates Value with the corresponding double
   *
   * @param d the value of the Value
   */
  public Value(double d) {
    val = d;
  }

  /**
   * Returns the value of the object
   *
   * @return the value of the object
   */
  public double getVal() {
    return val;
  }

  /**
   * Sets the Value to the corresponding double
   *
   * @param d the value of the Value
   */
  public void setVal(double d) {
    val = d;
  }

  /**
   * @return value rounded to nearest int
   */
  public int asInt() {
    return (int) Math.round(val);
  }

  /**
   * Return whether the value of this object is equal to the value of the parameter
   * @param a the value to compare to
   * @return whether the value of this object is equal to the value of the parameter
   */
  public boolean equals(Value a) {
    return (Math.abs(a.getVal() - val) < TOLERANCE);
  }

  /**
   * @param o Value to compare this to
   * @return 0 if Values are equal, -1 if o is larger 1 if o is smaller
   */
  @Override
  public int compareTo(Value o) {
    if (this.equals(o)) {
      return 0;
    } else if (this.val < o.getVal()) {
      return -1;
    } else {
      return 1;
    }
  }
}
