package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import Models.Material;
import Models.Matrix;
import Models.Point;
import Models.Ray;
import Models.Sphere;
import Models.Vector;

public class SphereTests {

    @Test
    @DisplayName("Sphere Intersection")
    public void sphereIntersection() {

        Ray r = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));
        Ray r2 = new Ray(new Point(0, 1, -5), new Vector(0, 0, 1));
        Ray r3 = new Ray(new Point(0, 2, -5), new Vector(0, 0, 1));
        Ray r4 = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));

        Sphere s = new Sphere();

        assertEquals(4, s.intersects(r).get(0).getT(), "Sphere intersection method is not implemented correctly");
        assertEquals(s, s.intersects(r).get(0).getShape(), "Sphere intersection method is not implemented correctly");

        assertEquals(5, s.intersects(r2).get(0).getT(), "Sphere intersection method is not implemented correctly");
        assertEquals(s, s.intersects(r2).get(0).getShape(), "Sphere intersection method is not implemented correctly");

        assertEquals(null, s.intersects(r3), "Sphere intersection method is not implemented correctly");

        assertEquals(-1, s.intersects(r4).get(0).getT(), "Sphere intersection method is not implemented correctly");
        assertEquals(s, s.intersects(r4).get(0).getShape(), "Sphere intersection method is not implemented correctly");
    }

    @Test
    @DisplayName("Sphere Transform")
    public void spheretransform() {

        Sphere s = new Sphere();

        Matrix res = Matrix.IDENTITY.translate(2, 3, 4);

        s.setTransform(Matrix.IDENTITY.translate(2, 3, 4));

        assertEquals(true, s.getTransform().equals(res), "Sphere setTransform method is not implemented correctly");
    }

    @Test
    @DisplayName("Sphere Intersection after translation")
    public void sphereIntersection2() {

        Sphere s = new Sphere();

        Ray ray = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));

        s.setTransform(Matrix.IDENTITY.scale(2, 2, 2));

        assertEquals(3, ray.intersects(s).get(0).getT(),
                "Sphere translation intersection method is not implemented correctly");

        assertEquals(7, ray.intersects(s).get(1).getT(),
                "Sphere translation intersection method is not implemented correctly");

        ray = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));

        s.setTransform(Matrix.IDENTITY.translate(5, 0, 0));

        assertEquals(true, ray.intersects(s) == null,
                "Sphere translation intersection method is not implemented correctly");
    }

    @Test
    @DisplayName("Sphere normal at a point")
    public void normalAt() {

        Sphere s = new Sphere();

        Point p1 = new Point(1, 0, 0);
        Point p2 = new Point(0, 1, 0);
        Point p3 = new Point(0, 0, 1);
        Point p4 = new Point(Math.sqrt(3) / 3, Math.sqrt(3) / 3, Math.sqrt(3) / 3);

        Vector r1 = new Vector(1, 0, 0);
        Vector r2 = new Vector(0, 1, 0);
        Vector r3 = new Vector(0, 0, 1);
        Vector r4 = new Vector(Math.sqrt(3) / 3, Math.sqrt(3) / 3, Math.sqrt(3) / 3);

        assertEquals(true, s.normalAt(p1).equals(r1), "Sphere normalAt method is not implemented correctly");
        assertEquals(true, s.normalAt(p2).equals(r2), "Sphere normalAt method is not implemented correctly");
        assertEquals(true, s.normalAt(p3).equals(r3), "Sphere normalAt method is not implemented correctly");
        assertEquals(true, s.normalAt(p4).equals(r4), "Sphere normalAt method is not implemented correctly");

    }

    @Test
    @DisplayName("check to see id the normalAt method's return is normalized")
    public void normalAtNormalized() {

        Sphere s = new Sphere();

        Point p = new Point(Math.sqrt(3) / 3, Math.sqrt(3) / 3, Math.sqrt(3) / 3);

        Vector r = new Vector(Math.sqrt(3) / 3, Math.sqrt(3) / 3, Math.sqrt(3) / 3);

        assertEquals(true, s.normalAt(p).normalize().equals(r), "Sphere normalAt method is not implemented correctly");

    }

    @Test
    @DisplayName("Computing the normal on a translated sphere")
    public void normalAtTranslated() {

        Sphere s = new Sphere();

        s.setTransform(Matrix.IDENTITY.translate(0, 1, 0));

        Point p = new Point(0, 1.70711, -0.70711);

        Vector r = new Vector(0, 0.70711, -0.70711);

        assertEquals(true, s.normalAt(p).equals(r), "Sphere normalAt method is not implemented correctly");

    }

    @Test
    @DisplayName("Computing the normal on a transformed sphere")
    public void normalAtTransformed() {

        Sphere s = new Sphere();

        s.setTransform(Matrix.IDENTITY.scale(1, 0.5, 1).rotateZ(Math.PI / 5));

        Point p = new Point(0, Math.sqrt(2) / 2, -Math.sqrt(2) / 2);

        Vector r = new Vector(0, 0.97014, -0.24254);

        assertEquals(true, s.normalAt(p).equals(r), "Sphere normalAt method is not implemented correctly");

    }

    @Test
    @DisplayName("sphere material init")
    public void materialInit() {

        Sphere s = new Sphere();

        assertEquals(true, s.getMaterial().equals(new Material()),
                "Sphere normalAt method is not implemented correctly");

    }
}