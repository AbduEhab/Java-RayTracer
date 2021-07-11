package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import Models.Material;
import Models.PointLight;
import Models.Tuples.Color;
import Models.Tuples.Point;
import Models.Tuples.Vector;

public class MaterialTests {

    @Test
    @DisplayName("Material construction")
    public void constructor() {

        Material m = new Material();

        assertEquals(true, m.getColor().equals(new Color(1, 1, 1)), "Material constructor is not valid");
        assertEquals(0.1, m.getAmbient(), "Material constructor is not valid");
        assertEquals(0.9, m.getDiffuse(), "Material constructor is not valid");
        assertEquals(0.9, m.getSpecular(), "Material constructor is not valid");
        assertEquals(200, m.getShininess(), "Material constructor is not valid");
    }

    @Test
    @DisplayName("lighting method")
    public void lighting() {

        Material m = new Material();
        Point p = new Point();
        Vector eyev = new Vector(0, 0, -1);
        Vector normalv = new Vector(0, 0, -1);
        PointLight l = new PointLight(new Color(1, 1, 1), new Point(0, 0, -10));
        Color res = new Color(1.9, 1.9, 1.9);

        assertEquals(true, m.lighting(l, p, eyev, normalv, false).equals(res), "Material lighting is not valid");

        p = new Point();
        eyev = new Vector(0, Math.sqrt(2) / 2, -Math.sqrt(2) / 2);
        normalv = new Vector(0, 0, -1);
        l = new PointLight(new Color(1, 1, 1), new Point(0, 0, -10));
        res = new Color(1, 1, 1);

        assertEquals(true, m.lighting(l, p, eyev, normalv, false).equals(res), "Materiallighting is not valid");

        p = new Point();
        eyev = new Vector(0, 0, -1);
        normalv = new Vector(0, 0, -1);
        l = new PointLight(new Color(1, 1, 1), new Point(0, 10, -10));
        res = new Color(0.7364, 0.7364, 0.7364);

        assertEquals(true, m.lighting(l, p, eyev, normalv, false).equals(res), "Material lighting is not valid");

        p = new Point();
        eyev = new Vector(0, -Math.sqrt(2) / 2, -Math.sqrt(2) / 2);
        normalv = new Vector(0, 0, -1);
        l = new PointLight(new Color(1, 1, 1), new Point(0, 10, -10));
        res = new Color(1.6364, 1.6364, 1.6364);

        assertEquals(true, m.lighting(l, p, eyev, normalv, false).equals(res), "Material lighting is not valid");

        p = new Point();
        eyev = new Vector(0, 0, -1);
        normalv = new Vector(0, 0, -1);
        l = new PointLight(new Color(1, 1, 1), new Point(0, 0, 10));
        res = new Color(0.1, 0.1, 0.1);

        assertEquals(true, m.lighting(l, p, eyev, normalv, false).equals(res), "Material lighting is not valid");
    }

    @Test
    @DisplayName("Lighting with the surface in shadow")
    public void lightingInShadow() {

        Material m = new Material();
        Point p = new Point();
        Vector eyev = new Vector(0, 0, -1);
        Vector normalv = new Vector(0, 0, -1);
        PointLight l = new PointLight(new Color(1, 1, 1), new Point(0, 0, -10));
        Color res = new Color(0.1, 0.1, 0.1);

        assertEquals(true, m.lighting(l, p, eyev, normalv, true).equals(res), "Material Shadow-lighting is not valid");
    }

}
