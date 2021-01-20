package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import Models.Intersection;
import Models.Sphere;

public class IntersectionTests {

    @Test
    @DisplayName("Intersection Hits")
    public void IntersectionHits() {

        Sphere s = new Sphere();
        Intersection i1 = new Intersection(1, s);
        Intersection i2 = new Intersection(2, s);
        Intersection i3 = new Intersection(-1, s);
        Intersection i4 = new Intersection(-2, s);

        assertEquals(i1, Intersection.hit(Intersection.intersections(i1, i2)),
                "Intersection hit method is not implemented correctly");

        assertEquals(i1, Intersection.hit(Intersection.intersections(i1, i3)),
                "Intersection hit method is not implemented correctly");

        assertEquals(null, Intersection.hit(Intersection.intersections(i3, i4)),
                "Intersection hit method is not implemented correctly");

        // assertEquals(4, Intersection.hit(Intersection.intersections(i1, i2)),
        // "Intersection hit method is not implemented correctly");

    }

}
