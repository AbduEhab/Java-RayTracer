package Models;

import java.util.ArrayList;

public class Sphere {

    private int radius;
    private Point center = new Point();

    public Sphere() {
        radius = 1;
    }

    public Sphere(int radius) {
        this.radius = radius;
    }

    public ArrayList<Intersection> intersects(Ray ray) { // fix
        Vector sphereToRay = ray.getOrigin().subtract(center);

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

    public Point getCenter() {
        return center;
    }

}
