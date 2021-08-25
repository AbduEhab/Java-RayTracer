package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Timeout;

import Models.Computation;
import Models.Intersection;
import Models.Matrix;
import Models.Ray;
import Models.Shapes.Shape;
import Models.Shapes.Sphere;
import Models.Shapes.XZPlane;
import Models.Tuples.Color;
import Models.Tuples.Point;
import Models.Tuples.Vector;
import Models.World;
import Models.Lights.PointLight;

public class WorldTests {

    @Test
    @DisplayName("world contruction")
    public void constructor() {

        World w = new World();
        assertEquals(0, w.getLights().size(), "World constructor is not implemented correctly");
        assertEquals(0, w.getShapes().size(), "World constructor is not implemented correctly");
    }

    @Test
    @DisplayName("default world contruction")
    public void defaultWorld() {

        World w = World.defaultWorld();

        PointLight p = new PointLight(new Color(1, 1, 1), new Point(-10, 10, -10));

        assertEquals(true, w.getLights().get(0).equals(p), "World default constructor is not implemented correctly");
        assertEquals(2, w.getShapes().size(), "World default constructor is not implemented correctly");
    }

    @Test
    @DisplayName("ray intersection")
    public void intersects() {

        World w = World.defaultWorld();

        Ray r = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));

        ArrayList<Intersection> res = w.intersects(r);

        assertEquals(4, res.get(0).getT(), "World intersection method is not implemented correctly");
        assertEquals(4.5, res.get(1).getT(), "World intersection method is not implemented correctly");
        assertEquals(5.5, res.get(2).getT(), "World intersection method is not implemented correctly");
        assertEquals(6, res.get(3).getT(), "World intersection method is not implemented correctly");
    }

    @Test
    @DisplayName("Pre-Computation")
    public void prepareCompute() {

        Ray r = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));

        Shape s = new Sphere();
        Point p = new Point(0, 0, -1);
        Vector eyeVector = new Vector(0, 0, -1);
        Vector normalVector = new Vector(0, 0, -1);

        Intersection i = new Intersection(4, s);

        Computation c = i.prepareComputate(r);

        assertEquals(true, c.getNormalVector().equals(normalVector),
                "World prepare Computation method is not implemented correctly");
        assertEquals(true, c.getEyeVector().equals(eyeVector),
                "World prepare Computation method is not implemented correctly");
        assertEquals(true, c.getPoint().equals(p), "World prepare Computation method is not implemented correctly");
        assertEquals(true, c.getT() == 4, "World prepare Computation method is not implemented correctly");
        assertEquals(false, c.isInside(), "World prepare Computation method is not implemented correctly");
    }

    @Test
    @DisplayName("Shading an intersection")
    public void shadingIntersects() {

        World w = World.defaultWorld();

        Ray r = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));

        Shape s = w.getShapes().get(0);

        Intersection i = new Intersection(4, s);

        Computation c = i.prepareComputate(r);

        Color color = w.shadeHit(c);

        Color res = new Color(0.38066f, 0.47583f, 0.2855f);

        assertEquals(true, color.equals(res), "World Shade Hit method is not implemented correctly");
    }

    @Test
    @DisplayName("Shading an intersection from the inside")
    public void shadingInsideIntersects() {

        World w = World.defaultWorld();

        w.getLights().set(0, new PointLight(new Color(1, 1, 1), new Point(0, 0.25f, 0)));

        Ray r = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));

        Shape s = w.getShapes().get(1);

        Intersection i = new Intersection(0.5f, s);

        Computation c = i.prepareComputate(r);

        Color color = w.shadeHit(c);

        Color res = new Color(0.90498f, 0.90498f, 0.90498f);

        assertEquals(true, color.equals(res), "World Shade Hit method is not implemented correctly");
    }

    @Test
    @DisplayName("Shadow casting test")
    public void inShadow() {

        World w = World.defaultWorld();

        Point p = new Point(0, 10, 0);
        assertEquals(false, w.isShadowed(p, new PointLight(new Color(0, 0, 0), new Point(-10, 10, -10))),
                "World isShadow is not valid");

        p = new Point(10, -10, 10);
        assertEquals(true, w.isShadowed(p, new PointLight(new Color(0, 0, 0), new Point(-10, 10, -10))),
                "World isShadow is not valid");

        p = new Point(-20, 20, -20);
        assertEquals(false, w.isShadowed(p, new PointLight(new Color(0, 0, 0), new Point(-10, 10, -10))),
                "World isShadow is not valid");

        p = new Point(-2, 2, -2);
        assertEquals(false, w.isShadowed(p, new PointLight(new Color(0, 0, 0), new Point(-10, 10, -10))),
                "World isShadow is not valid");
    }

    @Test
    @DisplayName("shade_hit() is given an intersection in shadow")
    public void shadeHitInShadow() {

        World w = new World();
        w.addLight(new PointLight(new Color(1, 1, 1), new Point(0, 0, -10)));

        Sphere s1 = new Sphere();
        w.addShape(s1);

        Sphere s2 = new Sphere();
        s2.setTransform(Matrix.IDENTITY.translate(0, 0, 10));
        w.addShape(s2);

        Ray r = new Ray(new Point(0, 0, 5), new Vector(0, 0, 1));
        Intersection intersection = new Intersection(4, s2);

        Computation comp = intersection.prepareComputate(r);

        Color result = w.shadeHit(comp);

        Color expected = new Color(0.1f, 0.1f, 0.1f);

        assertEquals(true, result.equals(expected), "World Shade Hit in Shadow method is not implemented correctly");
    }

    @Test
    @DisplayName("Reflected Color Method")
    public void reflectedColor() {

        World w = World.defaultWorld();

        Ray r = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));

        Shape s = w.getShapes().get(1);

        s.getMaterial().setAmbient(1);

        Intersection i = new Intersection(1, s);

        Computation comp = i.prepareComputate(r);

        Color c = w.reflectedColor(comp);

        assertEquals(true, c.equals(Color.BLACK), "Reflected Color Method is not implemented correctly");
    }

    @Test
    @DisplayName("Reflected Color Method further testing")
    public void reflectedColor2() {

        World w = World.defaultWorld();

        Shape s = new XZPlane();

        s.getMaterial().setReflectiveness(0.5);

        s.setTransform(Matrix.IDENTITY.translate(0, -1, 0));

        w.addShape(s);

        Ray r = new Ray(new Point(0, 0, -3), new Vector(0, -Math.sqrt(2) / 2, Math.sqrt(2) / 2));

        Intersection i = new Intersection(Math.sqrt(2), s);

        Computation comp = i.prepareComputate(r);

        Color c = w.reflectedColor(comp);

        System.out.println(c);

        assertEquals(true, c.equals(new Color(0.19033, 0.23791, 0.14274)),
                "Reflected Color Method is not implemented correctly");
    }

    @Test
    @DisplayName("Reflected Color Method extended testing")
    public void shadeHitReflectedColor() {

        World w = World.defaultWorld();

        Shape s = new XZPlane();

        s.getMaterial().setReflectiveness(0.5);

        s.setTransform(Matrix.IDENTITY.translate(0, -1, 0));

        w.addShape(s);

        Ray r = new Ray(new Point(0, 0, -3), new Vector(0, -Math.sqrt(2) / 2, Math.sqrt(2) / 2));

        Intersection i = new Intersection(Math.sqrt(2), s);

        Computation comp = i.prepareComputate(r);

        Color c = w.shadeHit(comp);

        assertEquals(true, c.equals(new Color(0.87675, 0.92434, 0.82917)),
                "Reflected Color Method is not implemented correctly");
    }

    @Timeout(1000)
    @Test
    @DisplayName("Reflected Color Method further extended testing")
    public void infiniteRecursionTest() {

        World w = new World();

        w.addLight(new PointLight(Color.WHITE, new Point()));

        Shape s = new XZPlane();
        s.setTransform(Matrix.IDENTITY.translate(0, -1, 0)).getMaterial().setReflectiveness(1);

        w.addShape(s);

        Shape s2 = new XZPlane();
        s2.setTransform(Matrix.IDENTITY.translate(0, 1, 0)).getMaterial().setReflectiveness(1);

        w.addShape(s2);

        Ray r = new Ray(new Point(), new Vector(0, 1, 0));

        w.colorAt(r);

        assertEquals(true, true, "Reflected Color Method is not implemented correctly");
    }

}
