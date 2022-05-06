package Models;

import java.util.ArrayList;

import Models.Shapes.Shape;
import Models.Tuples.Point;
import Models.Tuples.Vector;

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

    public ArrayList<Intersection> intersects(World w) { // redundant, used for debugging

        return w.intersects(this);
    }

    public Ray transform(Matrix matrix) {

        Point newOrigin = matrix.multiply(origin);
        Vector newdirection = matrix.multiply(direction);

        return new Ray(newOrigin, newdirection);
    }

    public String toString() {
        return "{ Origin: " + origin + ", Direction: " + direction + " }";
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
