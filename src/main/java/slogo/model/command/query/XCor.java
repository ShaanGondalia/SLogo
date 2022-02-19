package slogo.model.command.query;

import slogo.model.Turtle;

/**
 * Class that represents a XCOR query. Depends on TurtleQuery and Turtle.
 *
 * @author Shaan Gondalia
 */
public class XCor extends TurtleQuery{

  /**
   * Creates an XCOR command attached to the given Turtle.
   *
   * @param turtle the Turtle that will be queried
   */
  public XCor(Turtle turtle){
    super(turtle);
  }

  /**
   * Returns the x coordinate of the queried
   * @return the x coordinate of the queried turtle
   */
  @Override
  public Double returnValue() {
    return getTurtle().getPose().x();
  }
}
