package Models;

public abstract class Tuple {
    private double[] matrix;

    public Tuple() {
        matrix = new double[] { 0, 0, 0, 0 };
    }

    public Tuple(double x, double y, double z, double w) {
        matrix = new double[] { x, y, z, w };
    }

    public boolean equals(Tuple tupleToCompare) {
        return (Math.abs(matrix[0] - tupleToCompare.getX()) < 0.00001)
                && (Math.abs(matrix[1] - tupleToCompare.getY()) < 0.00001)
                && (Math.abs(matrix[2] - tupleToCompare.getZ()) < 0.00001)
                && (Math.abs(matrix[3] - tupleToCompare.getW()) < 0.00001);
    }

    public abstract Tuple add(Tuple b);

    public abstract Tuple subtract(Tuple b);

    public double getX() {
        return matrix[0];
    }

    public double getY() {
        return matrix[1];
    }

    public double getZ() {
        return matrix[2];
    }

    public double getW() {
        return matrix[3];
    }

    public Tuple setX(double newValue) {
        matrix[0] = newValue;
        return this;
    }

    public Tuple setY(double newValue) {
        matrix[1] = newValue;
        return this;
    }

    public Tuple setZ(double newValue) {
        matrix[2] = newValue;
        return this;
    }

    public Tuple setW(double newValue) {
        matrix[3] = newValue;
        return this;
    }
}
