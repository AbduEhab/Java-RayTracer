package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import Models.Camera;
import Models.Canvas;
import Models.Color;
import Models.Matrix;
import Models.Point;
import Models.Ray;
import Models.Vector;
import Models.World;

public class CameraTests {

    @Test
    @DisplayName("Constructing a camera")
    public void constructor() {

        Camera c = new Camera(160, 120, Math.PI / 2);

        assertEquals(Math.PI / 2, c.getFov(), "Camera Constructor is not implemented correctly");
        assertEquals(120, c.gethSize(), "Camera Constructor is not implemented correctly");
        assertEquals(160, c.getvSize(), "Camera Constructor is not implemented correctly");
        assertEquals(true, c.getTransform().equals(Matrix.IDENTITY), "Camera Constructor is not implemented correctly");
    }

    @Test
    @DisplayName("The transformation matrix for the default orientation")
    public void transformDefault() {

        Camera c = new Camera(0, 0, 0);

        Matrix translation = c.transform(new Point(0, 0, 0), new Point(0, 0, -1), new Vector(0, 1, 0));
        assertEquals(true, translation.equals(Matrix.IDENTITY), "Camera transform method is not implemented correctly");
    }

    @Test
    @DisplayName("A view transformation matrix looking in positive z direction")
    public void transformPositiveZ() {

        Camera c = new Camera(0, 0, 0);

        Matrix translation = c.transform(new Point(0, 0, 0), new Point(0, 0, 1), new Vector(0, 1, 0));
        assertEquals(true, translation.equals(Matrix.IDENTITY.scale(-1, 1, -1)),
                "Camera transform method is not implemented correctly");
    }

    @Test
    @DisplayName("The view transformation moves the world")
    public void transformWorld() {

        Camera c = new Camera(0, 0, 0);

        Matrix translation = c.transform(new Point(0, 0, 8), new Point(0, 0, 0), new Vector(0, 1, 0));
        assertEquals(true, translation.equals(Matrix.IDENTITY.translate(0, 0, -8)),
                "Camera transform method is not implemented correctly");
    }

    @Test
    @DisplayName("An arbitrary view transformation")
    public void transformRand() {

        Camera c = new Camera(0, 0, 0);

        Matrix translation = c.transform(new Point(1, 3, 2), new Point(4, -2, 8), new Vector(1, 1, 0));
        assertEquals(true, translation.equals(new Matrix(4,
                new double[][] { { -0.50709, 0.50709, 0.67612, -2.36643 }, { 0.76772, 0.60609, 0.12122, -2.82843 },
                        { -0.35857, 0.59761, -0.71714, 0.00000 }, { 0.00000, 0.00000, 0.00000, 1.00000 } })),
                "Camera transform method is not implemented correctly");
    }

    @Test
    @DisplayName("PixelSize Check")
    public void constructorCheck() {

        Camera c = new Camera(200, 125, Math.PI / 2);
        Camera c2 = new Camera(125, 200, Math.PI / 2);

        assertEquals(true, 0.01 - c.getPixelSize() <= 0.001, "Camera constructor is not implemented correctly");
        assertEquals(true, 0.01 - c2.getPixelSize() <= 0.001, "Camera constructor is not implemented correctly");

    }

    @Test
    @DisplayName("Constructing a ray through the canvus")
    public void rayForPixel() {

        Camera c = new Camera(201, 101, Math.PI / 2);

        Ray res = c.rayForPixel(100, 50);
        assertEquals(true, res.getOrigin().equals(new Point()), "Camera RayForPixel is not implemented correctly");
        assertEquals(true, res.getDirection().equals(new Vector(0, 0, -1)),
                "Camera RayForPixel is not implemented correctly");

        res = c.rayForPixel(0, 0);
        assertEquals(true, res.getOrigin().equals(new Point()), "Camera RayForPixel is not implemented correctly");
        assertEquals(true, res.getDirection().equals(new Vector(0.66519, 0.33259, -0.66851)),
                "Camera RayForPixel is not implemented correctly");

        c.setTransform(Matrix.IDENTITY.rotateY(Math.PI / 4).translate(0, -2, 5));
        res = c.rayForPixel(100, 50);
        assertEquals(true, res.getOrigin().equals(new Point(0, 2, -5)),
                "Camera RayForPixel is not implemented correctly");
        assertEquals(true, res.getDirection().equals(new Vector(Math.sqrt(2) / 2, 0, -Math.sqrt(2) / 2)),
                "Camera RayForPixel is not implemented correctly");
    }

    @Test
    @DisplayName("Rendering a world with a camera")
    public void render() throws InterruptedException {

        World w = World.defaultWorld();
        Camera c = new Camera(11, 11, Math.PI / 2);

        c.transform(new Point(0, 0, -5), new Point(0, 0, 0), new Vector(0, -1, 0));

        Canvas canv = c.render(w);

        assertEquals(true, canv.getPixel()[5][5].equals(new Color(0.38066, 0.47583, 0.2855)),
                "Camera render method is not implemented correctly");

    }
}
