package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import Models.Matrix;
import Models.Point;
import Models.Ray;
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
    public void rayPosition() {

        Point p = new Point(2, 3, 4);

        Vector v = new Vector(1, 0, 0);

        Ray r = new Ray(p, v);

        Point res = new Point(2, 3, 4);
        Point res2 = new Point(3, 3, 4);

        assertEquals(true, r.position(0).equals(res), "Ray's position method not implemented correctly");
        assertEquals(true, r.position(1).equals(res2), "Ray's position method not implemented correctly");
    }

    @Test
    @DisplayName("Ray transformation")
    public void rayTransformation() {

        Point p = new Point(1, 2, 3);

        Vector v = new Vector(0, 1, 0);

        Ray r = new Ray(p, v);

        Point res = new Point(4, 6, 8);

        Vector resvec = new Vector(0, 3, 0);
        Point res2 = new Point(2, 6, 12);

        assertEquals(true, r.transform(Matrix.IDENTITY.translate(3, 4, 5)).getOrigin().equals(res),
                "Ray's translation method not implemented correctly");
        assertEquals(true, r.transform(Matrix.IDENTITY.translate(3, 4, 5)).getDirection().equals(v),
                "Ray's translation method not implemented correctly");

        assertEquals(true, r.transform(Matrix.IDENTITY.scale(2, 3, 4)).getOrigin().equals(res2),
                "Ray's translation(scale) method not implemented correctly");
        assertEquals(true, r.transform(Matrix.IDENTITY.scale(2, 3, 4)).getDirection().equals(resvec),
                "Ray's translation(scale) method not implemented correctly");
    }
}
