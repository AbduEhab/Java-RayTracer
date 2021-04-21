package Models;

import java.util.ArrayList;

public class Sphere extends Shape {

    public ArrayList<Intersection> intersects(Ray ray) {
        ray = ray.transform(getTransform().inverse());

        Vector sphereToRay = ray.getOrigin().subtract(new Point());

        double a = ray.getDirection().dot(ray.getDirection());
        double b = 2 * ray.getDirection().dot(sphereToRay);

        double c = sphereToRay.dot(sphereToRay) - 1;

        double discriminant = Math.pow(b, 2) - 4 * a * c;

        if (discriminant < 0)
            return null;

        ArrayList<Intersection> retVaues = new ArrayList<Intersection>();

        retVaues.add(new Intersection((-b - Math.sqrt(discriminant)) / (2 * a), this));
        retVaues.add(new Intersection((-b + Math.sqrt(discriminant)) / (2 * a), this));

        return retVaues;
    }

    public Vector normalAt(Point p) {
        Point objectPoint = getTransform().inverse().multiply(p);

        Vector objectNormal = objectPoint.subtract(new Point());

        Vector worldNormal = getTransform().inverse().transpose().multiply(objectNormal);

        worldNormal.setW(0);

        return worldNormal.normalize();
    }

    public boolean equals(Shape s) {
        if (!(s instanceof Sphere))
            return false;

        if (getMaterial().equals(s.getMaterial()) && getTransform().equals(s.getTransform()))
            return true;

        return false;
    }

    @Override
    public String toString() {
        return "Sphere: " + hashCode();
    }

}
