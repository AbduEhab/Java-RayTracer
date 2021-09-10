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

    public double schlick() {

        double cosine = eyeVector.dot(normalVector);

        if (n1 > n2) {
            double n = n1 / n2;
            double sin2_t = n * n * (1 - cosine * cosine);

            if (sin2_t > 1)
                return 1;

            double cos_t = Math.sqrt(1 - sin2_t);

            cosine = cos_t;
        }

        double r0 = ((n1 - n2) / (n1 + n2));
        r0 = r0 * r0;

        return r0 + (1 - r0) * Math.pow(1 - cosine, 5);
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