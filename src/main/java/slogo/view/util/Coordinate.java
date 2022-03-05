package slogo.view.util;

/**
 * Bundles together two integers representing a coordinate for convenience.
 * Used exclusively alongside the matrix class for front end positioning of the turtle image
 *
 * @author Zack Schrage
 */
public class Coordinate {

    private double x;
    private double y;

    /**
     * Bundles together two integers representing a coordinate for convenience.
     * @param x coordinate
     * @param y coordinate
     */
    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter method for the x coordinate of a coordinate
     * @return x coordinate
     */
    public double x() {
        return x;
    }

    /**
     * Getter method for the y coordinate of a coordinate
     * @return y coordinate
     */
    public double y() {
        return y;
    }

    /**
     * Setter method for a coordinate
     * @param x new x coordinate
     * @param y new y coordinate
     */
    public void setXY(double x, double y) {
        this.x = x;
        this.y = y;
    }

}
