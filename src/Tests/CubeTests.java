package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import Models.Intersection;
import Models.Ray;
import Models.Shapes.Cube;
import Models.Shapes.Sphere;
import Models.Tuples.Point;
import Models.Tuples.Vector;

public class CubeTests {

    @Test
    @DisplayName("Intersection Hits")
    public void IntersectionHits() {

        Cube c = new Cube();

        Ray r = new Ray(new Point(5, 0.5, 0), new Vector(-1, 0, 0));


        assertEquals(4, c.intersects(r).get(0).getT(),
                "Intersection hit method is not implemented correctly");

    }
}
