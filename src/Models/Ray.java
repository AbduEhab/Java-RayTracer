package Models;

import java.util.ArrayList;

public class Ray {

    private Point origin;
    private Vector direction;

    public Ray(Point origin, Vector direction) {
        this.origin = origin;
        this.direction = direction;
    }

    public Point position(double t) {
        return origin.add(direction.multiply(t));
    }

    public ArrayList<Intersection> intersects(Shape s) {
        return s.intersects(this);
    }

    public ArrayList<Intersection> intersects(World w) { // possibly redundant, used for debugging

        return w.intersects(this);
    }

    public Ray transform(Matrix matrix) { // possible problem related to deep cloning

        Point newOrigin = matrix.multiply(origin);
        Vector newdirection = matrix.multiply(direction);

        return new Ray(newOrigin, newdirection);
    }

    public String toString() {
        return "Ray: { " + origin + ", " + direction + " }";
    }

    public Point getOrigin() {
        return origin;
    }

    public void setOrigin(Point origin) {
        this.origin = origin;
    }

    public Vector getDirection() {
        return direction;
    }

    public void setDirection(Vector direction) {
        this.direction = direction;
    }

}
