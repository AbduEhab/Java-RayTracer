package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import Models.Ray;
import Models.Shapes.XZPlane;
import Models.Tuples.Point;
import Models.Tuples.Vector;

public class PlaneTests {

    @Test
    @DisplayName("normalAt")
    public void normalAt() {

        XZPlane p = new XZPlane();

        assertEquals(true, p.normalAt(new Point(0, 0, 0)).equals(new Vector(0, 1, 0)),
                "Plane normalAt vector is not returned correctly");

        assertEquals(true, p.normalAt(new Point(10, 0, -10)).equals(new Vector(0, 1, 0)),
                "Plane normalAt vector is not returned correctly");

        assertEquals(true, p.normalAt(new Point(-5, 0, 150)).equals(new Vector(0, 1, 0)),
                "Plane normalAt vector is not returned correctly");
    }

    @Test
    @DisplayName("Intersect with a ray parallel to the plane")
    public void intersectionsTest() {

        XZPlane p = new XZPlane();

        Ray r = new Ray(new Point(0, 10, 0), new Vector(0, 0, 1));

        var xs = p.intersects(r);

        assertEquals(true, xs == null, "Plane intersects method is not implemented correctly");

        r = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));

        xs = p.intersects(r);

        assertEquals(true, xs == null, "Plane intersects method is not implemented correctly");

    }

    @Test
    @DisplayName("A ray intersecting a plane from above")
    public void intersectionsabobeTest() {

        XZPlane p = new XZPlane();

        Ray r = new Ray(new Point(0, 1, 0), new Vector(0, -1, 0));

        var xs = p.intersects(r);

        assertEquals(1, xs.size(), "Plane intersects method return size is not implemented correctly");
        assertEquals(true, xs.get(0).getT() == 1, "Plane intersects method return T is not implemented correctly");
        assertEquals(true, xs.get(0).getShape().equals(p),
                "Plane intersects method return shape is not implemented correctly");

    }

    @Test
    @DisplayName("A ray intersecting a plane from below")
    public void intersectionsunderTest() {

        XZPlane p = new XZPlane();

        Ray r = new Ray(new Point(0, -1, 0), new Vector(0, 1, 0));

        var xs = p.intersects(r);

        assertEquals(1, xs.size(), "Plane intersects method return size is not implemented correctly");
        assertEquals(true, xs.get(0).getT() == 1, "Plane intersects method return T is not implemented correctly");
        assertEquals(true, xs.get(0).getShape().equals(p),
                "Plane intersects method return shape is not implemented correctly");

    }
}
