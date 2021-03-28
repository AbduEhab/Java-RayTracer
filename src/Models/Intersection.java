package Models;

import java.util.ArrayList;

public class Intersection {

    private double t;
    private Shape shape;

    public Intersection(double t, Shape shape) {
        this.t = t;
        this.shape = shape;
    }

    public static ArrayList<Intersection> intersections(Intersection inters1, Intersection inters2) {
        ArrayList<Intersection> retVaues = new ArrayList<Intersection>();

        retVaues.add(inters1);
        retVaues.add(inters2);
        return retVaues;
    }

    public static ArrayList<Intersection> intersections(ArrayList<Intersection> list1, ArrayList<Intersection> list2) {
        list1.addAll(list2);
        return list1;
    }

    public static Intersection hit(ArrayList<Intersection> intersections) {

        intersections = sort(intersections);

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

    public Computation prepareComputate(Ray ray) {

        double t2 = t;
        Shape s = shape;
        Point p = ray.position(t);
        Vector eyeVector = ray.getDirection().multiply(-1);
        Vector normalVector = s.normalAt(p);

        boolean inside = false;

        if (normalVector.dot(eyeVector) < 0) {
            inside = true;
            normalVector = normalVector.multiply(-1);
        }

        return new Computation(t2, s, p, eyeVector, normalVector, inside);

    }

    public double getT() {
        return t;
    }

    public Shape getShape() {
        return shape;
    }

}
