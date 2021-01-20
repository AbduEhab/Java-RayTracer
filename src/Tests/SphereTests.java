package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import Models.Point;
import Models.Ray;
import Models.Sphere;
import Models.Vector;

public class SphereTests {

    @Test
    @DisplayName("Sphere intersection")
    public void sphereIntersection() {

        Ray r = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));
        Ray r2 = new Ray(new Point(0, 1, -5), new Vector(0, 0, 1));
        Ray r3 = new Ray(new Point(0, 2, -5), new Vector(0, 0, 1));
        Ray r4 = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));

        Sphere s = new Sphere();

        assertEquals(4, s.intersects(r).get(0).getT(), "Sphere intersection method is not implemented correctly");
        assertEquals(s, s.intersects(r).get(0).getObject(), "Sphere intersection method is not implemented correctly");

        assertEquals(5, s.intersects(r2).get(0).getT(), "Sphere intersection method is not implemented correctly");
        assertEquals(s, s.intersects(r2).get(0).getObject(), "Sphere intersection method is not implemented correctly");

        assertEquals(null, s.intersects(r3), "Sphere intersection method is not implemented correctly");
        assertEquals(null, s.intersects(r3), "Sphere intersection method is not implemented correctly");

        assertEquals(-1, s.intersects(r4).get(0).getT(), "Sphere intersection method is not implemented correctly");
        assertEquals(s, s.intersects(r4).get(0).getObject(), "Sphere intersection method is not implemented correctly");
    }
}