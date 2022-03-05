package slogo.view.util;

/**
 * Implements a matrix for coordinate transformations
 *
 * @author Zack Schrage
 */
public class Matrix {

    private double[] matrix = new double[6];

    /**
     * Implements a matrix for coordinate transformations
     * @param a row 1 col 1 coordinate of the matrix
     * @param b row 1 col 2 coordinate of the matrix
     * @param c row 1 col 3 coordinate of the matrix
     * @param d row 2 col 1 coordinate of the matrix
     * @param e row 2 col 2 coordinate of the matrix
     * @param f row 2 col 3 coordinate of the matrix
     */
    public Matrix(double a, double b, double c, double d, double e, double f) {
        matrix[0] = a;   matrix[1] = b;   matrix[2] = c;
        matrix[3] = d;   matrix[4] = e;   matrix[5] = f;
    }

    /**
     * Applies a transformation of a coordinate to the implicitly called transformation matrix
     * @param c coordinate to be mapped
     * @return the transformed coordinate
     */
    public Coordinate mapPoint(Coordinate c) {
        return new Coordinate(this.getMat()[0] * c.x() + this.getMat()[1] * c.y() + this.getMat()[2], this.getMat()[3] * c.x() + this.getMat()[4] * c.y() + this.getMat()[5]);
    }

    /**
     * Getter method for a matrix
     * @return a matrix object
     */
    public double[] getMat() {
        return matrix;
    }

}
