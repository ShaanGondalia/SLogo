package slogo.view;

public class Matrix {

    private double[] matrix = new double[6];

    public Matrix() {
        matrix[0] = 1;   matrix[1] = 0;   matrix[2] = 0;
        matrix[3] = 0;   matrix[4] = 1;   matrix[5] = 0;
    }

    public Matrix(double a, double b, double c, double d, double e, double f) {
        matrix[0] = a;   matrix[1] = b;   matrix[2] = c;
        matrix[3] = d;   matrix[4] = e;   matrix[5] = f;
    }

    public Matrix translate(float tx, float ty) {
        return new Matrix(1, 0, tx, 0, 1, ty);
    }

    public Matrix concat(Matrix m1, Matrix m2) {
        double a = m1.getMat()[0]*m2.getMat()[0] + m1.getMat()[1]*m2.getMat()[3];
        double b = m1.getMat()[0]*m2.getMat()[1] + m1.getMat()[1]*m2.getMat()[4];
        double c = m1.getMat()[0]*m2.getMat()[2] + m1.getMat()[1]*m2.getMat()[5] + m1.getMat()[2];
        double d = m1.getMat()[3]*m2.getMat()[0] + m1.getMat()[4]*m2.getMat()[3];
        double e = m1.getMat()[3]*m2.getMat()[1] + m1.getMat()[4]*m2.getMat()[4];
        double f = m1.getMat()[3]*m2.getMat()[2] + m1.getMat()[4]*m2.getMat()[5]+ m1.getMat()[5];
        return new Matrix(a, b, c, d, e, f);
    }

    public boolean invert() {
        double det = this.getMat()[0]*this.getMat()[4] - this.getMat()[1]*this.getMat()[3];
        if (det == 0) return false;
        double a = this.getMat()[0];
        double b = this.getMat()[1];
        double c = this.getMat()[2];
        double d =this.getMat()[3];
        double e = this.getMat()[4];
        double f = this.getMat()[5];
        this.getMat()[0] = e / det;
        this.getMat()[1] = -b / det;
        this.getMat()[2] = (b*f - e*c) / det;
        this.getMat()[3] = -d / det;
        this.getMat()[4] = a / det;
        this.getMat()[5] = -(a*f - c*d) / det;
        return true;
    }

    public Coordinate mapPoint(Coordinate c) {
        return new Coordinate(this.getMat()[0] * c.x() + this.getMat()[1] * c.y() + this.getMat()[2], this.getMat()[3] * c.x() + this.getMat()[4] * c.y() + this.getMat()[5]);
    }

    public double[] getMat() {
        return matrix;
    }

    public static void main(String[] args) {
        Matrix m = new Matrix(1, 0, 200, 0, -1, 200);
        Coordinate c = new Coordinate(100, 100);
        Coordinate trans = m.mapPoint(c);
        System.out.println(trans.x() + " " + trans.y());
    }

}
