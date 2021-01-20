package Models;

public abstract class Tuple {
    private double[] components;

    public Tuple(double x, double y, double z, double w) {
        components = new double[] { x, y, z, w };
    }

    public boolean equals(Tuple tupleToCompare) {
        return (Math.abs(components[0] - tupleToCompare.getX()) <= 0.00001)
                && (Math.abs(components[1] - tupleToCompare.getY()) <= 0.00001)
                && (Math.abs(components[2] - tupleToCompare.getZ()) <= 0.00001)
                && (Math.abs(components[3] - tupleToCompare.getW()) <= 0.00001);
    }

    public abstract Tuple add(Tuple b);

    public abstract Tuple subtract(Tuple b);

    public abstract String toString();

    public double getX() {
        return components[0];
    }

    public double getY() {
        return components[1];
    }

    public double getZ() {
        return components[2];
    }

    public double getW() {
        return components[3];
    }

    public Tuple setX(double newValue) {
        components[0] = newValue;
        return this;
    }

    public Tuple setY(double newValue) {
        components[1] = newValue;
        return this;
    }

    public Tuple setZ(double newValue) {
        components[2] = newValue;
        return this;
    }

    public Tuple setW(double newValue) {
        components[3] = newValue;
        return this;
    }
}
