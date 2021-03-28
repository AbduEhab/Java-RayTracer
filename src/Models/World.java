package Models;

import java.util.ArrayList;

public class World {

    private ArrayList<Shape> shapes = new ArrayList<Shape>();
    private ArrayList<PointLight> lights = new ArrayList<PointLight>();

    public World() {
    }

    private World(Object o) {
        Sphere sphere = new Sphere();
        sphere.setMaterial(new Material(new Color(0.8, 1, 0.6), -1, 0.7, 0.2, -1));
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
        for (PointLight pointLight : lights) {
            res = res.add(c.getShape().getMaterial().lighting(pointLight, c.getPoint(), c.getEyeVector(),
                    c.getNormalVector()));
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

    public ArrayList<Shape> getShapes() {
        return shapes;
    }

    public void setShapes(ArrayList<Shape> shapes) {
        this.shapes = shapes;
    }

    public ArrayList<PointLight> getLights() {
        return lights;
    }

    public void setLights(ArrayList<PointLight> lights) {
        this.lights = lights;
    }

}
