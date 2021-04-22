package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import Models.Computation;
import Models.Intersection;
import Models.Matrix;
import Models.Point;
import Models.Ray;
import Models.Sphere;
import Models.Vector;

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

        }

        @Test
        @DisplayName("Testing hit offset")
        public void hitOffset() {

                Ray r = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));

                Sphere s = new Sphere();
                s.setTransform(Matrix.IDENTITY.translate(0, 0, 1));

                Intersection intersection = new Intersection(5, s);

                Computation comp = intersection.prepareComputate(r);

                assertEquals(true, comp.getOverPoint().getZ() < -0.00001 / 2,
                                "Intersection hit-offset is not implemented correctly");

                assertEquals(true, comp.getPoint().getZ() > comp.getOverPoint().getZ(),
                                "Intersection hit-offset is not implemented correctly");

        }

}
