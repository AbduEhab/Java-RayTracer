package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import Models.Vector;

public class VectorTests {

    @Test
    @DisplayName("Negate Vector")
    public void Negate() {

        Vector a = new Vector(1, 0, 1);
        Vector b = new Vector(-1, 0, -1);

        Vector res = a.negate();

        assertEquals(true, res.equals(b), "Vector negation method is not valid");
    }

    @Test
    @DisplayName("Multiply Vector")
    public void multiply() {

        Vector a = new Vector(1, 0, 1);

        Vector res = a.multiply(2);

        assertEquals(2, res.getX(), "Vector multiplication method is not valid");
    }

    @Test
    @DisplayName("Divide Vector")
    public void divide() {

        Vector a = new Vector(1, 0, 1);

        Vector res = a.divide(2);

        assertEquals(0.5, res.getX(), "Vector division method is not valid");
    }

    @Test
    @DisplayName("Magnitude Vector")
    public void magnitude() {

        Vector a = new Vector(1, 2, 3);

        assertEquals(Math.sqrt(14), a.magnitude(), "Vector magnitude method is not valid");
    }

    @Test
    @DisplayName("normalize Vector")
    public void normalize() {

        Vector a = new Vector(4, 0, 0);

        Vector res = a.normalize();

        assertEquals(1, res.getX(), "Vector normalize method is not valid");
    }

    @Test
    @DisplayName("dot Vector")
    public void dot() {

        Vector a = new Vector(1, 2, 3);
        Vector b = new Vector(2, 3, 4);

        double res = a.dot(b);

        assertEquals(20, res, "Vector dot method is not valid");
    }

    @Test
    @DisplayName("cross Vector")
    public void cross() {

        Vector a = new Vector(1, 2, 3);
        Vector b = new Vector(2, 3, 4);

        Vector res = a.cross(b);

        assertEquals(-1, res.getX(), "Vector cross (X) method is not valid");
        assertEquals(2, res.getY(), "Vector cross (Y) method is not valid");
        assertEquals(-1, res.getZ(), "Vector cross (Z) method is not valid");
    }

}
