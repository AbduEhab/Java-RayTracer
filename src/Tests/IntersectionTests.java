package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import Models.Computation;
import Models.Intersection;
import Models.Matrix;
import Models.Ray;
import Models.Shapes.Sphere;
import Models.Shapes.XZPlane;
import Models.Tuples.Point;
import Models.Tuples.Vector;

public class IntersectionTests {

        @Test
        @DisplayName("Intersection Hits")
        public void IntersectionHits() {

                Models.Shapes.Sphere s = new Sphere();
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

                Computation comp = intersection.prepareComputate(r, null);

                assertEquals(true, comp.getOverPoint().getZ() < -0.00001 / 2,
                                "Intersection hit-offset is not implemented correctly");

                assertEquals(true, comp.getPoint().getZ() > comp.getOverPoint().getZ(),
                                "Intersection hit-offset is not implemented correctly");

        }

        @Test
        @DisplayName("Testing reflection Computation")
        public void reflectionComputation() {

                Ray r = new Ray(new Point(0, 1, -1), new Vector(0, -Math.sqrt(2) / 2, Math.sqrt(2) / 2));

                XZPlane s = new XZPlane();

                Intersection intersection = new Intersection(Math.sqrt(2), s);

                Computation comp = intersection.prepareComputate(r, null);

                assertEquals(true, comp.getReflectionVector().equals(new Vector(0, Math.sqrt(2) / 2, Math.sqrt(2) / 2)),
                                "Intersection reflection Vector is not computed correctly");
        }

        @Test
        @DisplayName("Testing refraction Computation")
        public void refractionComputation() {

                Sphere a = Sphere.glassSphere().setTransform(Matrix.IDENTITY.scale(2, 2, 2))
                                .setMaterial(Sphere.glassSphere().getMaterial().setRefractiveIndex(1.5));
                Sphere b = Sphere.glassSphere().setTransform(Matrix.IDENTITY.translate(0, 0, -0.25))
                                .setMaterial(Sphere.glassSphere().getMaterial().setRefractiveIndex(2));
                Sphere c = Sphere.glassSphere().setTransform(Matrix.IDENTITY.translate(0, 0, 0.25))
                                .setMaterial(Sphere.glassSphere().getMaterial().setRefractiveIndex(2.5));

                Ray r = new Ray(new Point(0, 0, -4), new Vector(0, 0, 1));

                Intersection[] intersections = { new Intersection(2, a), new Intersection(2.75, b),
                                new Intersection(3.25, c), new Intersection(4.75, b), new Intersection(5.25, c),
                                new Intersection(6, a) };

                ArrayList<Intersection> xs = new ArrayList<Intersection>(Arrays.asList(intersections));

                Double[][] results = { { 1.0, 1.5 }, { 1.5, 2d }, { 2d, 2.5 }, { 2.5, 2d }, { 2d, 2.5 }, { 2.5, 1.5 } };

                for (int i = 0; i < intersections.length; i++) {
                        Computation comp = intersections[i].prepareComputate(r, xs);

                        assertEquals(results[i][0], comp.getN1(),
                                        "Intersection refraction index 1 is not computed correctly");

                        assertEquals(results[i][1], comp.getN2(),
                                        "Intersection reflection index 2 is not computed correctly");
                }
        }

        @Test
        @DisplayName("Testing underpoint Computation")
        public void underpointoo() {

                Ray r = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));

                Sphere s = Sphere.glassSphere().setTransform(Matrix.IDENTITY.translate(0, 0, 1));

                Intersection intersection = new Intersection(5, s);

                ArrayList<Intersection> xs = new ArrayList<Intersection>(
                                Arrays.asList(new Intersection[] { intersection }));

                Computation comp = intersection.prepareComputate(r, xs);

                assertEquals(true, comp.getUnderPoint().getZ() > 0.00001 / 2,
                                "Intersection underPoint is not implemented correctly");
        }

}
