package Models;

public class Vector extends Tuple {

    public Vector() {
        super(0, 0, 0, 0);
    }

    public Vector(double x, double y, double z) {
        super(x, y, z, 0);
    }

    public Vector add(Tuple b) {
        return new Vector(getX() + b.getX(), getY() + b.getY(), getZ() + b.getZ());

    }

    public Vector subtract(Tuple b) {
        return new Vector(getX() - b.getX(), getY() - b.getY(), getZ() - b.getZ());
    }

    public Vector negate() {
        return new Vector(getX() * -1, getY() * -1, getZ() * -1);
    }

    public Vector divide(double factor) {
        return multiply(1 / factor);
    }

    public Vector multiply(double factor) {
        return new Vector(getX() * factor, getY() * factor, getZ() * factor);
    }

    public double magnitude() {
        return Math.sqrt(getX() * getX() + getY() * getY() + getZ() * getZ());
    }

    public Vector normalize() {
        double mag = this.magnitude();

        return new Vector(getX() / mag, getY() / mag, getZ() / mag);
    }

    public double dot(Vector b) {
        return (getX() * b.getX()) + (getY() * b.getY()) + (getZ() * b.getZ());
    }

    public Vector cross(Vector b) {
        return new Vector(getY() * b.getZ() - getZ() * b.getY(), getZ() * b.getX() - getX() * b.getZ(),
                getX() * b.getY() - getY() * b.getX());
    }

    public Vector reflect(Vector b) {
        return subtract(b.multiply(2 * dot(b)));
    }

    public String toString() {
        return "Vector: [" + getX() + ", " + getY() + ", " + getZ() + "]";
    }

}