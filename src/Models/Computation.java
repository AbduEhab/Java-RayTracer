package Models;

import Models.Shapes.Shape;
import Models.Tuples.Point;
import Models.Tuples.Vector;

public class Computation {

    private double t;
    private Shape s;
    private Point p;
    private Vector eyeVector;
    private Vector normalVector;
    private boolean inside;
    private Point overPoint;
    private Vector reflectionVector;

    public Computation(double t, Shape s, Point p, Vector eyeVector, Vector normalVector, boolean inside,
            Point overPoint, Vector reflectionVector) {
        this.t = t;
        this.s = s;
        this.p = p;
        this.eyeVector = eyeVector;
        this.normalVector = normalVector;
        this.inside = inside;
        this.overPoint = overPoint;
        this.reflectionVector = reflectionVector;
    }

    public double getT() {
        return t;
    }

    public Shape getShape() {
        return s;
    }

    public Point getPoint() {
        return p;
    }

    public Vector getEyeVector() {
        return eyeVector;
    }

    public Vector getNormalVector() {
        return normalVector;
    }

    public Boolean isInside() {
        return inside;
    }

    public Point getOverPoint() {
        return overPoint;
    }

    public Vector getReflectionVector() {
        return reflectionVector;
    }

}
