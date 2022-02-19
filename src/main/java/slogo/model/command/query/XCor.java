package slogo.model.command.query;

import java.util.List;
import slogo.model.Turtle;
import slogo.model.exception.MissingArgumentException;

/**
 * Class that represents a XCOR query. Depends on TurtleQuery and Turtle.
 *
 * @author Shaan Gondalia
 */
public class XCor extends TurtleQuery{

  private static final int NUM_ARGS = 0;

  /**
   * Creates an XCOR command attached to the given Turtle.
   *
   * @param turtle the Turtle that will be queried
   */
  public XCor(Turtle turtle, List<Double> args) throws MissingArgumentException {
    super(turtle, args, NUM_ARGS);
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
