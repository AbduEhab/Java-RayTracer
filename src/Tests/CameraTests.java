package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import Models.Camera;
import Models.Matrix;
import Models.Point;
import Models.Vector;

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

}
