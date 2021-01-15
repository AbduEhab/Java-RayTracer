package Models;

public class Point extends Tuple {

    public Point() {
        super();
    }

    public Point(double x, double y, double z) {
        super(x, y, z, 1);
    }

    public Point add(Tuple b) {
        return new Point(getX() + b.getX(), getY() + b.getY(), getZ() + b.getZ());
    }

    public Vector subtract(Tuple b) {
        return new Vector(getX() - b.getX(), getY() - b.getY(), getZ() - b.getZ());
    }

}
