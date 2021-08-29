package Models.Shapes;

import java.util.ArrayList;

import Models.Intersection;
import Models.Material;
import Models.Matrix;
import Models.Ray;
import Models.Tuples.Point;
import Models.Tuples.Vector;

public class Sphere extends Shape {

    public static Sphere glassSphere() {
        return (Sphere) new Sphere().setMaterial(new Material().setTransparency(1).setRefractiveIndex(1.5));
    }

    @Override
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

        return Intersection.sort(retVaues);
    }

    @Override
    public Vector normalAt(Point p) {
        Point objectPoint = getTransform().inverse().multiply(p);

        Vector objectNormal = objectPoint.subtract(new Point());

        Vector worldNormal = getTransform().inverse().transpose().multiply(objectNormal);

        worldNormal.setW(0);

        return worldNormal.normalize();
    }

    @Override
    public boolean equals(Shape s) {
        if (!(s instanceof Sphere))
            return false;

        if (getMaterial().equals(s.getMaterial()) && getTransform().equals(s.getTransform()))
            return true;

        return false;
    }

    @Override
    public Sphere setTransform(Matrix transform) {
        return (Sphere) super.setTransform(transform);
    }

    @Override
    public Sphere setMaterial(Material material) {
        return (Sphere) super.setMaterial(material);
    }
}
