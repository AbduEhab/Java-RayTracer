package Models;

import java.util.ArrayList;
import java.util.Stack;

import Models.Shapes.Shape;
import Models.Tuples.Point;
import Models.Tuples.Vector;

public class Intersection {

    private double t;
    private Shape shape;

    public Intersection(double t, Shape shape) {
        this.t = t;
        this.shape = shape;
    }

    public static ArrayList<Intersection> intersections(Intersection inters1, Intersection inters2) {

        ArrayList<Intersection> retVaues = new ArrayList<Intersection>();

        if (inters1 != null)
            retVaues.add(inters1);

        if (inters2 != null)
            retVaues.add(inters2);

        return sort(retVaues);
    }

    public static ArrayList<Intersection> intersections(ArrayList<Intersection> list1, ArrayList<Intersection> list2) {

        if (list2 != null)
            list1.addAll(list2);

        return list1;
    }

    public static Intersection hit(ArrayList<Intersection> intersections) {

        for (Intersection intersection : intersections) {
            if (intersection.getT() > 0)
                return intersection;
        }

        return null;
    }

    public static ArrayList<Intersection> sort(ArrayList<Intersection> intersections) {

        if (Intersection.isSorted(intersections))
            return intersections;

        for (int i = 0; i < intersections.size(); i++) {
            Intersection temp;
            for (int j = 1; j < intersections.size(); j++) {
                if (intersections.get(j - 1).getT() > intersections.get(j).getT()) {
                    temp = intersections.get(j - 1);
                    intersections.set(j - 1, intersections.get(j));
                    intersections.set(j, temp);
                }

            }
        }

        return intersections;
    }

    private static boolean isSorted(ArrayList<Intersection> intersections) {
        for (int i = 1; i < intersections.size(); i++) {
            if (intersections.get(i - 1).getT() > intersections.get(i).getT())
                return false;
        }
        return true;
    }

    public Computation prepareComputate(Ray ray, ArrayList<Intersection> xs) {

        double t2 = t;
        Shape s = shape;
        Point p = ray.position(t);
        Vector eyeVector = ray.getDirection().multiply(-1);
        Vector normalVector = s.normalAt(p);
        Vector reflectionVector = ray.getDirection().reflect(normalVector);

        boolean inside = false;

        if (normalVector.dot(eyeVector) < 0) {
            inside = true;
            normalVector = normalVector.multiply(-1);
        }

        Point overPoint = p.add(normalVector.multiply(0.00001));
        Point underPoint = p.add(normalVector.multiply(-0.00001));

        double n1 = 0;
        double n2 = 0;

        if (xs != null) {

            Stack<Intersection> shapesStack = new Stack<Intersection>();

            for (Intersection intersection : xs) {

                if (intersection.equals(this))
                    if (shapesStack.isEmpty())
                        n1 = 1.0;
                    else
                        n1 = shapesStack.peek().getShape().getMaterial().getRefractiveIndex();

                if (shapesStack.contains(intersection))
                    shapesStack.remove(intersection);
                else
                    shapesStack.push(intersection);

                if (intersection.equals(this)) {
                    if (shapesStack.isEmpty())
                        n2 = 1.0;
                    else
                        n2 = shapesStack.peek().getShape().getMaterial().getRefractiveIndex();
                    break;
                }

            }
        }

        return new Computation(t2, s, p, eyeVector, normalVector, inside, overPoint, reflectionVector, n1, n2,
                underPoint);

    }

    public boolean equals(Intersection b) {
        return t == b.getT() && shape.equals(b.getShape());
    }

    public double getT() {
        return t;
    }

    public Shape getShape() {
        return shape;
    }

}
