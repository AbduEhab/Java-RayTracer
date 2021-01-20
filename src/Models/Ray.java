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

    public ArrayList<Intersection> intersects(Sphere s) {
        return s.intersects(this);
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
