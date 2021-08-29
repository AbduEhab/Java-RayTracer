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
    private int recursionCalls = 7;

    public final static World DEFAULT_WORLD = new World(null);

    public World() {
    }

    private World(Object o) {
        Sphere sphere = new Sphere();
        sphere.setMaterial(new Material(new Color(0.8, 1, 0.6), -1, 0.7, 0.2, -1, null, 0, -1, -1));
        shapes.add(sphere);
        Sphere sphere2 = new Sphere();
        sphere2.setTransform(Matrix.IDENTITY.scale(0.5, 0.5, 0.5));
        shapes.add(sphere2);
        lights.add(new PointLight(new Color(1, 1, 1), new Point(-10, 10, -10)));
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
        return shadeHitHelper(c, 0);
    }

    public Color shadeHitHelper(Computation c, int recursionLevel) {
        Color res = new Color(0, 0, 0);
        for (Light light : lights) {

            boolean inShadow = false;

            inShadow = isShadowed(c.getOverPoint(), light);

            res = res.add(c.getShape().getMaterial().lighting(light, c.getShape(), c.getPoint(), c.getEyeVector(),
                    c.getNormalVector(), inShadow));

            res = res.add(reflectedColorHelper(c, ++recursionLevel));

            res = res.add(refractedColorHelper(c, ++recursionLevel));
        }

        return res;
    }

    public Color colorAt(Ray ray) {

        ArrayList<Intersection> intrsections = intersects(ray);

        Intersection hit = Intersection.hit(intrsections);

        if (hit == null)
            return Color.BLACK;

        Computation c = hit.prepareComputate(ray, intrsections);

        return shadeHitHelper(c, 0);
    }

    public Color colorAt(Ray ray, int recursionLevel) {

        ArrayList<Intersection> intrsections = intersects(ray);

        Intersection hit = Intersection.hit(intrsections);

        if (hit == null)
            return Color.BLACK;

        Computation c = hit.prepareComputate(ray, intrsections);

        return shadeHitHelper(c, recursionLevel);
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

    public Color reflectedColor(Computation comp) {

        return reflectedColorHelper(comp, 0);

    }

    public Color reflectedColorHelper(Computation comp, int recursionLevel) {

        if (comp.getShape().getMaterial().getReflectiveness() == 0 | recursionLevel > recursionCalls)
            return Color.BLACK;

        Ray reflectedRay = new Ray(comp.getOverPoint(), comp.getReflectionVector());

        Color c = colorAt(reflectedRay, recursionLevel);

        return c.multiply(comp.getShape().getMaterial().getReflectiveness());
    }

    public Color refractedColor(Computation comp) {
        return refractedColorHelper(comp, 0);
    }

    public Color refractedColorHelper(Computation comp, int recursionLevel) {

        if (comp.getShape().getMaterial().getRefractiveIndex() == 0 | recursionLevel > recursionCalls)
            return Color.BLACK;

        double ratio = comp.getN1() / comp.getN2();

        double cos_i = comp.getEyeVector().dot(comp.getNormalVector());

        double sin2_t = ratio * ratio * (1 - (cos_i * cos_i));

        if (sin2_t > 1)
            return Color.BLACK;

        double cos_t = Math.sqrt(1 - sin2_t);

        Vector direction = comp.getNormalVector().multiply(ratio * cos_i - cos_t)
                .subtract(comp.getEyeVector().multiply(ratio));

        Ray refractedRay = new Ray(comp.getUnderPoint(), direction);

        Color c = colorAt(refractedRay, recursionLevel).multiply(comp.getShape().getMaterial().getTransparency());

        return c;
    }

    public boolean setRecursionCalls(int r) {
        if (r >= 0)
            recursionCalls = r;
        return true;
    }

    public int getRecursionCalls() {
        return recursionCalls;
    }

    public World addShape(Shape s) {
        shapes.add(s);
        return this;
    }

    public World addShape(Shape[] s) {

        for (Shape shape : s) {
            shapes.add(shape);
        }

        return this;
    }

    public World addLight(Light l) {
        lights.add(l);
        return this;
    }

    public World addLight(Light[] l) {

        for (Light Light : lights) {
            lights.add(Light);
        }

        return this;
    }

    public ArrayList<Shape> getShapes() {
        return shapes;
    }

    public World setShapes(ArrayList<Shape> shapes) {
        this.shapes = shapes;
        return this;
    }

    public ArrayList<Light> getLights() {
        return lights;
    }

    public World setLights(ArrayList<Light> lights) {
        this.lights = lights;
        return this;
    }

}
