package slogo.model.command;

/**
 * Mutable class that wraps a double. Represents any value that can be passed to a SLogo command.
 * Required because Double is immutable.
 * Has no Dependencies.
 *
 * @author Shaan Gondalia
 */
public class Value {

  private double val;

  /**
   * Creates Value that defaults to 0
   */
  public Value() {
    val = 0;
  }

  /**
   * Creates Value with the corresponding double
   * @param d the value of the Value
   */
  public Value(double d) {
    val = d;
  }

  /**
   * Returns the value of the object
   * @return the value of the object
   */
  public double getVal() {
    return val;
  }

  /**
   * Sets the Value to the corresponding double
   * @param d the value of the Value
   */
  public void setVal(double d) {
    val = d;
  }

}
