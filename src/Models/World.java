package Models;

import java.util.ArrayList;

import Models.Lights.*;
import Models.Shapes.Shape;
import Models.Shapes.Sphere;
import Models.Tuples.Color;
import Models.Tuples.Point;
import Models.Tuples.Vector;

public class World {

    private ArrayList<Shape> shapes = new ArrayList<Shape>();
    private ArrayList<Light> lights = new ArrayList<Light>();

    public World() {
    }

    private World(Object o) {
        Sphere sphere = new Sphere();
        sphere.setMaterial(new Material(new Color(0.8, 1, 0.6), -1, 0.7, 0.2, -1, null, 0));
        shapes.add(sphere);
        Sphere sphere2 = new Sphere();
        sphere2.setTransform(Matrix.IDENTITY.scale(0.5, 0.5, 0.5));
        shapes.add(sphere2);
        lights.add(new PointLight(new Color(1, 1, 1), new Point(-10, 10, -10)));
    }

    public static World defaultWorld() {
        return new World(null);
    }

    public ArrayList<Intersection> intersects(Ray ray) {

        ArrayList<Intersection> res = new ArrayList<Intersection>();

        for (Shape shape : getShapes()) {
            ArrayList<Intersection> inters1 = shape.intersects(ray);
            res = Intersection.intersections(res, inters1);
        }

        return Intersection.sort(res);
    }

    public Color shadeHit(Computation c) {

        Color res = new Color(0, 0, 0);
        for (Light light : lights) {

            boolean inShadow = false;

            if (c.getShape() instanceof Sphere) {
                inShadow = isShadowed(c.getOverPoint().add(c.getShape().normalAt(c.getPoint())), light);
            } else {
                inShadow = isShadowed(c.getOverPoint(), light);
            }

            res = res.add(c.getShape().getMaterial().lighting(light, c.getShape(), c.getPoint(), c.getEyeVector(),
                    c.getNormalVector(), inShadow));

            res = res.add(reflectedColor(c));
        }

        return res;
    }

    public Color colorAt(Ray ray) {

        ArrayList<Intersection> intrsections = intersects(ray);

        Intersection hit = Intersection.hit(intrsections);

        if (hit == null)
            return new Color(0, 0, 0);

        Computation c = hit.prepareComputate(ray);

        return shadeHit(c);
    }

    public boolean isShadowed(Point p, Light light) {

        Vector v = light.getPosition().subtract(p);

        double distance = v.magnitude();
        Vector direction = v.normalize();

        Ray r = new Ray(p, direction);
        ArrayList<Intersection> intersection = intersects(r);

        Intersection hit = Intersection.hit(intersection);

        if (hit != null && hit.getT() < distance)
            return true;
        else
            return false;
    }

    static int recursionLevel = 0;

    public Color reflectedColor(Computation comp) {

        if (comp.getShape().getMaterial().getReflectiveness() == 0 | recursionLevel++ > 7) {
            recursionLevel = 0;
            return Color.BLACK;
        }

        Ray reflectedRay = new Ray(comp.getOverPoint(), comp.getReflectionVector());

        Color c = colorAt(reflectedRay);

        return c.multiply(comp.getShape().getMaterial().getReflectiveness());

    }

    public void addShape(Shape s) {
        shapes.add(s);
    }

    public void addShape(Shape[] s) {

        for (Shape shape : s) {
            shapes.add(shape);
        }

    }

    public void addLight(Light l) {
        lights.add(l);
    }

    public void addLight(Light[] l) {

        for (Light Light : lights) {
            lights.add(Light);
        }

    }

    public ArrayList<Shape> getShapes() {
        return shapes;
    }

    public void setShapes(ArrayList<Shape> shapes) {
        this.shapes = shapes;
    }

    public ArrayList<Light> getLights() {
        return lights;
    }

    public void setLights(ArrayList<Light> lights) {
        this.lights = lights;
    }

}
