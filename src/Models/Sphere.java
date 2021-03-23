package Models;

import java.util.ArrayList;

public class Sphere extends Shape {

    private Point center = new Point();
    private Matrix transform = Matrix.IDENTITY;

    public Sphere() {
    }

    public Sphere(int radius) {
    }

    public ArrayList<Intersection> intersects(Ray ray) {
        ray = ray.transform(transform.inverse());

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

    public Vector normalAt(Point p) {
        Point objectPoint = transform.inverse().multiply(p);

        Vector objectNormal = objectPoint.subtract(center);

        Vector worldNormal = transform.inverse().transpose().multiply(objectNormal);

        worldNormal.setW(0);

        return worldNormal.normalize();
    }

    public Point getCenter() {
        return center;
    }

    public Matrix getTransform() {
        return transform;
    }

    public void setTransform(Matrix transform) {
        this.transform = transform;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

}
