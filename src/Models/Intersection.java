package Models;

import java.util.ArrayList;

public class Intersection {

    private double t;
    private Object object;

    public Intersection(double t, Object object) {
        this.t = t;
        this.object = object;
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

        Intersection[] arr = new Intersection[intersections.size()];

        for (int i = 0; i < intersections.size(); i++) {
            arr[i] = intersections.get(i);
        }

        for (int i = 0; i < intersections.size(); i++) {
            Intersection temp;
            for (int j = 1; j < arr.length - 1; j++) {
                if (arr[j - 1].getT() > arr[j].getT()) {
                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }

            }
        }
        for (Intersection intersection : arr) {
            if (intersection.getT() > 0)
                return intersection;
        }

        return null;
    }

    public double getT() {
        return t;
    }

    public Object getObject() {
        return object;
    }

}
