package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import Models.Point;
import Models.Ray;
import Models.Sphere;
import Models.Vector;

public class RayTests {
    @Test
    @DisplayName("Create Ray")
    public void createRay() {

        Point p = new Point();

        Vector v = new Vector();

        Ray r = new Ray(p, v);

        assertEquals(true, r.getDirection().equals(v), "Ray's vector not initiated correctly");
        assertEquals(true, r.getOrigin().equals(p), "Ray's point not initiated correctly");
    }

    @Test
    @DisplayName("Ray Position")
    public void createMatrix() {

        Point p = new Point(2, 3, 4);

        Vector v = new Vector(1, 0, 0);

        Ray r = new Ray(p, v);

        Point res = new Point(2, 3, 4);
        Point res2 = new Point(3, 3, 4);

        assertEquals(true, r.position(0).equals(res), "Ray's vector not initiated correctly");
        assertEquals(true, r.position(1).equals(res2), "Ray's point not initiated correctly");
    }
}
