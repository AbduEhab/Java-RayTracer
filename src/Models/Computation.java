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
    private double n1;
    private double n2;
    private Point underPoint;

    public Computation(double t, Shape s, Point p, Vector eyeVector, Vector normalVector, boolean inside,
            Point overPoint, Vector reflectionVector, double n1, double n2, Point underPoint) {
        this.t = t;
        this.s = s;
        this.p = p;
        this.eyeVector = eyeVector;
        this.normalVector = normalVector;
        this.inside = inside;
        this.overPoint = overPoint;
        this.reflectionVector = reflectionVector;
        this.n1 = n1;
        this.n2 = n2;
        this.underPoint = underPoint;
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

    public double getN1() {
        return n1;
    }

    public double getN2() {
        return n2;
    }

    public Point getUnderPoint() {
        return underPoint;
    }

}